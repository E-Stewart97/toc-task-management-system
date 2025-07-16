package com.se_devops.toc_task_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @NotBlank(message = "TOC name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @Column(name = "business_code", unique = true, nullable = false, length = 10)
    @NotBlank(message = "Business code is required")
    @Size(max = 10, message = "Business code cannot exceed 10 characters")
    private String businessCode;

    @Column(nullable = false)
    private Boolean active = true;

    @OneToMany(mappedBy = "toc", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks;

    // Constructor
    public Toc(String name, String businessCode, Boolean active) {
        this.name = name;
        this.businessCode = businessCode;
        this.active = active;
    }
}