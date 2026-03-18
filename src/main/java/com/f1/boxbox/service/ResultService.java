package com.f1.boxbox.service;

import com.f1.boxbox.model.Result;
import com.f1.boxbox.repository.ResultRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    private final ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public List<Result> findAll() { return resultRepository.findAll(); }

    public Optional<Result> findById(Long id) { return resultRepository.findById(id); }

    @Transactional
    public Result create(Result result) {
        return resultRepository.save(result);
    }

    @Transactional
    public Result update(Long id, Result result) {
        Result r = resultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result not found"));

        r.setDriver(result.getDriver());
        r.setPoints(result.getPoints());
        r.setRace(result.getRace());
        r.setPosition(result.getPosition());

        return resultRepository.save(r);
    }

    @Transactional
    public void delete(Long id) {
        if (resultRepository.existsById(id)) {
            throw new RuntimeException("Result not found");
        }
        resultRepository.deleteById(id);
    }
}
