package pl.project.payload;

import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.project.ConfirmationToken.ConfirmationToken;
import pl.project.ConfirmationToken.ConfirmationTokenRepository;
import pl.project.payload.dto.ExpirationTimeDTO;
import pl.project.User.User;
import pl.project.payload.request.LoginRequest;
import pl.project.payload.request.SignupRequest;
import pl.project.payload.response.JwtResponse;
import pl.project.payload.response.MessageResponse;
import pl.project.payload.response.RefreshTokenResponse;
import pl.project.User.UserRepository;
import pl.project.security.MyUserDetails;
import pl.project.security.jwt.JwtUtils;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
        if(!userRepository.existsByLoginAndEnabled(loginRequest.getLogin(), true)){
            return ResponseEntity.badRequest().body(new MessageResponse("Verify your email"));
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));
        System.out.println("---------" + authentication.isAuthenticated() + "-----------");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        List<String> roles = myUserDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                myUserDetails.getId(),
                myUserDetails.getUsername(),
                myUserDetails.getEmail(),
                roles
        ));
    }

    @GetMapping("/refreshtoken")
    public RefreshTokenResponse refreshToken(HttpServletRequest request) throws Exception {
        DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");
        Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
        String token = jwtUtils.generateRefreshToken(expectedMap, expectedMap.get("sub").toString());
        return new RefreshTokenResponse(token);
    }

    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry: claims.entrySet()){
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) throws MessagingException {
        if(signupRequest.getLogin() == null || signupRequest.getLogin().length() < 4){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username's length should be at least 4"));
        }
        if(signupRequest.getEmail() == null || !signupRequest.getEmail().contains("@")){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is not valid"));
        }
        if(userRepository.existsByLogin(signupRequest.getLogin())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken"));
        }
        if(userRepository.existsByEmail(signupRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken"));
        }

        if(signupRequest.getPassword() == null || signupRequest.getPassword().length() < 5){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Password's length should be at least 5"));
        }

        if(signupRequest.getLastname() == null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Lastname cannot be null"));
        }

        if(signupRequest.getDegree() == null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Degree cannot be null"));
        }

        if(signupRequest.getDepartment() == null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Department cannot be null"));
        }

        if(signupRequest.getMajor() == null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Major cannot be null"));
        }

//        String roles = null;
//        if(signupRequest.getEmail().contains("@stud.prz.edu.pl"))
//            roles = "ROLE_STUDENT";
//        else if(signupRequest.getEmail().contains("@prz.edu.pl"))
//            roles = "ROLE_TEACHER";
//
//        if(roles == null){
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Email should be @stud.prz.edu.pl or @prz.edu.pl"));
//        }

//        User user = new User(0, signupRequest.getName(), signupRequest.getLastname(), signupRequest.getDegree(), signupRequest.getEmail(),
//                signupRequest.getDepartment(), signupRequest.getMajor(), encoder.encode(signupRequest.getPassword()), signupRequest.getLogin(), roles);

        User user = new User(0, signupRequest.getName(), signupRequest.getLastname(), signupRequest.getDegree(), signupRequest.getEmail(),
                signupRequest.getDepartment(), signupRequest.getMajor(), encoder.encode(signupRequest.getPassword()), signupRequest.getLogin(), "ROLE_USER", false);
        userRepository.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("javaquiz123@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                +"https://dashboard.heroku.com/apps/quiz-server-prz/api/auth/confirm-account?token="+confirmationToken.getConfirmationToken());

        emailSenderService.sendEmail(mailMessage);

        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }

    @PostMapping("/expiration")
    public ResponseEntity<?> expirationUpdate(@RequestBody ExpirationTimeDTO expirationTimeDTO){
        jwtUtils.setJwtExpirationMs(expirationTimeDTO.getExpirationTime());
        jwtUtils.setRefreshExpirationTime(expirationTimeDTO.getRefreshExpirationTime());
        return ResponseEntity.ok(new MessageResponse("Expiration times have been changed"));
    }

    @GetMapping("/confirm-account")
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken){
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null){
            User user = userRepository.findByEmailIgnoreCase(token.getUsersByUserId().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: The link is invalid or broken"));
        }
        return ResponseEntity.ok(new MessageResponse("Account is verified"));
    }

    @PostMapping("/resendConfirmation")
    public ResponseEntity<?> resendConfirmation(@RequestBody String email) throws MessagingException {
        User user = userRepository.findByEmailIgnoreCase(email);
        if(user == null) return ResponseEntity.badRequest().body(new MessageResponse("Error - User with email " + email + " doesn't exist"));
        if(user.getEnabled()) return ResponseEntity.badRequest().body(new MessageResponse("Error - User with email " + email + " is enabled"));
        ConfirmationToken confirmationToken = confirmationTokenRepository.getByUsersByUserId(user);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("javaquiz123@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                +"https://dashboard.heroku.com/apps/quiz-server-prz/api/auth/confirm-account?token="+confirmationToken.getConfirmationToken());

        emailSenderService.sendEmail(mailMessage);
        return ResponseEntity.ok(new MessageResponse("The mail was sent again"));
    }
}
























