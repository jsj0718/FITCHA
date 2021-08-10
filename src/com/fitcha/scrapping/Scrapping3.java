package com.fitcha.scrapping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.fitcha.model.dao.MovieAndStaffDAO;

// 영화와 출연진 관계 짓기
public class Scrapping3 {
    public static void main(String[] args) {

        String url = "https://movie.naver.com/movie/bi/mi/detail.naver?code=184318";

        Document doc = null;
        
        try {
            doc = Jsoup.connect(url).get(); // url의 html 요소 가져오기
                        
            Elements actorList = doc.select("#content .article .lst_people .p_thumb a");    // 배우 코드 리스트
            Elements directorList = doc.select("#content .article .dir_obj .dir_product .k_name");    // 감독 코드 리스트
            Elements role = doc.select(".lst_people .p_info .in_prt em");   // 배역
            Elements roleName = doc.select(".lst_people .p_info .pe_cmt span"); // 배역 이름
            
            // 영화 코드
            String movieCode = url.substring(url.lastIndexOf("=")+1);

//            ArrayList<String[]> actors = new ArrayList<>();
            for (int i=0; i<actorList.size(); i++) {
                // 역할
                String roleVal = role.get(i).text();
                // 배역명
                String roleNameVal = "";
                if (i < roleName.size() ) {
                    roleNameVal = roleName.get(i).text();                    
                } 
                // 인물 코드
                String actorUrl = actorList.get(i).attr("href");
                String actorCode = actorUrl.substring(actorUrl.lastIndexOf("=")+1);
                
                // 영화 코드, 배우 코드, 역할, 배역명 데이터 넣기
                MovieAndStaffDAO masdao = new MovieAndStaffDAO();
                masdao.insertMovieAndStaff(Integer.parseInt(movieCode), Integer.parseInt(actorCode), roleVal, roleNameVal);

                // 배우 정보 찾기
                Scrapping4 sc4 = new Scrapping4();
                sc4.selectStaff(actorCode);
                
//                String[] list = new String[] {movieCode, actorCode, roleVal, roleNameVal};
//                actors.add(list);
                
            }
            
//            ArrayList<String[]> directors = new ArrayList<>();
            for (int i=0; i<directorList.size(); i++) {
                String directorUrl = directorList.get(i).attr("href");
                String directorCode = directorUrl.substring(directorUrl.lastIndexOf("=") + 1);
                String roleVal = "감독";

                // 영화 코드, 감독 코드, 배역 넣기
                MovieAndStaffDAO masdao = new MovieAndStaffDAO();
                masdao.insertMovieAndStaff(Integer.parseInt(movieCode), Integer.parseInt(directorCode), roleVal, "");
                
                // 감독 정보 찾기
                Scrapping4 sc4 = new Scrapping4();
                sc4.selectStaff(directorCode);
                
//                String[] list = new String[] {movieCode, directorCode, roleVal};
//                directors.add(list);

            }
            
//            System.out.println("--------영화와 배우 간 관계--------");
//            for (String[] datas : actors) {
//                for (String data : datas) {
//                    System.out.print(data + ", ");
//                }
//                System.out.println();
//            }
//            
//            System.out.println("--------영화와 감독 간 관계--------");
//            for (String[] datas : directors) {
//                for (String data : datas) {
//                    System.out.print(data + ", ");
//                }
//                System.out.println();
//            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void movieAndStaff(String mcode) {
        String url = "https://movie.naver.com/movie/bi/mi/detail.naver?code=" + mcode;
        
        Document doc = null;
        
        try {
            doc = Jsoup.connect(url).get(); // url의 html 요소 가져오기
                        
            Elements actorList = doc.select("#content .article .lst_people .p_thumb a");    // 배우 코드 리스트
            Elements directorList = doc.select("#content .article .dir_obj .dir_product .k_name");    // 감독 코드 리스트
            Elements role = doc.select(".lst_people .p_info .in_prt em");   // 배역
            Elements roleName = doc.select(".lst_people .p_info .pe_cmt span"); // 배역 이름
            
            // 영화 코드
            String movieCode = url.substring(url.lastIndexOf("=")+1);

//            ArrayList<String[]> actors = new ArrayList<>();
            for (int i=0; i<actorList.size(); i++) {
                // 역할
                String roleVal = role.get(i).text();
                // 배역명
                String roleNameVal = "";
                if (i < roleName.size() ) {
                    roleNameVal = roleName.get(i).text();                    
                } 
                // 인물 코드
                String actorUrl = actorList.get(i).attr("href");
                String actorCode = actorUrl.substring(actorUrl.lastIndexOf("=")+1);
                
                // 영화 코드, 배우 코드, 역할, 배역명 데이터 넣기
                MovieAndStaffDAO masdao = new MovieAndStaffDAO();
                masdao.insertMovieAndStaff(Integer.parseInt(movieCode), Integer.parseInt(actorCode), roleVal, roleNameVal);

                // 배우 정보 찾기
                Scrapping4 sc4 = new Scrapping4();
                sc4.selectStaff(actorCode);
                
//                String[] list = new String[] {movieCode, actorCode, roleVal, roleNameVal};
//                actors.add(list);
                
            }
            
//            ArrayList<String[]> directors = new ArrayList<>();
            for (int i=0; i<directorList.size(); i++) {
                String directorUrl = directorList.get(i).attr("href");
                String directorCode = directorUrl.substring(directorUrl.lastIndexOf("=") + 1);
                String roleVal = "감독";

                // 영화 코드, 감독 코드, 배역 넣기
                MovieAndStaffDAO masdao = new MovieAndStaffDAO();
                masdao.insertMovieAndStaff(Integer.parseInt(movieCode), Integer.parseInt(directorCode), roleVal, "");
                
                // 감독 정보 찾기
                Scrapping4 sc4 = new Scrapping4();
                sc4.selectStaff(directorCode);
                
//                String[] list = new String[] {movieCode, directorCode, roleVal};
//                directors.add(list);

            }
            
//            System.out.println("--------영화와 배우 간 관계--------");
//            for (String[] datas : actors) {
//                for (String data : datas) {
//                    System.out.print(data + ", ");
//                }
//                System.out.println();
//            }
//            
//            System.out.println("--------영화와 감독 간 관계--------");
//            for (String[] datas : directors) {
//                for (String data : datas) {
//                    System.out.print(data + ", ");
//                }
//                System.out.println();
//            }

            

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
