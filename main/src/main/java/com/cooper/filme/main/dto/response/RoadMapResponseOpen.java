package com.cooper.filme.main.dto.response;

import com.cooper.filme.main.models.RoadMap;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@JsonPropertyOrder({ "fileName", "status" })
public class RoadMapResponseOpen {

    private String fileName;

    private RoadMap.Status status;

}
