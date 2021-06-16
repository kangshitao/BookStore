package com.kang.service;

import com.kang.bean.Book;
import com.kang.bean.Page;

import java.util.List;

/**
 * @author Kangshitao
 * @date 2021年6月13日 下午5:36
 */
public interface BookService {
    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
