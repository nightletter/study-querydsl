package me.nightletter.studyquerydsl.repository;

import me.nightletter.studyquerydsl.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
