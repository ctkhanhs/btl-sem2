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
@Table(name = "Consignment", schema = "dbo", catalog = "projects_sem2")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsignmentEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "code")
    private String code;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "import_total_amount")
    private Double importTotal;

    @Column(name = "export_total_amount")
    private Double exportTotal;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name="warehouse_id")
    private WarehouseEntity warehouse;

    @OneToMany(mappedBy="consignment")
    private List<ConsignmentDetailsEntity> consignmentDetailsEntities = new ArrayList<>();

    @OneToMany(mappedBy="recieptConsignment")
    private List<ReceiptEntity> receiptEntities = new ArrayList<>();
}
