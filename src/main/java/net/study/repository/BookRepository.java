package net.study.repository;

import net.study.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/15/15 | 2:32 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findOneByIsbn(String isbn);
}
