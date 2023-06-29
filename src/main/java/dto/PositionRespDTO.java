package dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class PositionRespDTO {

    private List<String> teamList;
    private Map<String, List<String>> positionMap;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("team : ").append(teamList).append("\n");

        for (Map.Entry<String, List<String>> entry : positionMap.entrySet()) {
            builder.append(entry.getKey()).append(" : ");
            builder.append(entry.getValue()).append("\n");
        }
        return builder.toString();
    }
}
