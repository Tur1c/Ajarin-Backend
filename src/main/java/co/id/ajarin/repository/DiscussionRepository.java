package co.id.ajarin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.id.ajarin.entity.DiscussionEntity;

public interface DiscussionRepository extends JpaRepository<DiscussionEntity, Long>{
    
}
