package by.nikita.web.model.dao;


import by.nikita.web.exception.DaoException;
import by.nikita.web.model.entity.Book;
import by.nikita.web.model.entity.Comment;

import java.util.List;

/**
 * The {@code BookDAO} class represents book DAO.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public interface BookDAO {
    /**
     * add book
     *
     * @param book  the all information of book
     * @param genre the id genre
     */
    void addBook(Book book, int genre) throws DaoException;

    /**
     * delete the book
     *
     * @param idBookForDelete the id book
     */
    void deleteBook(int idBookForDelete) throws DaoException;

    /**
     * delete the comment
     *
     * @param idBook the id book
     */
    void deleteComments(int idBook) throws DaoException;

    /**
     * gets all books
     *
     * @return the books
     */
    List<Book> getAllBooks() throws DaoException;

    /**
     * find book by id
     *
     * @param idBook the id book
     * @return the book
     */
    Book findBookById(int idBook) throws DaoException;

    /**
     * gets all books by id author
     *
     * @param idAuthor the id author(user)
     * @return the books
     */
    List<Book> findAllBookByIdAuthor(int idAuthor) throws DaoException;

    /**
     * gets all books by criteria
     *
     * @param sort the selection criterion
     * @return the books
     */
    List<Book> getAllBooksBySort(String sort) throws DaoException;

    /**
     * updates the picture of the book
     *
     * @param idBook      the id book
     * @param pictureName the new name picture
     */
    void updatePictureBook(int idBook, String pictureName) throws DaoException;

    /**
     * changes book access
     *
     * @param idBook the id book
     * @param status the new status of the book
     */
    void updateStatusBook(int idBook, int status) throws DaoException;

    /**
     * add comment for the book
     *
     * @param comment the comment
     */
    void addComment(Comment comment) throws DaoException;

    /**
     * updates book information
     *
     * @param book the book
     */
    void updateBookInfo(Book book) throws DaoException;

    /**
     * updates comment status
     *
     * @param status    the new status
     * @param idComment the comment id
     */
    void updateCommentStatus(int idComment, int status) throws DaoException;

    /**
     * get all book comments
     *
     * @param idBook the id book
     * @return all comments
     */
    List<Comment> findCommentsById(int idBook) throws DaoException;

    /**
     * get all comments by status
     *
     * @param idUser the id book
     * @return all comments
     */
    List<Comment> findCommentsByStatus(int idUser) throws DaoException;

    /**
     * gets all books by name
     *
     * @param name the name book
     * @return the books
     */
    List<Book> findBookByName(String name) throws DaoException;

    /**
     * gets all books by genre
     *
     * @param typeGenre the id genre
     * @return the books
     */
    List<Book> getAllBookByGenre(int typeGenre) throws DaoException;

}
