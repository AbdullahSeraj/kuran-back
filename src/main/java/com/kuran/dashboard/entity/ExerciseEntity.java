package com.kuran.dashboard.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "Exercises")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String exerciseId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private InstituteEntity institute;

    @Column(nullable = false)
    private boolean archived = false;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;
    @CreationTimestamp
    private Timestamp updatedAt;
}
