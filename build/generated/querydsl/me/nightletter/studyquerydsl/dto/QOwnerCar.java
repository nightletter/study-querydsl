package me.nightletter.studyquerydsl.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * me.nightletter.studyquerydsl.dto.QOwnerCar is a Querydsl Projection type for OwnerCar
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QOwnerCar extends ConstructorExpression<OwnerCar> {

    private static final long serialVersionUID = -1343836826L;

    public QOwnerCar(com.querydsl.core.types.Expression<Long> ownerId, com.querydsl.core.types.Expression<String> ownerNm, com.querydsl.core.types.Expression<Long> carCount) {
        super(OwnerCar.class, new Class<?>[]{long.class, String.class, long.class}, ownerId, ownerNm, carCount);
    }

}

