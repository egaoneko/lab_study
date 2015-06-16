package net.study.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QBook is a Querydsl query type for Book
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBook extends EntityPathBase<Book> {

    private static final long serialVersionUID = 798477117L;

    public static final QBook book = new QBook("book");

    public final StringPath author = createString("author");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final StringPath isbn = createString("isbn");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final DatePath<java.util.Date> pubdate = createDate("pubdate", java.util.Date.class);

    public final StringPath publisher = createString("publisher");

    public final SetPath<Study, QStudy> studySet = this.<Study, QStudy>createSet("studySet", Study.class, QStudy.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QBook(String variable) {
        super(Book.class, forVariable(variable));
    }

    public QBook(Path<? extends Book> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBook(PathMetadata<?> metadata) {
        super(Book.class, metadata);
    }

}

