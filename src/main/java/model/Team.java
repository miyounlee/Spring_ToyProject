package model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Builder
@ToString
public class Team {
    private int id;
    private int stadiumId;
    private String name;
    private Timestamp createdAt;
}
