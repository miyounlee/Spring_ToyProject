package service;

import dao.TeamDAO;
import dto.TeamRespDTO;

import java.sql.Connection;
import java.util.List;

public class TeamService {
    private TeamDAO teamDAO;
    private Connection connection;

    public TeamService(TeamDAO teamDAO, Connection connection) {
        this.teamDAO = teamDAO;
        this.connection = connection;
    }

    //팀이름 중복 확인 후
    public String insertTeam(int stadiumId, String name) {
        int result = teamDAO.createTeam(stadiumId, name);

        return result == 1 ? "성공" : "실패";
    }

    // 전체 팀 목록 호출 메서드
    public List<TeamRespDTO> getTeams() {
        return teamDAO.getTeamList();
    }

}



