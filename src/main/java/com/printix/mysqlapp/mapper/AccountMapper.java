package com.printix.mysqlapp.mapper;

import com.printix.mysqlapp.dto.AccountDto;
import com.printix.mysqlapp.entity.Account;
import java.util.UUID;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance(),
                accountDto.getAccountId()
        );
        return account;
    }
    public static AccountDto mapToAccountDto(Account account){

        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance(),
                account.getAccountId()
        );
        return accountDto;
    }
}
