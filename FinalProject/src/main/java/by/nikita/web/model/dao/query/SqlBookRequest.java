package by.nikita.web.model.dao.query;

public final class SqlBookRequest {

    public static final String ADD_BOOK = "INSERT books(author_id, name_book, text, genre_book, description, add_time, cost, name_picture) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String DELETE_BOOK = "DELETE FROM books WHERE id_book = ?";
    public static final String SELECT_ALL_BOOKS = "SELECT id_book, author_id, name_book, text, genres.genre, description, add_time, access, cost, name_picture FROM books " +
            "INNER JOIN genres ON books.genre_book = genres.id_genre";
    public static final String LOOK_FOR_BOOK_AUTHOR = SELECT_ALL_BOOKS + " WHERE author_id=?";
    public static final String LOOK_FOR_BOOK_BY_NAME = SELECT_ALL_BOOKS + " WHERE name_book LIKE ?";
    public static final String LOOK_FOR_BOOK_BY_ID = SELECT_ALL_BOOKS + " WHERE id_book = ?";
    public static final String UPDATE_PICTURE_BOOK = "UPDATE books SET name_picture = ? WHERE id_book = ?";
    public static final String UPDATE_STATUS_BOOK = "UPDATE books SET access = ? WHERE id_book = ?";
    public static final String UPDATE_BOOK_INFO = "UPDATE books SET name_book = ?, text = ?, description = ?, cost = ?  WHERE id_book = ?";
    public static final String SORT_ALL_BOOKS = SELECT_ALL_BOOKS + " WHERE access = 0" +" ORDER BY ";
    public static final String FIND_ALL_BOOK_BY_GENRE = SELECT_ALL_BOOKS + " WHERE genre_book = ?";

    //comments request
    public static final String ADD_COMMENTS = "INSERT comments(id_user, id_book, comment, add_time) VALUES (?, ?, ?, ?)";
    public static final String DELETE_COMMENT_BY_ID = "DELETE FROM comments WHERE id_book = ?";
    public static final String UPDATE_STATUS_COMMENTS = "UPDATE comments SET status = ? WHERE id = ?";
    public static final String SELECT_ALL_COMMENTS_BY_ID = "SELECT id, comment, add_time, status, id_user, id_book FROM comments WHERE id_book = ? ORDER BY add_time DESC";
    public static final String SELECT_ALL_COMMENTS_BY_STATUS = "SELECT id, comment, add_time, status, id_user, id_book FROM comments WHERE id_user = ? AND status = 1";
}
