package model;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class Stadium {
    private int id;
    private String name;
    private Timestamp createdAt;

    @Override
    public String toString() {
        return "Stadium{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                "}\n";
    }
}
