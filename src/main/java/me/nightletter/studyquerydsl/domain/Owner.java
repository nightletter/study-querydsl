package me.nightletter.studyquerydsl.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Owner {

    @Id
    @Column(name = "OWNER_ID")
    private Long ownerId;

    @Column(name = "NM")
    private String ownerNm;

    @OneToMany(mappedBy = "owner")
    private List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        this.cars.add(car);
        car.setOwner(this);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerId=" + ownerId +
                ", ownerNm='" + ownerNm + '\'' +
                '}';
    }
}
