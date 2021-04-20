package by.nikita.web.model.dao.impl;

import by.nikita.web.exception.ConnectionDataBaseException;
import by.nikita.web.exception.DaoException;
import by.nikita.web.model.dao.BookDAO;
import by.nikita.web.model.dao.ConnectionPool;
import by.nikita.web.model.dao.query.SqlBookRequest;
import by.nikita.web.model.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BookDaoImpl implements BookDAO {
    private static final BookDaoImpl instance = new BookDaoImpl();

    private BookDaoImpl() {
    }
    /**
     * get instance
     *
     * @return the instance
     */
    public static BookDaoImpl getInstance() {
        return instance;
    }

    @Override
    public void addBook(Book book, int genre) throws DaoException {
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        ConnectionPool pool = ConnectionPool.getInstance();
        String time = stamp.toString();
        time = time.substring(0, time.length() - 7);

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlBookRequest.ADD_BOOK)) {
            statement.setInt(1, book.getAuthorId());
            statement.setString(2, book.getNameBook());
            statement.setString(3, book.getText());
            statement.setInt(4, genre);
            statement.setString(5, book.getDescription());
            statement.setString(6, time);
            statement.setDouble(7, book.getCost());
            statement.setString(8, book.getPhotoReference());
            statement.executeUpdate();//command from update DB

        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }

    @Override
    public void deleteBook(int idBookForDelete) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlBookRequest.DELETE_BOOK)) {
            statement.setInt(1, idBookForDelete);
            statement.execute();
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }

    @Override
    public void deleteComments(int idBook) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlBookRequest.DELETE_COMMENT_BY_ID)) {
            statement.setInt(1, idBook);
            statement.execute();
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }

    @Override
    public List<Book> getAllBooks() throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<Book> books;
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlBookRequest.SELECT_ALL_BOOKS);
            books = readAllBooks(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
        return books;
    }

    @Override
    public Book findBookById(int idBook) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Book book;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlBookRequest.LOOK_FOR_BOOK_BY_ID)) {
            statement.setInt(1, idBook);
            ResultSet resultSet = statement.executeQuery();
            book = readBooks(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
        return book;
    }

    @Override
    public List<Book> findAllBookByIdAuthor(int idAuthor) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<Book> books;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlBookRequest.LOOK_FOR_BOOK_AUTHOR)) {
            statement.setInt(1, idAuthor);
            ResultSet resultSet = statement.executeQuery();
            books = readAllBooks(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
        return books;
    }

    private List<Book> readAllBooks(ResultSet resultSet) throws SQLException {
        Book books;
        List<Book> listWithBooks = new ArrayList<>();
        while (resultSet.next()) {
            int idBook = resultSet.getInt(1);
            int authorId = resultSet.getInt(2);
            String nameBook = resultSet.getString(3);
            String text = resultSet.getString(4);
            Genre genre = Genre.valueOf(resultSet.getString(5).toUpperCase(Locale.ROOT));
            String description = resultSet.getString(6);
            String addTime = resultSet.getString(7);
            int access = resultSet.getInt(8);
            double cost = resultSet.getDouble(9);
            String namePicture = resultSet.getString(10);
            //int like = resultSet.getInt(11);
            books = new Book(nameBook, idBook, authorId, genre, text, description, namePicture, cost, addTime, access);
            listWithBooks.add(books);
        }
        return listWithBooks;
    }

    @Override
    public List<Book> getAllBooksBySort(String sort) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<Book> books;
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlBookRequest.SORT_ALL_BOOKS + sort);
            books = readAllBooks(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
        return books;
    }

    private Book readBooks(ResultSet resultSet) throws SQLException {
        Book book = null;
        while (resultSet.next()) {
            int idBook = resultSet.getInt(1);
            int authorId = resultSet.getInt(2);
            String nameBook = resultSet.getString(3);
            String text = resultSet.getString(4);
            Genre genre = Genre.valueOf(resultSet.getString(5).toUpperCase(Locale.ROOT));
            String description = resultSet.getString(6);
            String addTime = resultSet.getString(7);
            int access = resultSet.getInt(8);
            double cost = resultSet.getDouble(9);
            String namePicture = resultSet.getString(10);
            book = new Book(nameBook, idBook, authorId, genre, text, description, namePicture, cost, addTime, access);
        }
        return book;
    }

    @Override
    public void updatePictureBook(int idBook, String pictureName) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlBookRequest.UPDATE_PICTURE_BOOK)) {
            statement.setString(1, pictureName);
            statement.setInt(2, idBook);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }

    @Override
    public void updateStatusBook(int idBook, int status) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlBookRequest.UPDATE_STATUS_BOOK)) {
            statement.setInt(1, status);
            statement.setInt(2, idBook);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }

    @Override
    public void addComment(Comment comment) throws DaoException {
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        String time = stamp.toString();
        time = time.substring(0, time.length() - 7);
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlBookRequest.ADD_COMMENTS)) {
            statement.setInt(1, comment.getIdUser());
            statement.setInt(2, comment.getIdBook());
            statement.setString(3, comment.getComment());
            statement.setString(4, time);
            statement.executeUpdate();//command from update DB
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }

    @Override
    public void updateBookInfo(Book book) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlBookRequest.UPDATE_BOOK_INFO)) {
            statement.setString(1, book.getNameBook());
            statement.setString(2, book.getText());
            statement.setString(3, book.getDescription());
            statement.setDouble(4, book.getCost());
            statement.setInt(5, book.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }

    @Override
    public void updateCommentStatus(int idComment, int status) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlBookRequest.UPDATE_STATUS_COMMENTS)) {
            statement.setInt(1, status);
            statement.setInt(2, idComment);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }

    @Override
    public List<Comment> findCommentsById(int idBook) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<Comment> comments;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlBookRequest.SELECT_ALL_COMMENTS_BY_ID)) {
            statement.setInt(1, idBook);
            ResultSet resultSet = statement.executeQuery();
            comments = readAllComment(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
        return comments;
    }

    @Override
    public List<Comment> findCommentsByStatus(int idUser) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<Comment> comments;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlBookRequest.SELECT_ALL_COMMENTS_BY_STATUS)) {
            statement.setInt(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            comments = readAllComment(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
        return comments;
    }

    private List<Comment> readAllComment(ResultSet resultSet) throws SQLException {
        Comment comment;
        List<Comment> listWithComments = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String uniqueComment = resultSet.getString(2);
            String addTime = resultSet.getString(3);
            int status = resultSet.getInt(4);
            int idUser = resultSet.getInt(5);
            int idBook = resultSet.getInt(6);
            comment = new Comment(id, uniqueComment, addTime, status, idUser, idBook);
            listWithComments.add(comment);
        }
        return listWithComments;
    }

    @Override
    public List<Book> findBookByName(String name) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<Book> books;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlBookRequest.LOOK_FOR_BOOK_BY_NAME)) {
            statement.setString(1, name + "%");
            ResultSet resultSet = statement.executeQuery();
            books = readAllBooks(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
        return books;
    }

    @Override
    public List<Book> getAllBookByGenre(int typeGenre) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<Book> books;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlBookRequest.FIND_ALL_BOOK_BY_GENRE)) {
            statement.setInt(1, typeGenre);
            ResultSet resultSet = statement.executeQuery();
            books = readAllBooks(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
        return books;
    }


}
