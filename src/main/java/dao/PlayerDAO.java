package dao;

import java.sql.Connection;

public class PlayerDAO {
    private Connection connection;

    public PlayerDAO() {}

    private static class PlayerDAOHelper {
        private static final PlayerDAO INSTANCE = new PlayerDAO();
    }
    public static PlayerDAO getInstance() {
        return PlayerDAOHelper.INSTANCE;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
