package com.cooper.filme.main.dto.request;

import com.cooper.filme.main.dto.base.RoadMapDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RoadMapRequest extends RoadMapDto {

}
