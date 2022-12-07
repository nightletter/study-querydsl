package me.nightletter.studyquerydsl;

import me.nightletter.studyquerydsl.domain.Car;
import me.nightletter.studyquerydsl.domain.Owner;
import me.nightletter.studyquerydsl.dto.MultiInRequest;
import me.nightletter.studyquerydsl.dto.OwnerCar;
import me.nightletter.studyquerydsl.dto.OwnerResponse;
import me.nightletter.studyquerydsl.repository.CarRepository;
import me.nightletter.studyquerydsl.repository.OwnerRepository;
import me.nightletter.studyquerydsl.repository.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StudyQuerydslApplicationTests {

	@Autowired
	Query query;

	@Autowired
	OwnerRepository ownerRepository;

	@Autowired
	CarRepository carRepository;

	@Test
	void findAll() {
		for (Owner owner : query.findAll()) {
			System.out.println(owner);
		}
	}

	@Test
	void selectSubQuery() {
		for (OwnerCar ownerCar : query.selectSubQuery()) {
			System.out.println(ownerCar);
		}
	}

	@Test
	void whereSubQuery() {
		for (Owner owner : query.whereSubQuery()) {
			System.out.println(owner);
		}
	}

	@Test
	void orderByCase() {
		List<Long> result = query.orderByCase();
		result.forEach(o -> System.out.println(o));

		assertEquals(result.get(0), 2L);
		assertEquals(result.get(1), 1L);
	}

	@Test
	void exist() {
		assertEquals(query.exist(1L), 1L);
	}

	@Test
	void multiIn() {
		List<MultiInRequest> multiInRequest = new ArrayList<>();
		multiInRequest.add(new MultiInRequest(1L,"포르쉐"));
		multiInRequest.add(new MultiInRequest(2L,"마세라티"));


		for (Car o : query.multiIn(multiInRequest)) {
			System.out.println(o);
		}
	}

	@Test
	void paramIdNotinProjection() {
		OwnerResponse ownerResponse = query.paramIdNotinProjection(1L);
		System.out.println(ownerResponse);
	}

	@Test
	@Transactional
	@Rollback(value = false)
	void bulkInsert() {
		Owner owner = new Owner();
		owner.setOwnerId(1L);
		owner.setOwnerNm("KIM");

		Car car1 = new Car();
		car1.setCarId(1L);
		car1.setCarNm("포르쉐");

		Car car2 = new Car();
		car2.setCarId(2L);
		car2.setCarNm("마세라티");

		owner.addCar(car1);
		owner.addCar(car2);

		ownerRepository.save(owner);

		carRepository.save(car1);
		carRepository.save(car2);
	}
}
