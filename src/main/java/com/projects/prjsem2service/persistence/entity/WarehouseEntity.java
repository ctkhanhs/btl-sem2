package com.projects.prjsem2service.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Warehouse", schema = "dbo", catalog = "projects_sem2")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "capacity")
    private Integer capacity;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "created_at")
    private Date createdAt;
    @Basic
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name="partner_id")
    private PartnerEntity partner;

    @OneToMany(mappedBy="warehouse")
    private List<ConsignmentEntity> consignmentEntities = new ArrayList<>();

}
