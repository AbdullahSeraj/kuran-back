package com.kuran.dashboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Institutes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstituteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String instituteId;
    private String name;
    private String address;

    @OneToMany(mappedBy = "institute", cascade = CascadeType.ALL)
    private List<CenterEntity> centers;

    @OneToMany(mappedBy = "institute", cascade = CascadeType.ALL)
    private List<LessonEntity> lessons;

    @OneToMany(mappedBy = "institute", cascade = CascadeType.ALL)
    private List<ExerciseEntity> exercises;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;
    @CreationTimestamp
    private Timestamp updatedAt;
}
