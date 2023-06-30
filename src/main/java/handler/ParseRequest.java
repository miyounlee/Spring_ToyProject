package handler;

import java.util.*;

public class ParseRequest {

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
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

}
