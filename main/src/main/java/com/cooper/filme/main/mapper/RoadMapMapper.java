package com.cooper.filme.main.mapper;

import com.cooper.filme.main.dto.request.RoadMapRequest;
import com.cooper.filme.main.dto.response.RoadMapResponse;
import com.cooper.filme.main.dto.response.RoadMapResponseOpen;
import com.cooper.filme.main.models.RoadMap;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoadMapMapper {

    public RoadMapResponse mapToResponseDto(RoadMap entity) {
        return (entity == null) ? null
                : RoadMapResponse.builder()
                .id(entity.getId())
                .clientName(entity.getClientName())
                .clientEmail(entity.getClientEmail())
                .clientPhone(entity.getClientPhone())
                .fileName(entity.getFileName())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public RoadMapResponseOpen mapToResponseDtoOpen(RoadMap entity) {
        return (entity == null) ? null
                : RoadMapResponseOpen.builder()
                .fileName(entity.getFileName())
                .status(entity.getStatus())
                .build();
    }

    @SneakyThrows
    public RoadMap mapToEntity(RoadMapRequest dto) {
        return RoadMap.builder()
                .clientName(dto.getClientName())
                .clientEmail(dto.getClientEmail())
                .clientPhone(dto.getClientPhone())
                .fileName(dto.getFile().getOriginalFilename())
                .content(dto.getFile().getBytes())
                .status(RoadMap.Status.AGUARDANDO_ANALISE)
                .build();
    }

    public List<RoadMapResponse> mapToResponseList(List<RoadMap> entities) {
        return entities.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public List<RoadMapResponseOpen> mapToResponseListOpen(List<RoadMap> entities) {
        return entities.stream()
                .map(this::mapToResponseDtoOpen)
                .collect(Collectors.toList());
    }
}
