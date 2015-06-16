package net.study.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStudy is a Querydsl query type for Study
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStudy extends EntityPathBase<Study> {

    private static final long serialVersionUID = -1001158667L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudy study = new QStudy("study");

    public final EnumPath<net.study.domain.enums.Area> area = createEnum("area", net.study.domain.enums.Area.class);

    public final SetPath<Book, QBook> bookSet = this.<Book, QBook>createSet("bookSet", Book.class, QBook.class, PathInits.DIRECT2);

    public final EnumPath<net.study.domain.enums.Category> category = createEnum("category", net.study.domain.enums.Category.class);

    public final EnumPath<net.study.domain.enums.Charge> charge = createEnum("charge", net.study.domain.enums.Charge.class);

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<net.study.domain.enums.OnOffLine> onOffLine = createEnum("onOffLine", net.study.domain.enums.OnOffLine.class);

    public final NumberPath<Integer> participant = createNumber("participant", Integer.class);

    public final DateTimePath<java.util.Date> postingDate = createDateTime("postingDate", java.util.Date.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final EnumPath<net.study.domain.enums.Status> status = createEnum("status", net.study.domain.enums.Status.class);

    public final StringPath title = createString("title");

    public final QUser user;

    public final EnumPath<net.study.domain.enums.Way> way = createEnum("way", net.study.domain.enums.Way.class);

    public QStudy(String variable) {
        this(Study.class, forVariable(variable), INITS);
    }

    public QStudy(Path<? extends Study> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStudy(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStudy(PathMetadata<?> metadata, PathInits inits) {
        this(Study.class, metadata, inits);
    }

    public QStudy(Class<? extends Study> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

