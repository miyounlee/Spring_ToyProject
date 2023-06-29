package model;

import java.sql.Timestamp;
import lombok.*;

@AllArgsConstructor
@ToString
@Builder
@Getter
public class Player {
    private Integer id;
    private Integer teamId;
    private String name;
    private String position;
    private Timestamp createdAt;
}