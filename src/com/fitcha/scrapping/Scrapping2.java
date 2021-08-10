package com.fitcha.scrapping;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fitcha.model.dao.MovieAndGenreDAO;
import com.fitcha.model.dao.MovieDAO;
import com.fitcha.model.vo.MovieVO;

// 영화 정보 가져오기
public class Scrapping2 {
	public static void main(String[] args) {

		String url = "https://movie.naver.com/movie/bi/mi/basic.naver?code=185455";
	      
		Document doc = null;
        
        try {
            // url의 html 요소 가져오기
            doc = Jsoup.connect(url).get(); 
            
            // 장르, 나라, 러닝타임, 개봉일 리스트 담기
            Elements movieInfos = doc.select("#content .article .mv_info_area .mv_info .info_spec .step1 + dd p span");
            ArrayList<String> movieInfoList = new ArrayList<>();
            for (Element movieInfo : movieInfos) {
                movieInfoList.add(movieInfo.text());
            }
            
            // genre
            String genreVal = movieInfoList.get(0);
            // genre List
            String[] genreList = genreVal.split(", ");
            // country
            String countryVal = "";
            if (movieInfoList.size() > 1) {
                countryVal = movieInfoList.get(1);                
            }
            // running
            String runningTimeStr = "";
            int runningTime = 0;
            if (movieInfoList.size() > 3) {
                runningTimeStr = movieInfoList.get(2);
                runningTime = Integer.parseInt(runningTimeStr.substring(0, runningTimeStr.lastIndexOf("분")));                
            }
            // openDate
            String openDateStr = "";
            String openDate = "";
            if (movieInfoList.size() > 3) {
                openDateStr = movieInfoList.get(3);
            } else if (movieInfoList.size() == 3) {
                openDateStr = movieInfoList.get(2);
            }
            
            if (openDateStr.length() > 12) {
                String year = openDateStr.substring(0,4);
                String month = openDateStr.substring(6,8);
                String day = openDateStr.substring(9,12);                    
                openDate = year + month + day;
            } else if (openDateStr.length() > 8) {
                String year = openDateStr.substring(0,4);
                String month = openDateStr.substring(6,8);                    
                openDate = year + month;
            }
          
            // moviecode
            String movieCode = url.substring(url.lastIndexOf("=") + 1);
            // title
            Elements title = doc.select("#content .article .mv_info_area .mv_info .h_movie a");
            String titleVal = title.get(0).text();
            // subtitle
            Elements subTitle = doc.select("#content .article .mv_info_area .mv_info .h_movie2");
            String subTitleVal = subTitle.text();
            // story
            Elements story = doc.select(".story_area .con_tx");
            String storyVal = story.text();
            // poster
            Elements poster = doc.select("#content .article .mv_info_area .poster img");
            String posterVal = poster.attr("src");
            // rate(평점, 관람객 기준)
            Elements rate = doc.select(".score_area .netizen_score .sc_view .star_score em");
            String rateVal = rate.text();
            // grade
            Elements grade = doc.select("#content .article .mv_info_area .mv_info .info_spec .step4 ~ dd p a");
            String gradeVal = "";
            if (grade.size() > 0) {
                gradeVal = grade.get(0).text();
            }
            // attendance (관객수)
            Elements attendance = doc.select("#content .article .mv_info_area .mv_info .info_spec .step9 ~ dd .step9_cont .count");
            String attendanceStr = "";
            int attendanceNum = 0;
            if (attendance.size() > 0) {
                attendanceStr = attendance.get(0).text();
                attendanceNum = Integer.parseInt(attendanceStr.substring(0, attendanceStr.lastIndexOf("명")).replaceAll(",", ""));                
            }
            
            // 영화 테이블 데이터 추가, movie Table (movieCode, runningTimeStr, openDate, rateVal, attendanceStr은 형변환하기)
            // 영화 객체에 값 넣기
            MovieVO mvo = new MovieVO();
            mvo.setMovieId(Integer.parseInt(movieCode));
            mvo.setTitle(titleVal);
            mvo.setSubTitle(subTitleVal);
            mvo.setStory(storyVal);
            mvo.setPoster(posterVal);
            mvo.setCountry(countryVal);
            mvo.setRate(Double.parseDouble(rateVal));
            mvo.setGrade(gradeVal);
            
            if (runningTimeStr.equals("")) {
                mvo.setRunningTime(-1);
            } else {
                mvo.setRunningTime(runningTime);
            }
            if(openDate.equals("")) {
                mvo.setOpenDate("");
            } else {
                mvo.setOpenDate(openDate);
            }
            if(attendance.equals("")) {
                mvo.setAttendance(-1);
            } else {
                mvo.setAttendance(attendanceNum);
            }
            
            // 영화 데이터를 테이블에 넣기
            MovieDAO mdao = new MovieDAO();
            mdao.insertMovieInfo(mvo);
            
            // 영화 정보 리스트 선언 및 출력
//            String[] movieDatas = new String[] {
//                    movieCode, titleVal, subTitleVal, storyVal, posterVal, 
//                    countryVal, runningTimeStr, openDate, rateVal, gradeVal, attendanceStr
//            };
//            System.out.println("-----------영화 정보-----------");
//            for (String movieData : movieDatas) {
//                System.out.println(movieData);                
//            }
            
            // 영화 : 장르 테이블 데이터 추가 (M:N), movie-genre Table
//            System.out.println("-----------영화-장르 정보-----------");
            for (String genre : genreList) {
//                System.out.println(movieCode + ", " + changeGenreCode(genre));
                
                MovieAndGenreDAO magdao = new MovieAndGenreDAO();
                magdao.insertMovieAndGenre(Integer.parseInt(movieCode), changeGenreCode(genre));
            }
            
//            System.out.println(openDate);   // 개봉일 date 형식으로 변환
//            System.out.println(runningTime);    // 영화 시간 int 형식으로 변환
//            System.out.println(attendanceNum);  // 관객수 int 형식으로 변환
            
            // 영화와 인물 관계를 찾는 객체 선언
            Scrapping3 sc3 = new Scrapping3();
            sc3.movieAndStaff(movieCode);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
	}
	
	public void selectMovieInfo(String mcode) {
	    
	    
	    String url = "https://movie.naver.com/movie/bi/mi/basic.naver?code=" + mcode;
        
        Document doc = null;
        
        try {
            // url의 html 요소 가져오기
            doc = Jsoup.connect(url).get(); 
            
            
            
            // 장르, 나라, 러닝타임, 개봉일 리스트 담기
            Elements movieInfos = doc.select("#content .article .mv_info_area .mv_info .info_spec .step1 + dd p span");
            ArrayList<String> movieInfoList = new ArrayList<>();
            for (Element movieInfo : movieInfos) {
                movieInfoList.add(movieInfo.text());
            }
            
            // genre
            String genreVal = movieInfoList.get(0);
            // genre List
            String[] genreList = genreVal.split(", ");
            // country
            String countryVal = "";
            if (movieInfoList.size() > 1) {
                countryVal = movieInfoList.get(1);                
            }
            // running
            String runningTimeStr = "";
            int runningTime = 0;
            if (movieInfoList.size() > 3) {
                runningTimeStr = movieInfoList.get(2);
                runningTime = Integer.parseInt(runningTimeStr.substring(0, runningTimeStr.lastIndexOf("분")));                
            }
            // openDate
            String openDateStr = "";
            String openDate = "";
            if (movieInfoList.size() > 3) {
                openDateStr = movieInfoList.get(3);
            } else if (movieInfoList.size() == 3) {
                openDateStr = movieInfoList.get(2);
            }
            
            if (openDateStr.length() > 12) {
                String year = openDateStr.substring(0,4);
                String month = openDateStr.substring(6,8);
                String day = openDateStr.substring(9,12);                    
                openDate = year + month + day;
            } else if (openDateStr.length() > 8) {
                String year = openDateStr.substring(0,4);
                String month = openDateStr.substring(6,8);                    
                openDate = year + month;
            }
          
            // moviecode
            String movieCode = url.substring(url.lastIndexOf("=") + 1);
            // title
            Elements title = doc.select("#content .article .mv_info_area .mv_info .h_movie a");
            String titleVal = title.get(0).text();
            // subtitle
            Elements subTitle = doc.select("#content .article .mv_info_area .mv_info .h_movie2");
            String subTitleVal = subTitle.text();
            // story
            Elements story = doc.select(".story_area .con_tx");
            String storyVal = story.text();
            // poster
            Elements poster = doc.select("#content .article .mv_info_area .poster img");
            String posterVal = poster.attr("src");
            // rate(평점, 관람객 기준)
            Elements rate = doc.select(".score_area .netizen_score .sc_view .star_score em");
            String rateVal = rate.text();
            // grade
            Elements grade = doc.select("#content .article .mv_info_area .mv_info .info_spec .step4 ~ dd p a");
            String gradeVal = "";
            if (grade.size() > 0) {
                gradeVal = grade.get(0).text();
            }
            // attendance (관객수)
            Elements attendance = doc.select("#content .article .mv_info_area .mv_info .info_spec .step9 ~ dd .step9_cont .count");
            String attendanceStr = "";
            int attendanceNum = 0;
            if (attendance.size() > 0) {
                attendanceStr = attendance.get(0).text();
                attendanceNum = Integer.parseInt(attendanceStr.substring(0, attendanceStr.lastIndexOf("명")).replaceAll(",", ""));                
            }
            
            // 영화 테이블 데이터 추가, movie Table (movieCode, runningTimeStr, openDate, rateVal, attendanceStr은 형변환하기)
            // 영화 객체에 값 넣기
            MovieVO mvo = new MovieVO();
            mvo.setMovieId(Integer.parseInt(movieCode));
            mvo.setTitle(titleVal);
            mvo.setSubTitle(subTitleVal);
            mvo.setStory(storyVal);
            mvo.setPoster(posterVal);
            mvo.setCountry(countryVal);
            mvo.setRate(Double.parseDouble(rateVal));
            mvo.setGrade(gradeVal);
            
            if (runningTimeStr.equals("")) {
                mvo.setRunningTime(-1);
            } else {
                mvo.setRunningTime(runningTime);
            }
            if(openDate.equals("")) {
                mvo.setOpenDate("");
            } else {
                mvo.setOpenDate(openDate);
            }
            if(attendance.equals("")) {
                mvo.setAttendance(-1);
            } else {
                mvo.setAttendance(attendanceNum);
            }
            
            // 영화 데이터를 테이블에 넣기
            MovieDAO mdao = new MovieDAO();
            mdao.insertMovieInfo(mvo);
            
            // 영화 정보 리스트 선언 및 출력
//            String[] movieDatas = new String[] {
//                    movieCode, titleVal, subTitleVal, storyVal, posterVal, 
//                    countryVal, runningTimeStr, openDate, rateVal, gradeVal, attendanceStr
//            };
//            System.out.println("-----------영화 정보-----------");
//            for (String movieData : movieDatas) {
//                System.out.println(movieData);                
//            }
            
            // 영화 : 장르 테이블 데이터 추가 (M:N), movie-genre Table
//            System.out.println("-----------영화-장르 정보-----------");
            for (String genre : genreList) {
//                System.out.println(movieCode + ", " + changeGenreCode(genre));
                
                MovieAndGenreDAO magdao = new MovieAndGenreDAO();
                magdao.insertMovieAndGenre(Integer.parseInt(movieCode), changeGenreCode(genre));
            }
            
//            System.out.println(openDate);   // 개봉일 date 형식으로 변환
//            System.out.println(runningTime);    // 영화 시간 int 형식으로 변환
//            System.out.println(attendanceNum);  // 관객수 int 형식으로 변환
            
            // 영화와 인물 관계를 찾는 객체 선언
            Scrapping3 sc3 = new Scrapping3();
            sc3.movieAndStaff(movieCode);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
	}
	
	// 장르 이름을 코드로 변환해주는 메소드
	public static int changeGenreCode(String genre) {
        int result = 0;
        switch (genre) {
            case "드라마" : result = 1; break;
            case "판타지" : result = 2; break;
            case "서부" : result = 3; break;
            case "공포" : result = 4; break;
            case "로맨스" : result = 5; break;
            case "모험" : result = 6; break;
            case "스릴러" : result = 7; break;
            case "느와르" : result = 8; break;
            case "컬트" : result = 9; break;
            case "다큐멘터리" : result = 10; break;
            case "코미디" : result = 11; break;
            case "가족" : result = 12; break;
            case "미스터리" : result = 13; break;
            case "전쟁" : result = 14; break;
            case "애니메이션" : result = 15; break;
            case "범죄" : result = 16; break;
            case "뮤지컬" : result = 17; break;
            case "SF" : result = 18; break;
            case "액션" : result = 19; break;
            case "무협" : result = 20; break;
            case "에로" : result = 21; break;
            case "서스펜스" : result = 22; break;
            case "서사" : result = 23; break;
            case "블랙코미디" : result = 24; break;
            case "실험" : result = 25; break;
            case "영화카툰" : result = 26; break;
            case "영화음악" : result = 27; break;
            case "영화패러디포스터" : result = 28; break;
        }
        
        return result;
        
    }
	
}

