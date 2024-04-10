package co.id.ajarin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.id.ajarin.entity.StudentDiscEntity;
import co.id.ajarin.entity.composite.StudentDiscKey;

@Repository
public interface StudentDiscRepository extends JpaRepository<StudentDiscEntity, StudentDiscKey> {
    
    @Query(value = "SELECT COUNT(*) FROM STUDENT_DISC WHERE DISC_ID = :discId", nativeQuery = true)
    Long getCountDisc(@Param("discId") Long discId);
}
