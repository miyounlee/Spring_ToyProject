package service;

import dao.PlayerDAO;
import dao.PlayersByPositionDAO;
import dto.PositionRespDTO;
import model.Player;

import java.sql.*;
import java.util.*;

public class PlayerService {
    private PlayerDAO playerDao;
    private PlayersByPositionDAO playersByPositionDAO;

    private Connection connection;

    public PlayerService(PlayerDAO playerDAO,PlayersByPositionDAO playersByPositionDAO, Connection connection) {
        this.playerDao = playerDAO;
        this.playersByPositionDAO = playersByPositionDAO;
        this.connection = connection;
    }

    public PlayerService(PlayerDAO playerDAO) {
        this.playerDao = playerDAO;
    }

    // 선수 등록
    public String createPlayer(Integer teamId, String name, String position) {
        try {
            int result = playerDao.createPlayer(teamId, name, position);
            if (result > 0) {
                return "성공";
            } else {
                return "실패";
            }
        } catch (Exception e) {
            return "선수 등록 오류발생 : " + e.getMessage();
        }
    }

    // 팀별 선수 목록 조회
    public List<Player> getPlayersByTeamIdList(int teamId) {
        List<Player> players = playerDao.getPlayersByTeamIdList(teamId);
        if (players == null) {
            throw new RuntimeException("선수 목록을 가져올 수 없습니다.");
        }
        return players;
    }

    public PositionRespDTO getPlayerByPositions() {
        return playersByPositionDAO.getPlayerByPositionList();
    }
}
