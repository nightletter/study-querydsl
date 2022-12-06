package me.nightletter.studyquerydsl.repository;

import me.nightletter.studyquerydsl.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
