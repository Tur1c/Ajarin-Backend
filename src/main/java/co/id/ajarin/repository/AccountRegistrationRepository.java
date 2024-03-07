package co.id.ajarin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.id.ajarin.entity.AccountRegisterEntity;

@Repository
public interface AccountRegistrationRepository extends JpaRepository<AccountRegisterEntity, Long> {
    
    AccountRegisterEntity findByEmail(String email);

}
