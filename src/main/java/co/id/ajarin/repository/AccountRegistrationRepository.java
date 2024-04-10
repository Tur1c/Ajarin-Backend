package co.id.ajarin.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.id.ajarin.entity.AccountRegisterEntity;

@Repository
public interface AccountRegistrationRepository extends JpaRepository<AccountRegisterEntity, Long> {
    
    AccountRegisterEntity findByEmail(String email);

    @Modifying
    @Query(value = "UPDATE AJARIN_USER SET PROFILE_PIC = :profilepic WHERE email = :email", nativeQuery = true)
    int saveProfilepic(@Param("profilepic") String profilepic, @Param("email") String email);


}
