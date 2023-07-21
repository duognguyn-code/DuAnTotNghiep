package com.poly.be_duan.repositories;

import com.poly.be_duan.entities.Account;
import com.poly.be_duan.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    @Query("SELECT p FROM Bill p WHERE p.id = ?1 ")
    public List<Bill> getBill(int id);

    @Query("UPDATE Bill set timeReceive= ?1, moneyShip=?2,statusBuy =?3,status=?4 where (id =?5)")
    public Bill UpdateBill(LocalDateTime date, BigDecimal moneyShip, Integer statusBuy, Integer status, Integer id);

    @Query("SELECT b FROM Bill b WHERE b.phoneTake LIKE %?1% and  b.createDate between ?2 and ?3 and b.status = ?4 ")
    public List<Bill> searchByPhoneAndDateAndStatus(String phone, Date date,Date date1,Integer sts);

    @Query("SELECT b FROM Bill b WHERE b.phoneTake LIKE %?1% and  b.createDate between ?2 and ?3")
    public List<Bill> searchByPhoneAndDate(String phone, Date date,Date date1);

    @Query("SELECT b FROM Bill b WHERE b.phoneTake LIKE %?1% and b.status = ?2 ")
    public List<Bill> searchByPhoneAndStatus(String phone,Integer sts);

//    @Query("SELECT b FROM Bill b WHERE b.phoneTake LIKE %?1% ")
//    public List<Bill> searchByPhone(String phone);

    @Query("update Bill set status= ?1 where id= ?2")
    public Bill UpdateStatus(Integer status, Integer id);

    Optional<Bill> findBillById(Integer id);
//    @Query("SELECT b FROM Bill b WHERE b.createDate between ?1 and  ?2")
//    public List<Bill> searchByDate(Date date,Date date1);
//    @Query("update Bill b set b.totalMoney = ?1 where b.id =?2")
//    public Bill UpdateTotalMoney(BigDecimal money, Integer id);\

    List<Bill> findAllByAccount(Account account);
}
