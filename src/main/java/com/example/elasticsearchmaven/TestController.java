package com.example.elasticsearchmaven;

import com.sun.xml.internal.ws.api.WSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class TestController {
    @Autowired
    ESClientConnector connector;

    @GetMapping("/search")
    public ResponseEntity<List<Object>> hello(@RequestParam String keyword) {
        List<Object> result;
        try {
            result = connector.fetchWithMust(keyword);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/today")
    public ResponseEntity<List<Object>> searchToday() {
        List<Object> result;
        try {
            result = connector.fetchToday();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(result);
    }
}
