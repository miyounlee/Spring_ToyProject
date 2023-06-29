package dto;

import lombok.*;
import java.sql.Timestamp;

@Setter
@Getter
@Builder
public class OutPlayerRespDTO {
    private int id;
    private int playerId;
    private String name;
    private String position;
    private String reason;
    private Timestamp outcreatedAt;

    @Override
    public String toString() {
        return "OutPlayerRespDTO{" +
                "id=" + id +
                ", playerId='" + playerId + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", reason='" + reason + '\'' +
                ", createdAt=" + outcreatedAt +
                '}';
    }
}