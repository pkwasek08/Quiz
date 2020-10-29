package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.Result;
import pl.project.repositories.ResultRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;

    public List<Result> getAllResult() {
        List<Result> resultList = new ArrayList<>();
        resultRepository.findAll().forEach(resultList::add);
        return resultList;
    }

    public Result getResult(Integer id) {
        Result result = resultRepository.findById(id).get();
        return result;
    }

    public void addResult(Result result) {
        resultRepository.save(result);
    }


    public void updateResult(Integer id, Result result) {
        resultRepository.save(result);
    }


    public void deleteResult(Integer id) {
        resultRepository.deleteById(id);
    }
}
