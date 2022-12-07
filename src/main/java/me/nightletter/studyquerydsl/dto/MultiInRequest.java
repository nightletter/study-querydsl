package me.nightletter.studyquerydsl.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class MultiInRequest {

    private Long carId;
    private String carNm;

    public MultiInRequest(Long carId, String carNm) {
        this.carId = carId;
        this.carNm = carNm;
    }
}
