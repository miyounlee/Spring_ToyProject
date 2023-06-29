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

    public String insertTeam(int stadiumId, String name) {
        if (isValidation(name)) {
            return "이미 존재하는 팀입니다.";
        }

        int result = teamDAO.createTeam(stadiumId, name);

        return result == 1 ? "성공" : "실패";
    }

    // 중복 확인
    private boolean isValidation(String name) {
        List<TeamRespDTO> teamList = teamDAO.getTeamList();

        for (TeamRespDTO team : teamList) {
            if (team.getTeamName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // 전체 팀 목록 호출 메서드
    public List<TeamRespDTO> getTeams() {
        return teamDAO.getTeamList();
    }

}



