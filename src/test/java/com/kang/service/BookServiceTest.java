package com.kang.service;

import com.kang.bean.Book;
import com.kang.bean.Page;
import com.kang.service.iml.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Kangshitao
 * @date 2021年6月13日 下午5:41
 */
public class BookServiceTest {
    BookServiceImpl bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(21,"测试","Author",new BigDecimal(222),1111,0,null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"测试book","Author",new BigDecimal(222),1111,0,null));
    }

    @Test
    public void queryBookById() {
        bookService.queryBookById(22);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();
        for(Book b:books){
            System.out.println(b);
        }
    }
    @Test
    public void page() {
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }
}