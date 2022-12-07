package me.nightletter.studyquerydsl.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import me.nightletter.studyquerydsl.domain.Car;
import me.nightletter.studyquerydsl.domain.Owner;
import me.nightletter.studyquerydsl.dto.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static me.nightletter.studyquerydsl.domain.QCar.car;
import static me.nightletter.studyquerydsl.domain.QOwner.owner;

@Component
public class Query {

    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;

    public Query(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Owner> findAll() {
        return queryFactory
                .selectFrom(owner)
                .fetch();
    }

    public List<OwnerCar> selectSubQuery() {
        return queryFactory
                .select(new QOwnerCar(
                        owner.ownerId,
                        owner.ownerNm,
                        ExpressionUtils.as(
                        JPAExpressions
                                .select(car.carId.count())
                                .from(car)
                                .where(car.owner.ownerId.eq(owner.ownerId)), "as"
                )))
                .from(owner)
                .fetch();
    }

    public List<Owner> whereSubQuery() {

        return queryFactory
                .selectFrom(owner)
                .where(owner.ownerId.in(
                        JPAExpressions
                                .select(car.owner.ownerId)
                                .from(car)))
                .fetch();
    }

    public List<Long> orderByCase() {
        NumberExpression<Integer> caseBuilder = new CaseBuilder()
                .when(car.carId.eq(1L)).then(2)
                .when(car.carId.eq(2L)).then(1)
                .otherwise(0);

        return queryFactory
                .select(car.carId)
                .from(car)
                .orderBy(caseBuilder.asc())
                .fetch();
    }

    public Boolean exist(Long carId) {
        Long fetchOne = queryFactory
                .select(car.carId.count())
                .from(car)
                .where(car.carId.eq(carId))
//                .limit(1) 있어도 되고 없어도 됨
                .fetchOne();

        return fetchOne != null;
    }

    public List<Car> multiIn(List<MultiInRequest> requests) {
        return queryFactory
                .selectFrom(car)
                .where(Expressions.list(car.carId, car.carNm).in(searchCarIn(requests)))
                .fetch();
    }
    public OwnerResponse paramIdNotinProjection(Long ownerId) {
        return queryFactory
                .select(new QOwnerResponse(
                        Expressions.asNumber(ownerId),
                        owner.ownerNm
                ))
                .from(owner)
                .where(owner.ownerId.eq(ownerId))
                .fetchOne();
    }


    // 출처 : https://programmingnote.tistory.com/87
    private Expression[] searchCarIn(List<MultiInRequest> requests) {

        List<Expression> tuples = new ArrayList<>();

        for(MultiInRequest item : requests) {
            tuples.add(Expressions.template(Object.class, "(({0}, {1}))", item.getCarId(), item.getCarNm()));
        }
        return tuples.toArray(new Expression[0]);
    }
}
