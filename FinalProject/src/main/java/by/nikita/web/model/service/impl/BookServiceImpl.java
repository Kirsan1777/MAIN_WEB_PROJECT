package by.nikita.web.model.service.impl;

import by.nikita.web.exception.ConnectionDataBaseException;
import by.nikita.web.exception.DaoException;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.dao.ConnectionPool;
import by.nikita.web.model.dao.impl.BankDaoImpl;
import by.nikita.web.model.dao.impl.BookDaoImpl;
import by.nikita.web.model.dao.impl.OrderDaoImpl;
import by.nikita.web.model.dao.query.SqlBookRequest;
import by.nikita.web.model.dao.query.SqlUserRequest;
import by.nikita.web.model.entity.Book;
import by.nikita.web.model.entity.Comment;
import by.nikita.web.model.entity.User;
import by.nikita.web.model.service.BookService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
    private static final BookServiceImpl instance = new BookServiceImpl();

    public static BookServiceImpl getInstance() {
        return instance;
    }

    private BookServiceImpl() {
    }

    private static final BookDaoImpl bookDao = BookDaoImpl.getInstance();
    private static final OrderDaoImpl orderDao = OrderDaoImpl.getInstance();

    @Override
    public void addBook(Book book, int genre) throws ServiceException {
        try {
            bookDao.addBook(book, genre);
        } catch (DaoException e) {
            throw new ServiceException("Can't add book", e);
        }
    }

    @Override
    public void deleteBook(int idBook) throws ServiceException{
        try {
            orderDao.deleteOrders(idBook);
            bookDao.deleteComments(idBook);
            bookDao.deleteBook(idBook);
        } catch (DaoException e) {
            throw new ServiceException("Can't delete book", e);
        }
    }

    @Override
    public List<Book> getMyBoughtBook(int idUser) throws ServiceException{
        List<Book> myBook = new ArrayList<>();
        List<Integer> books;
        Book book;
        try {
            books = orderDao.getAllBooksByIdUser(idUser);
            for(int id : books) {
                book = bookDao.findBookById(id);
                myBook.add(book);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);        }
        return myBook;
    }

    @Override
    public List<Book> getAllBookByGenre(int idGenre) throws ServiceException{
        List<Book> books;
        try{
            books = bookDao.getAllBookByGenre(idGenre);
        }catch (DaoException ex){
            throw new ServiceException(ex);
        }
        return books;
    }

    @Override
    public List<Book> getAllBookByName(String name) throws ServiceException{
        List<Book> books;
        try{
            books = bookDao.findBookByName(name);
        }catch (DaoException ex){
            throw new ServiceException(ex);
        }
        return books;
    }

    @Override
    public List<Book> getAllBookById(int idUser) throws ServiceException{
        List<Book> books;
        try{
            books = bookDao.findAllBookById(idUser);
        }catch (DaoException ex){
            throw new ServiceException(ex);
        }
        return books;
    }

    @Override
    public List<Book> getAllBookByIdAuthor(int idUser) throws ServiceException{
        List<Book> books;
        try{
            books = bookDao.findAllBookByIdAuthor(idUser);
        }catch (DaoException ex){
            throw new ServiceException(ex);
        }
        return books;
    }

    @Override
    public List<Book> getAllBookSort(String sort) throws ServiceException{
        List<Book> books;
        try{
            books = bookDao.getAllBooksBySort(sort);
        }catch (DaoException ex){
            throw new ServiceException(ex);
        }
        return books;
    }

    @Override
    public Book findBook(int idBook) throws ServiceException {
        Book book;
        try {
            book = bookDao.findBookById(idBook);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return book;
    }

    @Override
    public void updateBookPicture(int idBook, String namePicture) throws ServiceException {
        try {
            bookDao.updatePictureBook(idBook, namePicture);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateBookStatus(int idBook, int status) throws ServiceException {
        try {
            bookDao.updateStatusBook(idBook, status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Comment> findAllComments(int idBook)throws ServiceException{
        List<Comment> comments;
        try {
            comments = bookDao.findCommentsById(idBook);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return comments;
    }

    @Override
    public void addComment(Comment comment)throws ServiceException{
        try {
            bookDao.addComment(comment);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateBook(Book book)throws ServiceException{
        try {
            bookDao.updateBookInfo(book);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


}
