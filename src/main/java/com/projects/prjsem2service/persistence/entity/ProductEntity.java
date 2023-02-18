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
@Table(name = "Product", schema = "dbo", catalog = "projects_sem2")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "brand")
    private String brand;
    @Basic
    @Column(name = "price")
    private Double price;
    @Basic
    @Column(name = "made_in")
    private String madeIn;
    @Basic
    @Column(name = "image")
    private String image;
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

    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity cat;

    @OneToMany(mappedBy="product")
    private List<ConsignmentDetailsEntity> consignmentDetailsEntities = new ArrayList<>();

}
