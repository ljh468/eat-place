package com.eat.eatplace.repository;

import com.eat.eatplace.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // 사용자 ID 확인
    Optional<Account> findByUserId(String userId);

    Optional<Account> findOneWithAuthoritiesByUserId(String userId);
}
