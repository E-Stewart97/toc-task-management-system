package com.se_devops.toc_task_management_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tocs")
public class Toc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "business_code", unique = true, nullable = false, length = 10)
    private String businessCode;

    @Column(nullable = false)
    private Boolean active = true;

    // Constructor with fields (excluding id)
    public Toc(String name, String businessCode, Boolean active) {
        this.name = name;
        this.businessCode = businessCode;
        this.active = active;
    }
}
