package com.banking.accounts.mapper;

import com.banking.accounts.dto.AccountDTO;
import com.banking.accounts.entity.Account;

public class AccountMapper {

    //we can use 2 libraries for the conversion of the dto to entity and viceversa
    // libraries are modelmapper and MapStruct

    public static AccountDTO mapToAccountDTO(Account accounts, AccountDTO accountsDTO){
        accountsDTO.setAccountNumber(accounts.getAccountNumber());
        accountsDTO.setAccountType(accounts.getAccountType());
        accountsDTO.setBranchAddress(accounts.getBranchAddress());
        return accountsDTO;
    }

    public static Account mapToAccount(AccountDTO accountsDTO, Account accounts){
        accounts.setAccountNumber(accountsDTO.getAccountNumber());
        accounts.setAccountType(accountsDTO.getAccountType());
        accounts.setBranchAddress(accountsDTO.getBranchAddress());
        return accounts;
    }
}
