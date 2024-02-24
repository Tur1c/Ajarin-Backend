package co.id.ajarin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.ajarin.entity.StudentDiscEntity;
import co.id.ajarin.entity.composite.StudentDiscKey;

@Repository
public interface StudentDiscRepository extends JpaRepository<StudentDiscEntity, StudentDiscKey> {
    
}
