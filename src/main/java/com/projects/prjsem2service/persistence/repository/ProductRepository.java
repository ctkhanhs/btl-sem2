package com.projects.prjsem2service.persistence.repository;

import com.projects.prjsem2service.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,String> {
    List<ProductEntity> findAllByStatus(String status);
    ProductEntity findByCode(String code);
    Optional<ProductEntity> findById(String id);
    boolean existsByName(String name);
    boolean existsByCode(String code);
}
