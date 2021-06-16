package com.kang.dao;

import com.kang.bean.Book;

import java.util.List;

/**
 * @author Kangshitao
 * @date 2021年6月13日 下午4:59
 */
public interface BookDao {
    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public abstract Integer queryForPageTotalCount();

    public List<Book> queryForPageItems(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);

}
