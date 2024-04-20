package co.id.ajarin.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.id.ajarin.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {

    @Query(value = "SELECT * FROM CATEGORY WHERE category_name = :categoryName", nativeQuery = true)
    CategoryEntity findByName(String categoryName);
}
