package service;

import dao.PlayerDAO;
import dao.PlayersByPositionDAO;
import dto.PositionRespDTO;

import java.sql.Connection;

public class PlayerService {

    private PlayerDAO playerDAO;
    private PlayersByPositionDAO playersByPositionDAO;
    private Connection connection;

    public PlayerService(PlayerDAO playerDAO, PlayersByPositionDAO playersByPositionDAO, Connection connection) {
        this.playerDAO = playerDAO;
        this.playersByPositionDAO = playersByPositionDAO;
        this.connection = connection;
    }

    public PositionRespDTO getPlayerByPositions() {
        return playersByPositionDAO.getPlayerByPositionList();
    }
}
