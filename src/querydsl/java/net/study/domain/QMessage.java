package net.study.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMessage is a Querydsl query type for Message
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMessage extends EntityPathBase<Message> {

    private static final long serialVersionUID = -1501716685L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMessage message1 = new QMessage("message1");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath message = createString("message");

    public final EnumPath<net.study.domain.enums.MessageStatus> messageStatus = createEnum("messageStatus", net.study.domain.enums.MessageStatus.class);

    public final DateTimePath<java.util.Date> receiveDate = createDateTime("receiveDate", java.util.Date.class);

    public final QUser receiver;

    public final DateTimePath<java.util.Date> sendDate = createDateTime("sendDate", java.util.Date.class);

    public final QUser sender;

    public final QStudy study;

    public final EnumPath<net.study.domain.enums.StudyRequest> studyRequest = createEnum("studyRequest", net.study.domain.enums.StudyRequest.class);

    public QMessage(String variable) {
        this(Message.class, forVariable(variable), INITS);
    }

    public QMessage(Path<? extends Message> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMessage(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMessage(PathMetadata<?> metadata, PathInits inits) {
        this(Message.class, metadata, inits);
    }

    public QMessage(Class<? extends Message> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.receiver = inits.isInitialized("receiver") ? new QUser(forProperty("receiver"), inits.get("receiver")) : null;
        this.sender = inits.isInitialized("sender") ? new QUser(forProperty("sender"), inits.get("sender")) : null;
        this.study = inits.isInitialized("study") ? new QStudy(forProperty("study"), inits.get("study")) : null;
    }

}

