package com.kuran.dashboard.service.Impl;

import com.kuran.dashboard.entity.CenterEntity;
import com.kuran.dashboard.entity.InstituteEntity;
import com.kuran.dashboard.entity.SessionEntity;
import com.kuran.dashboard.io.CenterReq;
import com.kuran.dashboard.io.CenterRes;
import com.kuran.dashboard.repository.CenterRepo;
import com.kuran.dashboard.repository.InstituteRepo;
import com.kuran.dashboard.repository.SessionRepo;
import com.kuran.dashboard.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {
    private final CenterRepo centerRepo;
    private final InstituteRepo instituteRepo;
    private final SessionRepo sessionRepo;

    @Override
    public List<CenterRes> all() {
        return centerRepo.findAll().stream().map(c -> convertToRes(c)).collect(Collectors.toList());
    }

    @Override
    public CenterRes save(CenterReq req) {
        CenterEntity newCenter = convertToEntity(req);
        newCenter = centerRepo.save(newCenter);
        return convertToRes(newCenter);
    }

    @Override
    public void delete(String centerId) {
        CenterEntity existingCenter = centerRepo.findByCenterId(centerId)
                .orElseThrow(() -> new RuntimeException("Center not found: " + centerId));

        if (existingCenter.getSessionEntity() != null) {
            throw new IllegalStateException("Cannot delete center: it is linked to a session.");
        }

        centerRepo.delete(existingCenter);
    }

    private CenterEntity convertToEntity(CenterReq req) {
        InstituteEntity instituteEntity = instituteRepo.findByInstituteId(req.getInstituteId()).get();
        return CenterEntity.builder()
                .centerId(UUID.randomUUID().toString())
                .name(req.getName())
                .institute(instituteEntity)
                .build();
    }

    private CenterRes convertToRes(CenterEntity entity) {
        return CenterRes.builder()
                .centerId(entity.getCenterId())
                .name(entity.getName())
                .instituteId(entity.getInstitute() != null ? entity.getInstitute().getInstituteId() : null)
                .instituteName(entity.getInstitute() != null ? entity.getInstitute().getName() : null)
                .sessionName(entity.getSessionEntity() != null ? entity.getSessionEntity().getName() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

}
