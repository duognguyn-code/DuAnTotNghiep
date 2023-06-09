package com.poly.be_duan.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.poly.be_duan.dto.*;
import com.poly.be_duan.entities.Account;
import com.poly.be_duan.entities.Address;
import com.poly.be_duan.entities.Role;
import com.poly.be_duan.repositories.AccountRepository;
import com.poly.be_duan.repositories.AddressRepository;
import com.poly.be_duan.repositories.RoleRepository;
import com.poly.be_duan.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;

    @Autowired
    private Cloudinary cloud;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    public AccountServiceImpl(AccountRepository repository, AddressRepository addressRepository, ModelMapper modelMapper) {
        this.repository = repository;
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountResponDTO save(AccountRequestDTO entity) {
        return null;
    }

    @Override
    public Account save(Account account) {
        return null;
    }

    @Override
    public List<Account> save(List<Account> entities) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public Optional<Account> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<AccountResponDTO> findAll() {
        return null;
    }

    @Override
    public Page<AccountResponDTO> findAll(String search, Integer role, Integer status, Pageable pageable) {
        return null;
    }

    @Override
    public AccountResponDTO update(AccountRequestDTO accountDTO, String username) {
        return null;
    }

    @Override
    public Account findByName(String username) {
        return repository.findByName(username);
    }

    @Override
    public Account findByUsername(String name) {
        return repository.findByName(name);
    }

    @Override
    public String setAddressDefault(Integer id) {
        Address address = addressRepository.findById(id).orElse(null);
        Account account = repository.findByName("Dương");
        account.setAddress_id(address);
        repository.save(account);
        return "OK";
    }

    @Override
    public AddressDTO getAddress() {
        Account account = repository.findByName("Dương");
        Address address = addressRepository.findById(account.getAddress_id().getIdAddress()).orElse(null);
        AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);
        return addressDTO;
    }

    @Override
    public AccountDTO updateAccountActive(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public Page<Account> getByPage(int pageNumber, int maxRecord, String share) {
        return null;
    }

    @Override
    public AccountResponDTO updateImage(AccountRequestDTO accountRequestDTO) {
        return null;
    }

    @Override
    public Boolean updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        return null;
    }

    @Override
    public Role getRoleByUserName(String userName) {
        return null;
    }
}
