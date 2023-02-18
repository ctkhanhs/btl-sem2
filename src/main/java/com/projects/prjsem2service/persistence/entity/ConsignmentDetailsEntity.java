package com.projects.prjsem2service.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Consignment_Details", schema = "dbo", catalog = "projects_sem2")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsignmentDetailsEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "number_of_boxes")
    private Integer numberOfBoxes;

    @Column(name = "number_products_in_box")
    private Integer numberProductsInBox;

    @Column(name = "import_price")
    private Double importPrice;

    @Column(name = "nsx")
    private String nsx;

    @Column(name = "hsd")
    private String hsd;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name="consignment_id")
    private ConsignmentEntity consignment;

}
