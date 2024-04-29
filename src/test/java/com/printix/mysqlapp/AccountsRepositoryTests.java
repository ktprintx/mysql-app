package com.printix.mysqlapp;

import com.printix.mysqlapp.entity.Account;
import com.printix.mysqlapp.repository.AccountRepository;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@Disabled("Disabled until bug #123 is fixed")
@DataJpaTest
@ActiveProfiles("test")
public class  AccountsRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testAccountsWithAccountHolderNameKofax() {
        // Insert a test record
        Account account = new Account();
        account.setAccountHolderName("kofax");
        account.setBalance(1000.00); // Assume there's a balance field
        entityManager.persist(account);
        entityManager.flush();

        // Test if the record exists
//        Optional<Account> foundAccount = accountRepository.findByAccountHolderName("kofax");
//        assertThat(foundAccount.isPresent()).isTrue();  // Check that an account was found

//        String accountName = foundAccount.get().getAccountHolderName();
//        assertThat(accountName).isEqualTo("kofax");
    }
}
