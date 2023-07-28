package com.poly.be_duan.service.impl;

import com.cloudinary.Cloudinary;
import com.poly.be_duan.Mapper.AccountMapper;
import com.poly.be_duan.config.ERole;
import com.poly.be_duan.dto.*;
import com.poly.be_duan.entities.Account;
import com.poly.be_duan.entities.Address;
import com.poly.be_duan.entities.Author;
import com.poly.be_duan.entities.Role;
import com.poly.be_duan.repositories.AccountRepository;
import com.poly.be_duan.repositories.AddressRepository;
import com.poly.be_duan.repositories.AuthorRepository;
import com.poly.be_duan.repositories.RoleRepository;
import com.poly.be_duan.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    private final AccountMapper accountMapper;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private Cloudinary cloud;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public AccountServiceImpl(AccountRepository repository, AccountMapper accountMapper, AddressRepository addressRepository, ModelMapper modelMapper) {
        this.repository = repository;
        this.accountMapper = accountMapper;
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<?> save(AccountRequestDTO entity) {
        if (repository.existsAccountByUsername(entity.getUsername())){
            return  ResponseEntity.badRequest().body("Error: Username is already taken!");
        }
        if(repository.existsAccountByEmail(entity.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }
        Account account = new Account();
        account.setUsername(entity.getUsername());
        account.setEmail(entity.getEmail());
        account.setPassword(entity.getPassword());
        account.setSex(entity.getGender());
        account.setPhone(entity.getPhone());
        account.setFullName(entity.getFullName());
        account = repository.save(account);
        Set<String> strRole = entity.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRole == null){
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() ->new RuntimeException("ErrorL: Role is not found "));
        }else {
            strRole.forEach(role -> {
                switch (role){
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: role is not found"));
                        roles.add(adminRole);
                        break;
                    case "staff":
                        Role staffRole = roleRepository.findByName(ERole.ROLE_STAFF)
                                .orElseThrow(() -> new RuntimeException("Error: role is not found"));
                        roles.add(staffRole);
                        break;
                    case "user":
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: role is not found"));
                        roles.add(userRole);
                        break;
                    default:
                        Role guestRole = roleRepository.findByName(ERole.ROLE_GUEST)
                                .orElseThrow(() -> new RuntimeException(("Error: role not found")));
                        roles.add(guestRole);
                }
            });
        }
        List<Author> authorList = new ArrayList<>();
        for (Role role: roles) {
            Author author = new Author();
            author.setAccount(repository.getById(account.getUsername()));
            author.setRole(role);
            authorList.add(author);
        }
        account.setAuthorList(authorList);
        authorList = authorRepository.saveAll(authorList);
        return ResponseEntity.ok("account tạo thành công");
    }

    @Override
    public Account save(Account account) {
        return repository.save(account);
    }

    @Override
    public List<Account> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Account> save(List<Account> entities) {
        return null;
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
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
        System.out.println(addressDTO);
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

    @Override
    public List<AccountDTO> getAllAccountResponseDTO() {
       List<Account> account = repository.findAll();
       return  account.stream().map(accountMapper::accountToAccountDTO).collect(Collectors.toList());

  @Override
    public Account findAccountByUsername(String username) {
        return repository.findByUsername(username);
    }
}
