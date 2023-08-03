package com.poly.be_duan.repositories;

import com.poly.be_duan.entities.Account;
import com.poly.be_duan.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query(value = "select * from author\n" +
            "inner join account\n" +
            "on author.username = account.username\n" +
            "inner join role\n" +
            "on role.id_role = author.id_role",nativeQuery = true)
    public List<Author> findAll();

    @Query("SELECT a FROM Author a WHERE a.account.username LIKE :username")
    public Author findByName(@Param("username") String username);
}
