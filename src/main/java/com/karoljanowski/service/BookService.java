package com.karoljanowski.service;

import com.karoljanowski.domain.Book;

import java.util.List;

/**
 * Created by Karol Janowski on 2017-06-27.
 */
public interface BookService {
    Book save(Book book);
    List<Book> findAll();
    Book findOne(Long id);

}
