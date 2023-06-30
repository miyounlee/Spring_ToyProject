import dao.PlayerDAO;
import dao.PlayersByPositionDAO;
import dao.StadiumDAO;
import dao.TeamDAO;
import db.DBConnection;
import handler.RequestHandler;
import service.PlayerService;
import service.StadiumService;
import service.TeamService;

import java.sql.Connection;
import java.sql.SQLException;


public class BaseBallApp {
    public static void main(String[] args) throws SQLException {

        Connection connection = DBConnection.getInstance();

        // ---------- dao ----------
        TeamDAO teamDAO = TeamDAO.getInstance();
        teamDAO.setConnection(connection);

        StadiumDAO stadiumDAO = StadiumDAO.getInstance();
        stadiumDAO.setConnection(connection);

        PlayerDAO playerDAO = PlayerDAO.getInstance();
        playerDAO.setConnection(connection);

        PlayersByPositionDAO playersByPositionDAO = PlayersByPositionDAO.getInstance();
        playersByPositionDAO.setConnection(connection);

        // ---------- service ----------

        StadiumService stadiumService = new StadiumService(stadiumDAO, connection);
        TeamService teamService = new TeamService(teamDAO, connection);
        PlayerService playerService = new PlayerService(playerDAO, playersByPositionDAO, connection);
        RequestHandler requestHandler = new RequestHandler(stadiumService, teamService, playerService);

        // ---------- 파싱 ----------
        requestHandler.requestHandler();

    }
}
