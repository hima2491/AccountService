package com.tekarch.AccountServiceMS.Repository;
import com.tekarch.AccountServiceMS.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByUserId(Integer userId);
    List<Account> findByAccountType(String accountType);
    void deleteByAccountType(String accountType);
}
