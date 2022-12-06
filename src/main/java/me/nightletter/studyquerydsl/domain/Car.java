package me.nightletter.studyquerydsl.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Car {

    @Id
    @Column(name = "CAR_ID")
    private Long carId;

    @Column(name = "NM")
    private String carNm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID")
    private Owner owner;

    public Car(Long carId, String carNm) {
        this.carId = carId;
        this.carNm = carNm;
    }
}
