package com.ainamaani.projects.pms.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    protected Long id;
    @CreationTimestamp
    @Column(name="created_at")
    protected Date createdAt;
    @UpdateTimestamp
    @Column(name="updated_at")
    protected Date updatedAt;
}
