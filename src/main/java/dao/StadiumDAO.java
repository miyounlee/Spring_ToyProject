package dao;

import model.Stadium;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StadiumDAO {

    private Connection connection;

    private static final StadiumDAO INSTANCE = new StadiumDAO();

    private StadiumDAO() {
    }

    public static StadiumDAO getInstance() {
        return INSTANCE;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public int createStadium(String name) {
        String query = "insert into stadium(name, created_at) values(?, now())";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);

            int result = statement.executeUpdate(); // 성공하면 1, 실패하면 -1

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return -1;
    }

    // stadium get (list)
    public List<Stadium> getStadiumList() {
        List<Stadium> stadiumList = new ArrayList<>();

        String query = "SELECT * FROM stadium";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Stadium stadium = buildTeamFromResultSet(rs);
                stadiumList.add(stadium);
            }
            return stadiumList;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return stadiumList; // 빈 컬렉션
    }

    private Stadium buildTeamFromResultSet(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        Timestamp createdAt = resultSet.getTimestamp("created_at");

        return Stadium.builder()
                .id(id)
                .name(name)
                .createdAt(createdAt)
                .build();
    }
}
