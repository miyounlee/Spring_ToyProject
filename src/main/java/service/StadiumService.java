package service;

import dao.StadiumDAO;
import model.Stadium;

import java.sql.Connection;
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
        int result = stadiumDAO.createStadium(name);
        return result == 1 ? "성공" : "실패";
    }

    public List<Stadium> getStadiums() {
        return stadiumDAO.getStadiumList();
    }
}
