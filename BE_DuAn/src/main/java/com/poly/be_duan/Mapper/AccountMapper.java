package com.poly.be_duan.Mapper;


import com.poly.be_duan.config.ERole;
import com.poly.be_duan.dto.AccountDTO;
import com.poly.be_duan.entities.Account;
import com.poly.be_duan.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target = "role", ignore = true) // Ignore the direct mapping of the role
    AccountDTO accountToAccountDTO(Account account);
}
