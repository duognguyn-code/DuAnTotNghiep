package com.poly.be_duan.Mapper;


import com.poly.be_duan.dto.AccountDTO;
import com.poly.be_duan.entities.Account;
import com.poly.be_duan.entities.Author;
import com.poly.be_duan.entities.Role;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2023-06-23T14:11:59+0700",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    private String getAccountRoleName(Account account) {
        if (account == null || account.getAuthorList() == null || account.getAuthorList().isEmpty()) {
            return null;
        }

        // Assuming that an account can have only one author
        Author author = account.getAuthorList().get(0);
        if (author == null || author.getRole() == null) {
            return null;
        }

        return String.valueOf(author.getRole().getName());
    }

    @Override
    public AccountDTO accountToAccountDTO(Account account) {
        if (account == null){
            return null;
        }
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUsername(account.getUsername());
        accountDTO.setRole(getAccountRoleName(account));
        accountDTO.setEmail(account.getEmail());
        accountDTO.setFullName(account.getFullName());
        accountDTO.setPhone(account.getPhone());
        accountDTO.setGender(account.getSex());
        accountDTO.setDate(account.getDate());
        accountDTO.setPassword(account.getPassword());

        return  accountDTO;
    }
}
