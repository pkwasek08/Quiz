package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.ConfirmationToken;
import pl.project.entities.GenerateTask;
import pl.project.repositories.ConfirmationTokenRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfirmationTokenService {
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    public List<ConfirmationToken> getAllConfirmationToken() {
        List<ConfirmationToken> confirmationTokenList = new ArrayList<>();
        confirmationTokenRepository.findAll().forEach(confirmationTokenList::add);
        return confirmationTokenList;
    }

    public ConfirmationToken getConfirmationToken(Integer id) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findById(id).get();
        return confirmationToken;
    }

    public void addConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }


    public void updateConfirmationToken(Integer id, ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }


    public void deleteConfirmationToken(Integer id) {
        confirmationTokenRepository.deleteById(id);
    }
}
