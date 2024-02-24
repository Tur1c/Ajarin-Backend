package co.id.ajarin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.ajarin.entity.ForumEntity;

@Repository
public interface ForumRepository extends JpaRepository<ForumEntity, Long> {
    
}
