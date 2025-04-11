package io.github.luizhenriqque18.gateway_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/invoices")
@RestController
public class InvoicesController {

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody String entity) {
        return ResponseEntity.ok("Invoice created successfully");
    }

    @GetMapping()
    public ResponseEntity<List<String>> list() {
        return ResponseEntity.ok(List.of("Invoice 1", "Invoice 23"));
    }
}
