package com.cooper.filme.main.service;

import com.cooper.filme.main.dto.request.RoadMapRequest;
import com.cooper.filme.main.models.RoadMap;

import java.util.List;

public interface RoadMapService {

    RoadMap save(RoadMapRequest roadMap);

    List<RoadMap> findByClientEmail(String clientEmail);

    RoadMap findById(Long id);

    RoadMap updateStatus(Long roadMapId, String status);

    RoadMap approval(Long roadMapId, boolean approved, String token);

    List<RoadMap> list();

    List<RoadMap> getFilteredRoadmaps(String status, String email, String date);
}
