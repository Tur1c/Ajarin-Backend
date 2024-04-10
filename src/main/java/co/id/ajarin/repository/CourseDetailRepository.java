package co.id.ajarin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.ajarin.entity.CourseDetailEntity;

@Repository
public interface CourseDetailRepository extends JpaRepository<CourseDetailEntity, Long>{
    
}
