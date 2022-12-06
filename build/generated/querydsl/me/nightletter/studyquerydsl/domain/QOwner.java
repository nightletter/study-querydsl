package me.nightletter.studyquerydsl.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOwner is a Querydsl query type for Owner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOwner extends EntityPathBase<Owner> {

    private static final long serialVersionUID = -1428447521L;

    public static final QOwner owner = new QOwner("owner");

    public final ListPath<Car, QCar> cars = this.<Car, QCar>createList("cars", Car.class, QCar.class, PathInits.DIRECT2);

    public final NumberPath<Long> ownerId = createNumber("ownerId", Long.class);

    public final StringPath ownerNm = createString("ownerNm");

    public QOwner(String variable) {
        super(Owner.class, forVariable(variable));
    }

    public QOwner(Path<? extends Owner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOwner(PathMetadata metadata) {
        super(Owner.class, metadata);
    }

}

