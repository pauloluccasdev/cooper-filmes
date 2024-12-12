package com.cooper.filme.main.dto.base;

import com.cooper.filme.main.models.RoadMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RoadMapDto {

    private String clientName;

    private String clientEmail;

    private String clientPhone;

    private MultipartFile file;

    private String status;
}
