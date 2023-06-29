package model;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Player {
    private int id;
    private int teamId;
    private String name;
    private String position;
    private Timestamp createdAt;

}
