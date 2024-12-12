package com.cooper.filme.main.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "road_map")
public class RoadMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;

    private String clientEmail;

    private String clientPhone;

    @Lob
    private byte[] content;

    private String fileName;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "roadMap", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ApprovalRecord> approvalRecords = new HashSet<>();

    public enum Status {
        AGUARDANDO_ANALISE,
        EM_ANALISE,
        APROVADO,
        REPROVADO
    }

}
