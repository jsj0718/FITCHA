package com.fitcha.openapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;


public class MovieAPI {
	// 상수 설정
    //   - 요청(Request) 요청 변수
    private final String REQUEST_URL = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
    private final String AUTH_KEY = SecretKey.getKobisKey();
 
    //   - 일자 포맷
    private final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyyMMdd");
 
    // 영화 이름 리스트
    public static ArrayList<String> resultList = new ArrayList<>();
    // 출력용
    public static StringBuilder result = new StringBuilder();
    
    // Map -> QueryString
    public String makeQueryString(Map<String, String> paramMap) {
        final StringBuilder sb = new StringBuilder();
 
        paramMap.entrySet().forEach(( entry )->{
            if( sb.length() > 0 ) {
                sb.append('&');
            }
            sb.append(entry.getKey()).append('=').append(entry.getValue());
        });
 
        return sb.toString();
    }
 
    // API요청
    public void requestAPI(int num) {
        // 변수설정
        //   - 하루전 날짜
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, num);
 
        // 변수 설정
        //   - 요청(Request) 인터페이스 Map
        //   - 어제자 다양성 한국영화 10개 조회
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("key"          , AUTH_KEY);                        // 발급받은 인증키
        paramMap.put("targetDt"     , DATE_FMT.format(cal.getTime()));  // 조회하고자 하는 날짜
//        paramMap.put("curPage"		, num);
        paramMap.put("itemPerPage"  , "10");                            // 결과 ROW 의 개수( 최대 10개 )
//        paramMap.put("multiMovieYn" , "Y");                             // Y:다양성 영화, N:상업영화, Default:전체
//        paramMap.put("repNationCd"  , "K");                             // K:한국영화, F:외국영화, Default:전체
 
        try {
            // Request URL 연결 객체 생성
            URL requestURL = new URL(REQUEST_URL+"?"+makeQueryString(paramMap));
            HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
 
            // GET 방식으로 요청
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
 
            // 응답(Response) 구조 작성
            //   - Stream -> JSONObject
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String readline = null;
            StringBuffer response = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                response.append(readline);
            }
 
            // JSON 객체로  변환
            JSONObject responseBody = new JSONObject(response.toString());
 
            // 데이터 추출
            JSONObject boxOfficeResult = responseBody.getJSONObject("boxOfficeResult");
 
            // 박스오피스 주제 출력
//            String boxofficeType = movieListResult.getString("boxofficeType");
//            System.out.println(boxofficeType);
 
            // 박스오피스 목록 출력
            JSONArray dailyBoxOfficeList = boxOfficeResult.getJSONArray("dailyBoxOfficeList");
            Iterator<Object> iter = dailyBoxOfficeList.iterator();
            while(iter.hasNext()) {
                JSONObject movie = (JSONObject) iter.next();
                String movieNm = (String) movie.get("movieNm");
                String audiAcc = (String) movie.get("audiAcc");
//                result.append(movieNm).append(", ").append(audiAcc).append("\n");
                
                if (!resultList.contains(movieNm) && Integer.parseInt(audiAcc) > 10000) {
                    resultList.add(movieNm);                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        // API 객체 생성
        MovieAPI api = new MovieAPI();
 
        // API 요청 (curPage는 매개변수)
        for (int i=0; i>-120; i--) {
        	api.requestAPI(i);
        }
        
//        api.requestAPI("1");
//        System.out.println(result);
                
        for (String title : resultList) {
            System.out.println(title);
        }
        
       
        
        
//        for (String title : resultList) {
//            Scrapping sc = new Scrapping();
//            sc.selectMovieList(title);
//        }
    }

}
