package me.nightletter.studyquerydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class OwnerCar {
    private Long ownerId;
    private String ownerNm;

    private Long carCount;

    @QueryProjection
    public OwnerCar(Long ownerId, String ownerNm, Long carCount) {
        this.ownerId = ownerId;
        this.ownerNm = ownerNm;
        this.carCount = carCount;
    }
}
