
package dto;
import java.sql.Timestamp;
import lombok.*;

@Getter
@Setter
public class PlayerRespDTO {
    private int id;
    private int teamId;
    private String name;
    private String position;
    private Timestamp createdAt;

    @Override
    public String toString() {
        return "OutPlayerRespDTO{" +
                "id=" + id +
                "teamId" + teamId +
                ", playerName='" + name + '\'' +
                ", position='" + position + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
