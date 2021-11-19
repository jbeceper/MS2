package com.misiontic.account_ms.controllers;
import com.misiontic.account_ms.exceptions.AccountNotFoundException;
import com.misiontic.account_ms.models.Account;
import com.misiontic.account_ms.repositories.AccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AccountController {
    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping("/accounts")
    Account newAccount(@RequestBody Account account){
        return accountRepository.save(account);
    }

    @GetMapping("/accounts/{username}")
    Account getAccount(@PathVariable String username){
        return accountRepository.findById(username)
                .orElseThrow(() -> new AccountNotFoundException("No se encontró la cuenta"));

    }

    @DeleteMapping("/accounts/{username}")
    void deleteAccount(@PathVariable String username){
        accountRepository.deleteById(username);
    }

}
