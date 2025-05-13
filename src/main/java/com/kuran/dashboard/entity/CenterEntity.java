package com.kuran.dashboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "Centers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CenterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String centerId;
    private String name;

    @ManyToOne
    @JoinColumn(name = "institute_id")
    private InstituteEntity institute;

    @OneToOne(mappedBy = "center", cascade = CascadeType.ALL)
    private SessionEntity sessionEntity;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;
    @CreationTimestamp
    private Timestamp updatedAt;
}
