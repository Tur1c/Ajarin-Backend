package co.id.ajarin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.id.ajarin.entity.PrivateDiscEntity;

@Repository
public interface PrivateDiscRepository extends JpaRepository<PrivateDiscEntity, Long> {
    
    @Modifying
    @Query(value = "UPDATE PRIVATE_DISC SET STATUS = :status WHERE private_id = :privateid", nativeQuery = true)
    int setStatus(@Param("privateid") Long privateid, @Param("status") String status);

    @Query(value = "SELECT * FROM PRIVATE_DISC WHERE user_id = :userid AND STATUS = 'Accepted'", nativeQuery = true)
    List<PrivateDiscEntity> getPrivateDisc(Long userid);
}
