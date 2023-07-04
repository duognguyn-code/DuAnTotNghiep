package com.poly.be_duan.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.poly.be_duan.dto.*;
import com.poly.be_duan.entities.Account;
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
//    @Override
//    public AccountResponDTO save(AccountRequestDTO entity) {
//        try {
//            Account accountCheck = findByUsername(entity.getUsername());
//            if (accountCheck != null && accountCheck.getUsername().equals(entity.getUsername())) {
//                return null;
//            } else {
//                Role role = roleRepository.findById(entity.getRole()).orElse(null);
//                Date date = new Date();
//                Account account = new Account();
//                account.setUsername(entity.getUsername());
//                account.setFullName(entity.getFullName());
//                account.setPassword(HashUtil.hash(entity.getPassword()));
//                account.setEmail(entity.getEmail());
//                account.setSex(entity.getGender());
//                account.setPhone(entity.getPhone());
//                account.setStatus(entity.getStatus());
//                account.setRole(role);
//                account.setCreateDate(date);
//                if (accountRequestDTO.getImage() == null){
//                    account.setImage("https://res.cloudinary.com/dcll6yp9s/image/upload/v1669087979/kbasp5qdf76f3j02mebr.png");
//                }else {
//                    Map r = this.cloud.uploader().upload(accountRequestDTO.getImage().getBytes(),
//                            ObjectUtils.asMap(
//                                    "cloud_name", "dcll6yp9s",
//                                    "api_key", "916219768485447",
//                                    "api_secret", "zUlI7pdWryWsQ66Lrc7yCZW0Xxg",
//                                    "secure", true,
//                                    "folders", "c202a2cae1893315d8bccb24fd1e34b816"
//                            ));
//                    account.setImage(r.get("secure_url").toString());
//                }
//
//                Account accountSave = repository.save(account);
//                AccountResponDTO accountResponDTO = modelMapper.map(accountSave, AccountResponDTO.class);
//                accountResponDTO.setRole(accountSave.getRole().getIdRole());
//                return accountResponDTO;
//            }
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//            return null;
//        }
//    }

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
        return null;
    }

    @Override
    public Account findByUsername(String name) {
        return null;
    }

    @Override
    public String setAddressDefault(Integer id) {
        return null;
    }

    @Override
    public AddressDTO getAddress() {
        return null;
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
