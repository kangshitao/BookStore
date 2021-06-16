package com.kang.dao.iml;

import com.kang.bean.Book;
import com.kang.dao.BookDao;

import java.util.List;

/**
 * @author Kangshitao
 * @date 2021年6月13日 下午5:03
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "INSERT INTO t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) VALUES(?,?,?,?,?,?);";
        return update(sql,book.getName(), book.getAuthor(),book.getPrice(),
                book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id=?;";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id=?;";
        return update(sql,book.getName(), book.getAuthor(),book.getPrice(),
                book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book where id=?;";
        Book book = queryForOne(Book.class, sql, id);
        return book;
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id`, `name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book;";
        List<Book> books = queryForList(Book.class, sql);
        return books;
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        //返回值是Long类型，不能直接转为Integer
        Number number = (Number)queryForSingleValue(sql);
        return number.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id`, `name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ? order by price";
        //返回值是Long类型，不能直接转为Integer
        Number number = (Number)queryForSingleValue(sql,min,max);
        return number.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select `id`, `name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book " +
                "where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }
}
