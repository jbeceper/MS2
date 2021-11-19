package com.misiontic.account_ms.controllers;

import com.misiontic.account_ms.exceptions.AccountNotFoundException;
import com.misiontic.account_ms.models.Account;
import com.misiontic.account_ms.models.Transaction;
import com.misiontic.account_ms.repositories.AccountRepository;
import com.misiontic.account_ms.repositories.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TransactionController {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;


    public TransactionController(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
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

        Account accountDestiny = accountRepository.findById(transaction.getUsernameDestiny()).orElse(null);
        Account accountOrigin = accountRepository.findById(transaction.getUsernameOrigin()).orElse(null);

        if(accountDestiny == null || accountOrigin == null){
            throw new AccountNotFoundException("No se encontro la cuenta destino o cuenta origen");
        }

        accountOrigin.setBalance(accountOrigin.getBalance() - transaction.getValue());
        accountOrigin.setBalance(accountDestiny.getBalance() + transaction.getValue());

        accountRepository.save(accountDestiny);
        accountRepository.save(accountOrigin);
        return transactionRepository.save(transaction);
    }

}
