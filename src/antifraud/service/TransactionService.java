package antifraud.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TransactionService {

    public ResponseEntity<Object> allowingTransactions(Map<String, Long> longMap) {
        Long amount = longMap.get("amount");

        if (amount == null || amount <= 0) {
            return ResponseEntity.badRequest().build();
        } else if (amount > 1500) {
            return ResponseEntity.ok().body(
                    new ConcurrentHashMap<>(Map.of("result", "PROHIBITED")));
        } else if (amount <= 200) {
            return ResponseEntity.ok().body(
                    new ConcurrentHashMap<>(Map.of("result", "ALLOWED")));
        } else {
            return ResponseEntity.ok().body(
                    new ConcurrentHashMap<>(Map.of("result", "MANUAL_PROCESSING")));
        }
    }

}
