package dao;

import dto.TeamRespDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {
    private Connection connection;

    // ---- Bill Pugh Singleton Implementation ------
    public TeamDAO() {}

    private static class TeamDAOHelper {
        private static final TeamDAO INSTANCE = new TeamDAO();
    }

    public static TeamDAO getInstance() {
        return TeamDAOHelper.INSTANCE;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    // --------------------------------------------

    // 팀 등록
    public int createTeam(int stadiumId, String name) {
        String query = "INSERT INTO team(stadium_id, name, created_at) VALUES(?, ?, now())";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, stadiumId);
            statement.setString(2, name);

            int result = statement.executeUpdate();

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return -1;
    }

    // 팀 전체 목록
    public List<TeamRespDTO> getTeamList() {
        List<TeamRespDTO> teamList = new ArrayList<>();

        String query = "SELECT t.id team_id\n" +
                ", t.name team_name\n" +
                ", s.id stadium_id\n" +
                ", s.name stadium_name\n" +
                " FROM team t \n" +
                " LEFT JOIN stadium s ON t.stadium_id = s.id";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                TeamRespDTO teamRespDTO = buildTeamFromResultSet(resultSet);
                teamList.add(teamRespDTO);
            }
            return teamList;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return teamList;
    }


    private TeamRespDTO buildTeamFromResultSet(ResultSet resultSet) throws SQLException {
        // (team) id, stadium_id, name, created_at / (stadium) id, name, created_at

        int teamId = resultSet.getInt("team_id");
        String teamName = resultSet.getString("team_name");
        int stadiumId = resultSet.getInt("stadium_id");
        String stadiumName = resultSet.getString("stadium_name");

        return TeamRespDTO.builder()
                .teamId(teamId)
                .teamName(teamName)
                .stadiumId(stadiumId)
                .stadiumName(stadiumName)
                .build();

    }
}