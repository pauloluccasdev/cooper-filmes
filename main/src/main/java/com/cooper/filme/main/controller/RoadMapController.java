package com.cooper.filme.main.controller;

import com.cooper.filme.main.dto.request.RoadMapRequest;
import com.cooper.filme.main.dto.response.RoadMapResponse;
import com.cooper.filme.main.dto.response.RoadMapResponseOpen;
import com.cooper.filme.main.mapper.RoadMapMapper;
import com.cooper.filme.main.service.RoadMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/road-map")
public class RoadMapController {

    @Autowired
    private RoadMapService roadMapService;

    @Autowired
    private RoadMapMapper roadMapMapper;

    @PostMapping("/send")
    public ResponseEntity<RoadMapResponse> sendRoadMap(@ModelAttribute @Validated RoadMapRequest roadMapRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                roadMapMapper.mapToResponseDto(roadMapService.save(roadMapRequest))
        );
    }

    @GetMapping("/status")
    public ResponseEntity<List<RoadMapResponseOpen>> getRoadMapStatus(@RequestParam("clientEmail") @Validated String clientEmail) {

        return ResponseEntity.status(HttpStatus.OK).body(
                roadMapMapper.mapToResponseListOpen(roadMapService.findByClientEmail(clientEmail))
        );
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasRole('REVISOR') or hasRole('ANALISTA') or hasRole('APROVADOR')")
    public ResponseEntity<RoadMapResponse> getRoadMapById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                roadMapMapper.mapToResponseDto(roadMapService.findById(id))
        );
    }

    @PutMapping("/status/{id}")
    @PreAuthorize("hasRole('REVISOR') or hasRole('ANALISTA') or hasRole('APROVADOR')")
    public ResponseEntity<RoadMapResponse> updateStatus(@PathVariable Long id,
                                                @RequestParam @Validated String status) {
        return ResponseEntity.status(HttpStatus.OK).body(
                roadMapMapper.mapToResponseDto(roadMapService.updateStatus(id, status))
        );
    }

    @PostMapping("/approval/{id}")
    @PreAuthorize("hasRole('APROVADOR')")
    public ResponseEntity<RoadMapResponse> vote(@PathVariable Long id,
                                       @RequestParam boolean approved,
                                       @RequestHeader("Authorization") String token) {

        return ResponseEntity.status(HttpStatus.OK).body(
                roadMapMapper.mapToResponseDto(roadMapService.approval(id, approved, token))
        );
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('REVISOR') or hasRole('ANALISTA') or hasRole('APROVADOR')")
    public ResponseEntity<List<RoadMapResponse>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(
                roadMapMapper.mapToResponseList(roadMapService.list())
        );
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadMapResponse>> listRoadmaps(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String date) {

        return ResponseEntity.status(HttpStatus.OK).body(
                roadMapMapper.mapToResponseList(roadMapService.getFilteredRoadmaps(status, email, date))
        );
    }
}
