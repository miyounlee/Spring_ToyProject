package handler;

import lombok.AllArgsConstructor;
import service.PlayerService;
import service.StadiumService;
import service.TeamService;
import service.OutPlayerService;

import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

@AllArgsConstructor
public class RequestHandler {

    private StadiumService stadiumService;
    private TeamService teamService;
    private PlayerService playerService;
    private OutPlayerService outplayerService;

    public void requestHandler() throws SQLException {
        RequestInfo requestInfo = new RequestInfo();
        ParseRequest parseRequest = new ParseRequest();


        Scanner scanner = new Scanner(System.in);
        while (true) {
            String result = "";
            System.out.println("어떤 기능을 요청하시곘습니까? (종료)");
            String request = scanner.nextLine();

            requestInfo = parseRequest.parseRequest(request);
            String name = requestInfo.getName();
            Map<String, Object> parameterMap = requestInfo.getParameterMap();

            switch (name) {
                case "야구장목록":
                    System.out.println(stadiumService.getStadiumList());
                    break;
                case "야구장등록":
                    result = stadiumService.createStadium(String.valueOf(parameterMap.get("name")));
                    break;
                case "팀목록":
                    System.out.println(teamService.getTeamList());
                    break;
                case "팀등록":
                    result = teamService.createTeam(Integer.valueOf((String) parameterMap.get("stadiumId")),
                            String.valueOf(parameterMap.get("name")));
                    break;
                case "선수목록":
//                    System.out.println(playerService.getPlayersByTeamIdList());
                    break;
                case "선수등록":
                    result = playerService.createPlayer(Integer.valueOf((String) parameterMap.get("teamId")),
                            String.valueOf(parameterMap.get("name")),String.valueOf(parameterMap.get("position")));
                    break;
                case "포지션별목록":
                    System.out.println(playerService.getPlayerByPositions());
                    break;
                case "퇴출목록":
                    System.out.println(outplayerService.getOutPlayerList());
                    break;
                case "퇴출선수등록":
                    result = outplayerService.createOutPlayer(Integer.valueOf((String) parameterMap.get("playerId")),
                            String.valueOf(parameterMap.get("reason")));
                    break;
                case "종료":
                    System.out.println("프로그램 종료");
                    return;
                default:
                    System.out.println("요청을 다시 입력해주세요.");
            }

            System.out.println(result);
        }
    }
}