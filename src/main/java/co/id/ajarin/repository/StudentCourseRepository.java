package co.id.ajarin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.id.ajarin.entity.StudentCourseEntity;
import co.id.ajarin.entity.composite.StudentCourseKey;

public interface StudentCourseRepository extends JpaRepository<StudentCourseEntity, StudentCourseKey> {
    
}
