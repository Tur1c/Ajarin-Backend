package co.id.ajarin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.ajarin.entity.DiscussionEntity;

@Repository
public interface DiscussionRepository extends JpaRepository<DiscussionEntity, Long>{

}
