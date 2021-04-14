package by.nikita.web.model.dao.query;

public final class SqlUserRequest {
    //прочитать как join таблички, через табличку получить значение, вместо 1 получить название - администратор
    public static final String ADD_USER = "INSERT users(login, password) VALUES (?, ?)";
    public static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    public static final String DELETE_USER_INFO_BY_ID = "DELETE FROM users_info WHERE id_user = ?";
    public static final String SELECT_ALL_USERS = "SELECT users.login, users.password, roles.name_role, users.id FROM users " +
            "INNER JOIN roles ON users.role = roles.id_role";
    public static final String LOOK_FOR_USER = SELECT_ALL_USERS + " WHERE login=? AND password=?";
    public static final String LOOK_FOR_USER_NAME = SELECT_ALL_USERS + " WHERE login=?";
    public static final String LOOK_FOR_USER_BY_ID = SELECT_ALL_USERS + " WHERE id = ?";
    public static final String ADD_USER_INFO = "INSERT users_info(id_user, name, date_registration, rating, email) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_USERS_INFORMATION = "SELECT users_info.id_user, users_info.name, " +
        "users_info.date_registration, users_info.rating, users_info.name_picture, users_info.email FROM users_info";
    public static final String LOOK_FOR_USER_INFO = SELECT_ALL_USERS_INFORMATION + " WHERE id_user=?";
    //public static final String ADD_PICTURE = "";
    public static final String LOOK_FOR_USER_INFO_BY_NAME = SELECT_ALL_USERS_INFORMATION + " WHERE name LIKE ?";
    //дальше команды на изменение полей будут
    public static final String UPDATE_PASSWORD = "UPDATE users SET users.password = ? WHERE users.id = ?";
    public static final String UPDATE_ROLE = "UPDATE users SET users.role = ? WHERE users.id = ?";
    public static final String UPDATE_PICTURE = "UPDATE users_info SET name_picture = ? WHERE users_info.id_user = ?";
    public static final String UPDATE_USER_INFO = "UPDATE users_info SET email = ?, name = ? WHERE users_info.id_user = ?";
    public static final String SORT_ALL_USERS = SELECT_ALL_USERS_INFORMATION + " ORDER BY ";

}
