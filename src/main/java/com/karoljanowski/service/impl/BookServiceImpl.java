package com.karoljanowski.service.impl;

import com.karoljanowski.domain.Book;
import com.karoljanowski.repository.BookRepository;
import com.karoljanowski.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Karol Janowski on 2017-06-27.
 */
@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findOne(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public void remove(Book book) {
        bookRepository.delete(book);
    }
}












