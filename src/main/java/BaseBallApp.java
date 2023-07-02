import dao.*;
import db.DBConnection;
import requestHandler.RequestParser;
import service.OutPlayerService;
import service.PlayerService;
import service.StadiumService;
import service.TeamService;

import java.sql.Connection;


public class BaseBallApp {
    public static void main(String[] args) {

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

        OutPlayerDAO outPlayerDAO = OutPlayerDAO.getInstance();
        outPlayerDAO.setConnection(connection);

        // ---------- service ----------

        StadiumService stadiumService = new StadiumService(stadiumDAO, connection);
        TeamService teamService = new TeamService(teamDAO, connection);
        PlayerService playerService = new PlayerService(playerDAO, playersByPositionDAO, connection);
        OutPlayerService outPlayerService = new OutPlayerService(playerDAO, outPlayerDAO, connection);
        RequestParser requestHandler = new RequestParser(stadiumService, teamService, playerService, outPlayerService);

        // ---------- 파싱 ----------
        requestHandler.handleRequest();

    }
}
