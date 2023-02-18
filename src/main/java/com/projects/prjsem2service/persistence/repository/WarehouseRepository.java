package com.projects.prjsem2service.persistence.repository;

import com.projects.prjsem2service.persistence.entity.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<WarehouseEntity,String> {
    List<WarehouseEntity> findAllByStatus(String status);
    List<WarehouseEntity> findAllByStatusAndType(String status,String type);
    Optional<WarehouseEntity> findById(String id);
    WarehouseEntity findByCode(String code);
    boolean existsByName(String name);
    boolean existsByCode(String code);

}
