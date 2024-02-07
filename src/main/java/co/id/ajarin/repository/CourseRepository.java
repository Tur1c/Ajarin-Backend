package co.id.ajarin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.ajarin.entity.CourseEntity;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity,Long>{
    
}
