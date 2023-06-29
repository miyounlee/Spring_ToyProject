package dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
public class TeamRespDTO {

        private int teamId;
        private String teamName;
        private int stadiumId;
        private String stadiumName;


        @Override
        public String toString() {
                return "TeamRespDTO{" +
                        "teamId=" + teamId +
                        ", teamName='" + teamName + '\'' +
                        ", stadiumId=" + stadiumId +
                        ", stadiumName='" + stadiumName + '\'' +
                        "}\n";
        }
}


