package service;

import dao.StadiumDAO;
import model.Stadium;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

public class StadiumService {
    private StadiumDAO stadiumDAO;
    private Connection connection;

    public StadiumService(StadiumDAO stadiumDAO, Connection connection) {
        this.stadiumDAO = stadiumDAO;
        this.connection = connection;
    }

    public StadiumService(Connection connection) {
        this.connection = connection;
    }

    public String insertStadium(String name) {
        // 유효성 검사
        if (isValidation(name)) {
            return "이미 존재하는 야구장입니다.";
        }

        int result = stadiumDAO.createStadium(name);
        return result == 1 ? "성공" : "실패";
    }

    // 중복 확인
    private boolean isValidation(String name) {
        List<Stadium> stadiumList = stadiumDAO.getStadiumList();

        for ( Stadium stadium : stadiumList) {
            if(stadium.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public List<Stadium> getStadiums() {
        return stadiumDAO.getStadiumList();
    }
}
