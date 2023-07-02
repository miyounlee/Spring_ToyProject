package requestHandler;

import lombok.AllArgsConstructor;
import service.OutPlayerService;
import service.PlayerService;
import service.StadiumService;
import service.TeamService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@AllArgsConstructor
public class RequestParser {

    private StadiumService stadiumService;
    private TeamService teamService;
    private PlayerService playerService;
    private OutPlayerService outPlayerService;

    public void handleRequest() {
        Scanner scanner = new Scanner(System.in);
        RequestInfo requestInfo = new RequestInfo();

        while (true) {
            String result = "";
            System.out.println("어떤 기능을 요청하시곘습니까? (종료)");
            String request = scanner.nextLine();

            if (request.contains("종료")) {
                System.out.println("프로그램 종료");
                break;
            }

            try {
                requestInfo = parseRequest(request);
                String name = requestInfo.getName();
                Map<String, Object> parameterMap = requestInfo.getParameterMap();

                connectService(name, parameterMap);
            } catch (Exception e) {
                System.out.println("잘못 입력 하셨습니다.");
            }
        }
    }

    public RequestInfo parseRequest(String request) {
        RequestInfo requestInfo = new RequestInfo();
        Map<String, Object> parameterMap = new HashMap<>();

        if (!request.contains("?")) {
            return RequestInfo.builder()
                    .name(request)
                    .build();
        }

        try {
            String[] firstArr = request.split("\\?");
            String[] paramArr = firstArr[1].split("&");

            for (String param : paramArr) {
                parameterMap.put(param.split("=")[0], param.split("=")[1]);
            }

            requestInfo = RequestInfo.builder()
                    .name(firstArr[0])
                    .parameterMap(parameterMap)
                    .build();

            return requestInfo;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    public void connectService(String name, Map<String, Object> parameterMap) {
        String result = "";

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
            case "선수등록":
                result = playerService.createPlayer(Integer.valueOf((String) parameterMap.get("teamId")),
                        String.valueOf(parameterMap.get("name")),
                        String.valueOf(parameterMap.get("position")));
                break;
            case "선수목록":
                System.out.println(playerService.getPlayersByTeamIdList(Integer.valueOf((String) parameterMap.get("teamId"))));
                break;
            case "퇴출목록":
                System.out.println(outPlayerService.getOutPlayerList());
                break;
            case "퇴출등록":
                result = outPlayerService.createOutPlayer(Integer.valueOf((String) parameterMap.get("playerId")),
                        String.valueOf(parameterMap.get("reason")));
                break;
            case "포지션별목록":
                System.out.println(playerService.getPlayerByPositions());
                break;
            default:
                System.out.println("잘못 입력 하셨습니다.");
        }
        System.out.println(result);
    }
}

