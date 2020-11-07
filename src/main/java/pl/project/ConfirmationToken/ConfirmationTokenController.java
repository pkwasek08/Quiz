package pl.project.ConfirmationToken;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/token")
public class ConfirmationTokenController {
    Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<ConfirmationToken> getAllConfirmationToken() {
        return confirmationTokenService.getAllConfirmationToken();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ConfirmationToken getConfirmationToken(@PathVariable Integer id) {
        return confirmationTokenService.getConfirmationToken(id);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addConfirmationToken(@RequestBody ConfirmationToken confirmationToken) {
        confirmationTokenService.addConfirmationToken(confirmationToken);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateConfirmationToken(@RequestBody ConfirmationToken confirmationToken, @PathVariable Integer id) {
        confirmationTokenService.updateConfirmationToken(id, confirmationToken);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteConfirmationToken(@PathVariable Integer id) {
        confirmationTokenService.deleteConfirmationToken(id);
    }
}
