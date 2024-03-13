package co.id.ajarin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.id.ajarin.entity.DiscussionEntity;

@Repository
public interface DiscussionRepository extends JpaRepository<DiscussionEntity, Long>{
    @Query(value = "SELECT COUNT(d.*) FROM discussion d", nativeQuery = true)
    Integer x();
}
