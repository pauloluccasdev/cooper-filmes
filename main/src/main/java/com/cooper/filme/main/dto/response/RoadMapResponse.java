package com.cooper.filme.main.dto.response;

import com.cooper.filme.main.models.RoadMap;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@JsonPropertyOrder({"id", "clientEmail", "clientName", "clientPhone", "fileName", "status", "createdAt"  })
public class RoadMapResponse {
    private Long id;

    private String clientEmail;

    private String clientName;

    private String clientPhone;

    private String fileName;

    private RoadMap.Status status;

    private LocalDateTime createdAt;

}
