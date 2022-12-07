package me.nightletter.studyquerydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;

@Data
public class OwnerResponse {

    private Long ownerId;
    private String ownerNm;

    @QueryProjection
    public OwnerResponse(Long ownerId, String ownerNm) {
        this.ownerId = ownerId;
        this.ownerNm = ownerNm;
    }
}
