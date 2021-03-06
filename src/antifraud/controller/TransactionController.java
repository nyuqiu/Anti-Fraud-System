package antifraud.controller;

import antifraud.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping(value = "/api/antifraud/transaction", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> allowingTransactions(@RequestBody Map<String, Long> longMap) {
        return transactionService.allowingTransactions(longMap);
    }

}
