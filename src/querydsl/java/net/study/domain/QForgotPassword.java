package net.study.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QForgotPassword is a Querydsl query type for ForgotPassword
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QForgotPassword extends EntityPathBase<ForgotPassword> {

    private static final long serialVersionUID = 1699505714L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QForgotPassword forgotPassword = new QForgotPassword("forgotPassword");

    public final DateTimePath<java.util.Date> expiredDate = createDateTime("expiredDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath keyHash = createString("keyHash");

    public final QUser user;

    public final EnumPath<net.study.domain.enums.ValidEntity> validEntity = createEnum("validEntity", net.study.domain.enums.ValidEntity.class);

    public QForgotPassword(String variable) {
        this(ForgotPassword.class, forVariable(variable), INITS);
    }

    public QForgotPassword(Path<? extends ForgotPassword> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QForgotPassword(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QForgotPassword(PathMetadata<?> metadata, PathInits inits) {
        this(ForgotPassword.class, metadata, inits);
    }

    public QForgotPassword(Class<? extends ForgotPassword> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

