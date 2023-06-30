package dao;

import dto.PositionRespDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PlayersByPositionDAO {

    private Connection connection;

    private static final PlayersByPositionDAO INSTANCE = new PlayersByPositionDAO();
    public PlayersByPositionDAO() {
    }

    public static PlayersByPositionDAO getInstance() {
        return INSTANCE;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    // 포지션 별 팀 선수 피벗 테이블
    public PositionRespDTO getPlayerByPositionList() {

        List<String> teamList = getTeamNameList();
        Map<String, List<String>> positionMap = new LinkedHashMap<>();
        StringBuilder builder = new StringBuilder();

        builder.append("select position");
        for (String team : teamList) {
            builder.append(", MAX(CASE WHEN t.name = '");
            builder.append(team);
            builder.append("' THEN p.name END) ");
            builder.append(team);
        }
        builder.append(" FROM player p LEFT JOIN team t ON p.team_id = t.id GROUP BY p.position");

        try {
            PreparedStatement statement = connection.prepareStatement(builder.toString());
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                String position = resultSet.getString("position");

                List<String> playerList = new ArrayList<>();
                for (String team : teamList) {
                    playerList.add(resultSet.getString(team));
                }

                positionMap.put(position, playerList);
            }
            return PositionRespDTO.builder()
                    .teamList(teamList)
                    .positionMap(positionMap)
                    .build();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return PositionRespDTO.builder().build();
    }

    // 팀 이름 가져오기
    public List<String> getTeamNameList() {
        List<String> teamList = new ArrayList<>();
        String query = "SELECT name FROM team";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                teamList.add(resultSet.getString("name"));
            }
            return teamList;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return teamList;
    }
}
