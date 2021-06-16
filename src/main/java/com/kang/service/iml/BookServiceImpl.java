package com.kang.service.iml;

import com.kang.bean.Book;
import com.kang.bean.Page;
import com.kang.dao.iml.BookDaoImpl;
import com.kang.service.BookService;

import java.util.List;

/**
 * @author Kangshitao
 * @date 2021年6月13日 下午5:38
 */
public class BookServiceImpl implements BookService {
    private BookDaoImpl bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();

        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount/pageSize;
        if(pageTotalCount%pageSize >0){
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        //求当前页数据
        int begin = (page.getPageNo()-1)*pageSize;
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();

        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min, max);
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount/pageSize;
        if(pageTotalCount%pageSize >0){
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        //求当前页数据
        int begin = (page.getPageNo()-1)*pageSize;
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);
        return page;
    }
}
