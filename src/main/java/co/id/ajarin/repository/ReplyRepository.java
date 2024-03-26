package co.id.ajarin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.ajarin.entity.ForumReplyEntity;


@Repository
public interface ReplyRepository extends JpaRepository<ForumReplyEntity, Long> {
    // ForumReplyEntity findByFr_id(Long fr_id);

    
}
