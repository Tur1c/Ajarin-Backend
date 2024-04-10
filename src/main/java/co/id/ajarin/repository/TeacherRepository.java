package co.id.ajarin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.id.ajarin.entity.TeacherEntity;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    
    @Query(value = "SELECT * FROM TEACHER WHERE user_id = :user", nativeQuery = true)
    TeacherEntity getTeacherById(@Param("user") Long user);
}
