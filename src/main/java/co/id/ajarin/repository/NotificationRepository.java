package co.id.ajarin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.id.ajarin.entity.NotificationEntity;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long>{
    
    @Query(value = "SELECT * FROM NOTIFICATION WHERE user_id = :id", nativeQuery = true)
    List<NotificationEntity> getNotifData(Long id);
}
