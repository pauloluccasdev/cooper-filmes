package com.cooper.filme.main.service.impl;

import com.cooper.filme.main.dto.request.RoadMapRequest;
import com.cooper.filme.main.dto.response.RoadMapResponse;
import com.cooper.filme.main.mapper.RoadMapMapper;
import com.cooper.filme.main.models.ApprovalRecord;
import com.cooper.filme.main.models.RoadMap;
import com.cooper.filme.main.repository.ApprovalRecordRepository;
import com.cooper.filme.main.repository.RoadMapRepository;
import com.cooper.filme.main.repository.UserRepository;
import com.cooper.filme.main.service.RoadMapService;
import com.cooper.filme.main.utils.JwtTokenProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoadMapServiceImpl implements RoadMapService {

    @Autowired
    private RoadMapRepository roadMapRepository;

    @Autowired
    private RoadMapMapper roadMapMapper;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApprovalRecordRepository approvalRecordRepository;


    @Override
    @Transactional
    public RoadMap save(RoadMapRequest roadMapRequest) {
        return roadMapRepository.save(roadMapMapper.mapToEntity(roadMapRequest));
    }

    @Override
    @Transactional
    public List<RoadMap> findByClientEmail(String clientEmail) {
        return roadMapRepository.findByClientEmail(clientEmail);
    }


    @Override
    public RoadMap findById(Long id) {
        return roadMapRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Roteiro não encontrado com o ID: " + id));
    }

    @Override
    @Transactional
    public RoadMap updateStatus(Long roadMapId, String status) {

        RoadMap roadMap = findById(roadMapId);

        roadMap.setStatus(RoadMap.Status.valueOf(status));

        return roadMapRepository.save(roadMap);
    }

    @Override
    @Transactional
    public RoadMap approval(Long roadMapId, boolean approved, String token) {

        RoadMap roadMap = findById(roadMapId);

        String email = jwtTokenProvider.getEmailFromToken(token);

        boolean alreadyApproved = roadMap.getApprovalRecords().stream()
                .anyMatch(r -> r.getUser().getEmail().equals(email));

        if (alreadyApproved) {
            throw new RuntimeException("Aprovador já votou nesse roteiro");
        }

        ApprovalRecord approvalRecord = new ApprovalRecord();
        approvalRecord.setRoadMap(roadMap);
        approvalRecord.setUser(userRepository.findByEmail(email));
        approvalRecord.setApproved(approved);

        roadMap.getApprovalRecords().add(approvalRecord);
        approvalRecordRepository.save(approvalRecord);

        return roadMapRepository.save(roadMap);
    }

    @Override
    public List<RoadMap> list() {
        return roadMapRepository.findAll();
    }

    @Override
    public List<RoadMap> getFilteredRoadmaps(String status, String email, String date) {

        LocalDate parsedDate = null;
        if (date != null && !date.isEmpty()) {
            parsedDate = LocalDate.parse(date);
        }


        return roadMapRepository.findByFilters(status, email, parsedDate);
    }
}
