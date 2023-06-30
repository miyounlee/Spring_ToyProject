
import db.DBConnection;
import dto.OutPlayerRespDTO;
import dao.PlayerDAO;
import model.Player;

import service.PlayerService;
import service.OutPlayerService;
import java.util.*;
import java.sql.*;


public class BaseBallApp {
    public static void main(String[] args) throws SQLException {

        Connection connection = DBConnection.getInstance();
        PlayerDAO playerDAO =new PlayerDAO(connection);
        PlayerService playerService = new PlayerService(playerDAO);

        System.out.println("어떤 기능을 요청 하시겠습니까?");
        Scanner sc =new Scanner(System.in);
        String input = sc.nextLine();


        String[] parsedInput = input.split("\\?"); //선수등록?teamId=1&name=이대호&position=1루수
        String function = parsedInput[0]; // 선수등록


        if (function.equals("선수등록")) {
            String params = parsedInput[1];
            String[] paramArray = params.split("&"); //paramArray={teamId=1,name=이대호,position=1루수}
            int teamId = Integer.parseInt(paramArray[0].split("=")[1]); //1
            String name = paramArray[1].split("=")[1]; //이대호
            String position = paramArray[2].split("=")[1]; // 1루수

            String player = playerService.createPlayer(teamId, name, position);

            System.out.println(player);

        } else if (function.equals("선수목록")) {
            int teamId = Integer.parseInt(parsedInput[1].split("=")[1]); //1

            List<Player> players = playerService.getPlayersByTeamIdList(teamId);

            System.out.println(players);

        } else if (function.equals("퇴출 등록")) {
            String params = parsedInput[1];
            String[] paramArray = params.split("&"); //paramArray={playerId=1, reason=도박}
            int playerId = Integer.parseInt(parsedInput[0].split("=")[1]); //1
            String reason = paramArray[1].split("=")[1]; //도박

            String outplayer =OutPlayerService.createOutPlayer(playerId,reason);

            System.out.println(outplayer);

        }else if (function.equals("퇴출목록")) {

            List<OutPlayerRespDTO> outplayers = OutPlayerService.getOutPlayerList( );

            System.out.println(outplayers);

        }else {
            System.out.println("지원하지 않는 기능입니다.");
        }
    }
}


