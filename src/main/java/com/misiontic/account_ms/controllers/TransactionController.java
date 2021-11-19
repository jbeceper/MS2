package com.misiontic.account_ms.controllers;

import com.misiontic.account_ms.models.Transaction;
import com.misiontic.account_ms.repositories.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TransactionController {
    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/transaction/{id}")
    public Optional<Transaction> getTransaction(@PathVariable String id){
        return transactionRepository.findById(id);
    }

    @GetMapping("/transactions")
    List<Transaction> listTransactions(){
        return transactionRepository.findAll();
    }

    @PostMapping("/transactions")
    Transaction newTransaction(@RequestBody Transaction transaction){
        return transactionRepository.save(transaction);
    }

}
