package com.projects.prjsem2service.persistence.repository;

import com.projects.prjsem2service.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,String> {
    List<CategoryEntity> findAllByStatus(String status);
    CategoryEntity findByCode(String code);

    Optional<CategoryEntity> findById(String id);

    boolean existsByCode(String code);
    boolean existsByName(String name);
}
