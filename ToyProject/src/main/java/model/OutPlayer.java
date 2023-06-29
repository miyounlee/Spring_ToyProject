package model;

import java.sql.Timestamp;
import lombok.*;
@AllArgsConstructor
@ToString
@Builder
@Getter
public class OutPlayer {
    private int id;
    private int playerId;
    private String reason;
    private Timestamp createdAt;
}
