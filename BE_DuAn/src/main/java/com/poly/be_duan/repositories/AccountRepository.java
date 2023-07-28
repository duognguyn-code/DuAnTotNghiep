package com.poly.be_duan.repositories;

import com.poly.be_duan.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query("SELECT a FROM Account a WHERE a.username LIKE :username AND a.status = 1")
    public Account findByName(String username);

    @Query(value = "select * from accounts where status = 1 and (full_name like %?1% or username like %?1%)",nativeQuery = true)
    Page<Account> findShowSale(String share, Pageable pageable);

    Boolean existsAccountByEmail(String email);
    Boolean existsAccountByUsername(String username);
    Page<Account> findAllByStatus(Integer status, Pageable pageable);


    Page<Account> findByUsernameContaining(String username, Pageable pageable);

    Page<Account> findByUsernameContainingAndStatus(String username, Integer status, Pageable pageable);





    Account findByUsername(String username);
    Account findByEmail(String email);
}
