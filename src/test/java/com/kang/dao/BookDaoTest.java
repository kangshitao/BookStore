package com.kang.dao;

import com.kang.bean.Book;
import com.kang.dao.iml.BookDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Kangshitao
 * @date 2021年6月13日 下午5:23
 */
//Integer id, String name, String author, BigDecimal price, Integer sales, Integer stock, String imgPath
public class BookDaoTest {
    BookDaoImpl bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"测试","Author",new BigDecimal(222),1111,0,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"测试book","Author",new BigDecimal(222),1111,0,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for(Book q:bookDao.queryBooks()){
            System.out.println(q);
        }
    }
    @Test
    public void  queryForPageTotalCount(){
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems(){
        List<Book> books = bookDao.queryForPageItems(0, 4);
        for(Book book:books){
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice(){
        System.out.println(bookDao.queryForPageTotalCountByPrice(50, 100));

    }

    @Test
    public void queryForPageItemsByPrice(){
        List<Book> books = bookDao.queryForPageItemsByPrice(0,5,50,100);
        for(Book book:books){
            System.out.println(book);
        }
    }
}