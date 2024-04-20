package co.id.ajarin.repository;

import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.ForumReplyEntity;
import co.id.ajarin.entity.LikesEntity;

@Repository
public interface LikesRepository extends JpaRepository<LikesEntity, Long> {
    // Optional<LikesEntity> findByReplyAndUser(ForumReplyEntity reply, AccountRegisterEntity user);

    @Query(value = "SELECT * FROM LIKES l WHERE l.fr_id = ?1 and l.email LIKE ?2", nativeQuery = true)
    LikesEntity findByFrId(Long fr_id, String email);
}
