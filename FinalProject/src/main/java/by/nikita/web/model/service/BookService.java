package by.nikita.web.model.service;

import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.entity.Book;
import by.nikita.web.model.entity.Comment;

import java.util.List;

/**
 * The {@code BookService} class represents Book Service.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public interface BookService {
    /**
     * add book
     *
     * @param book  the all information of book
     * @param genre the id genre
     */
    void addBook(Book book, int genre) throws ServiceException;

    /**
     * delete the book
     *
     * @param idBook the id book
     */
    void deleteBook(int idBook) throws ServiceException;

    /**
     * gets all user-bought books
     *
     * @param idUser the id user
     * @return the books
     */
    List<Book> getMyBoughtBook(int idUser) throws ServiceException;

    /**
     * gets all books by genre
     *
     * @param idGenre the id genre
     * @return the books
     */
    List<Book> getAllBookByGenre(int idGenre) throws ServiceException;

    /**
     * gets all books by name
     *
     * @param name the name book
     * @return the books
     */
    List<Book> getAllBookByName(String name) throws ServiceException;

    /**
     * gets all books by id author
     *
     * @param idUser the id author(user)
     * @return the books
     */
    List<Book> getAllBookByIdAuthor(int idUser) throws ServiceException;

    /**
     * gets all books by criteria
     *
     * @param sort the selection criterion
     * @return the books
     */
    List<Book> getAllBookSort(String sort) throws ServiceException;

    /**
     * find book by id
     *
     * @param idBook the id book
     * @return the book
     */
    Book findBook(int idBook) throws ServiceException;

    /**
     * updates the picture of the book
     *
     * @param idBook      the id book
     * @param namePicture the new name picture
     */
    void updateBookPicture(int idBook, String namePicture) throws ServiceException;

    /**
     * changes book access
     *
     * @param idBook the id book
     * @param status the new status of the book
     */
    void updateBookStatus(int idBook, int status) throws ServiceException;

    /**
     * get all book comments
     *
     * @param idBook the id book
     * @return all comments
     */
    List<Comment> findAllComments(int idBook) throws ServiceException;

    /**
     * add comment for the book
     *
     * @param comment the comment
     */
    void addComment(Comment comment) throws ServiceException;

    /**
     * updates book information
     *
     * @param book the book
     */
    void updateBook(Book book) throws ServiceException;
}
