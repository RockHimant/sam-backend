package com.sam.search.controller;

import com.sam.search.ESClientConnector;
import com.sam.search.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class TestController {
    @Autowired
    ESClientConnector connector;

    @GetMapping("/search")
    public ResponseEntity<List<Result>> hello(@RequestParam String keyword) {
        List<Result> result;
        try {
            result = connector.fetchWithMust(keyword);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/today")
    public ResponseEntity<List<Result>> searchToday() {
        List<Result> result;
        try {
            result = connector.fetchToday();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/exact-search")
    public ResponseEntity<List<Result>> searchExact(@RequestParam final String keyword) {
        List<Result> result;
        try {
            result = connector.searchExact(keyword);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(result);
    }
}
