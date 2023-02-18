package com.projects.prjsem2service.persistence.repository;

import com.projects.prjsem2service.persistence.entity.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<ReceiptEntity,String> {
    List<ReceiptEntity> findAllByWarehouseNameReceive(String name);
    List<ReceiptEntity> findAllByWarehouseNameSent(String name);


}
