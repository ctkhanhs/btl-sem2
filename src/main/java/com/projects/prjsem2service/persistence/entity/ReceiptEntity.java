package com.projects.prjsem2service.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Receipt", schema = "dbo", catalog = "projects_sem2")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptEntity {

    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "warehouse_name_sent")
    private String warehouseNameSent;
    @Basic
    @Column(name = "warehouse_address_sent")
    private String warehouseAddressSent;
    @Basic
    @Column(name = "warehouse_name_receive")
    private String warehouseNameReceive;
    @Basic
    @Column(name = "warehouse_address_receive")
    private String warehouseAddressReceive;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name="consignment_id")
    private ConsignmentEntity recieptConsignment;

}
