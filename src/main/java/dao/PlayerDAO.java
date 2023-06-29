package dao;

import model.Player;

import java.sql.*;
import java.util.*;

public class PlayerDAO {
    private Connection connection;
    private static final PlayerDAO INSTANCE = new PlayerDAO();
    private PlayerDAO() {
    }
    public static PlayerDAO getInstance() {
        return INSTANCE;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    // 선수 생성
    public int createPlayer(int teamId, String name, String position) {
        String query = "INSERT INTO player (team_id, name, position) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, teamId);
            statement.setString(2, name);
            statement.setString(3, position);

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // 선수 조회
    public Player getPlayerById(int id) {
        String query = "SELECT * FROM player WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultset = statement.executeQuery()) {
                if (resultset.next()) {
                    return buildPlayerFromResultSet(resultset);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // 선수 업데이트
    public int updatePlayer(int playerId){
        String updatequery = "UPDATE player SET team_id =NULL WHERE id = ?";
        try(PreparedStatement updatestatement = connection.prepareStatement(updatequery)){
            updatestatement.setInt(1,playerId);

            return updatestatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();;
            return 0;
        }
    }

    // 팀별 선수 목록 조회
    public List<Player> getPlayersByTeamIdList(int teamId) {
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM player WHERE team_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, teamId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    players.add(buildPlayerFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    private Player buildPlayerFromResultSet(ResultSet resultSet) {
        try {
            Integer id = resultSet.getInt("id");
            Integer teamId = resultSet.getInt("team_id");
            teamId = teamId == 0 ? null : teamId;
            String name = resultSet.getString("name");
            String position = resultSet.getString("position");
            Timestamp createdAt = resultSet.getTimestamp("created_at");

            return Player.builder()
                    .id(id)
                    .teamId(teamId)
                    .name(name)
                    .position(position)
                    .createdAt(createdAt)
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
