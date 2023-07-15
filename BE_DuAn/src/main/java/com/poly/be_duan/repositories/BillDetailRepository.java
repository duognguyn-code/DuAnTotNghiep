package com.poly.be_duan.repositories;

import com.poly.be_duan.entities.Bill_detail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<Bill_detail,Integer> {

    @Query("SELECT p FROM Bill_detail p WHERE p.bill.id = ?1 ")
    public List<Bill_detail> getBill_detail(int id);

}
