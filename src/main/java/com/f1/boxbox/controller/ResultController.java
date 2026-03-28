package com.f1.boxbox.controller;

import com.f1.boxbox.dto.request.ResultRequest;
import com.f1.boxbox.dto.response.ResultResponse;
import com.f1.boxbox.service.ResultService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@Tag(name = "Results")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping
    public List<ResultResponse> findAll() {
        return resultService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultResponse> findById(@PathVariable Long id) {
        ResultResponse result = resultService.findById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ResultResponse> save(@Valid @RequestBody ResultRequest resultRequest) {
        ResultResponse r = resultService.create(resultRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultResponse> update(@PathVariable Long id,
                                                 @Valid @RequestBody ResultRequest resultRequest) {
        try {
            ResultResponse r = resultService.update(id, resultRequest);
            return ResponseEntity.ok(r);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            resultService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}