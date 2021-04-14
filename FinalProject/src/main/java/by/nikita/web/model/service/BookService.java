package by.nikita.web.model.service;

import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.entity.Book;
import by.nikita.web.model.entity.Comment;
import java.util.List;

public interface BookService {
     void addBook(Book book, int genre) throws ServiceException ;
     void deleteBook(int idBook) throws ServiceException;
     List<Book> getMyBoughtBook(int idUser) throws ServiceException;
     List<Book> getAllBookByGenre(int idGenre) throws ServiceException;
     List<Book> getAllBookByName(String name) throws ServiceException;
     List<Book> getAllBookById(int idUser) throws ServiceException;
     List<Book> getAllBookByIdAuthor(int idUser) throws ServiceException;
     List<Book> getAllBookSort(String sort) throws ServiceException;
     Book findBook(int idBook) throws ServiceException;
     void updateBookPicture(int idBook, String namePicture) throws ServiceException;
     void updateBookStatus(int idBook, int status) throws ServiceException;
     List<Comment> findAllComments(int idBook)throws ServiceException;
     void addComment(Comment comment)throws ServiceException;
     void updateBook(Book book)throws ServiceException;
}
