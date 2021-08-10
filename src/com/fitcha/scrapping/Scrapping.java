package com.fitcha.scrapping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// 네이버 영화 검색 시 나오는 영화 링크 리스트 가져오기
public class Scrapping {
	public static void main(String[] args) {
	    String url = "https://movie.naver.com/movie/search/result.naver?query=" + "" + "&section=all";
        
        Document doc = null;
        
        try {
            // url의 html 요소 가져오기
            doc = Jsoup.connect(url).get();
            
            // 선택자를 이용해 요소 가져오기
            Elements movieLinkList = doc.select(".search_all .search_list_1 .result_thumb a");    
            
            // 반복문을 통해 a 태그 안에 있는 영화 상세 정보 주소 가져오기
            for (Element movieLink : movieLinkList) {
                String movieUrl = movieLink.attr("href");
                // code 부분만 추출하기
                String movieCode = movieUrl.substring(movieUrl.lastIndexOf("=") + 1);
               
                // 영화 정보를 찾는 객체 선언
                Scrapping2 sc2 = new Scrapping2();
                sc2.selectMovieInfo(movieCode);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	public void selectMovieList(String title) {
	    String url = "https://movie.naver.com/movie/search/result.naver?query=" + title + "&section=all";
	      
        Document doc = null;
        
        try {
            // url의 html 요소 가져오기
            doc = Jsoup.connect(url).get(); 
            // 선택자를 이용해 요소 가져오기
            Elements movieLinkList = doc.select(".search_all .search_list_1 .result_thumb a");
            
            // 반복문을 통해 a 태그 안에 있는 영화 상세 정보 주소 가져오기
            for (Element movieLink : movieLinkList) {
                String movieUrl = movieLink.attr("href");
                // code 부분만 추출하기
                String movieCode = movieUrl.substring(movieUrl.lastIndexOf("=") + 1);
               
                // 영화 정보를 찾는 객체 선언
                Scrapping2 sc2 = new Scrapping2();
                sc2.selectMovieInfo(movieCode);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
	}

	
}
