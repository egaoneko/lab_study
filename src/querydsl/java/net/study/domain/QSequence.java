package net.study.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QSequence is a Querydsl query type for Sequence
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSequence extends EntityPathBase<Sequence> {

    private static final long serialVersionUID = -1791902187L;

    public static final QSequence sequence = new QSequence("sequence");

    public final StringPath name = createString("name");

    public final NumberPath<Long> nextValue = createNumber("nextValue", Long.class);

    public QSequence(String variable) {
        super(Sequence.class, forVariable(variable));
    }

    public QSequence(Path<? extends Sequence> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSequence(PathMetadata<?> metadata) {
        super(Sequence.class, metadata);
    }

}

