package com.misiontic.account_ms.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.misiontic.account_ms.models.Account;

public interface AccountRepository extends MongoRepository<Account, String> {

}
