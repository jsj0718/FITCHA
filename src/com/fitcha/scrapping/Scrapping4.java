package com.fitcha.scrapping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.fitcha.model.dao.StaffDAO;
import com.fitcha.model.vo.StaffVO;

// 감독 또는 영화배우 정보
public class Scrapping4 {
    public static void main(String[] args) {

        String url = "https://movie.naver.com/movie/bi/pi/basic.naver?code=1326";
          
        Document doc = null;
        
        try {
            doc = Jsoup.connect(url).get(); // url의 html 요소 가져오기
            
            // 인물 코드
            String staffCode = url.substring(url.lastIndexOf("=")+1);
            // 이름
            Elements name = doc.select("#content .article .mv_info_area .mv_info .h_movie a");
            String nameVal = name.text();
            // 인물 사진
            Elements img = doc.select("#content .article .mv_info_area .poster img");
            String imgVal = img.attr("src");
            // 출생
            Elements birth = doc.select("#content .article .mv_info_area .mv_info .info_spec .step5 + dd");
            String birthVal = birth.text();

            System.out.println(staffCode);
            System.out.println(nameVal);
            System.out.println(imgVal);
            System.out.println(birthVal);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void selectStaff(String pcode) {
        String url = "https://movie.naver.com/movie/bi/pi/basic.naver?code=" + pcode;
        
        Document doc = null;
        
        try {
            // url의 html 요소 가져오기
            doc = Jsoup.connect(url).get(); 
            
            // 인물 코드
            String staffCode = url.substring(url.lastIndexOf("=")+1);
            // 이름
            Elements name = doc.select("#content .article .mv_info_area .mv_info .h_movie a");
            String nameVal = name.text();
            // 인물 사진
            Elements img = doc.select("#content .article .mv_info_area .poster img");
            String imgVal = img.attr("src");
            // 출생
            Elements birth = doc.select("#content .article .mv_info_area .mv_info .info_spec .step5 + dd");
            String birthVal = birth.text();
            
            
            // 스태프 객체 선언 후 값 넣기
            StaffVO svo = new StaffVO();
            svo.setStaffId(Integer.parseInt(staffCode));
            svo.setName(nameVal);
            svo.setBirth(birthVal);
            svo.setImg(imgVal);
            
            // 스태프 테이블에 데이터 넣기
            StaffDAO sdao = new StaffDAO();
            sdao.insertStaffInfo(svo);
            
//            System.out.println("------------인물 정보------------");
//            String[] staffInfoList = new String[] {staffCode, nameVal, imgVal, birthVal};
//            for(String staffInfo : staffInfoList) {
//                System.out.println(staffInfo);
//            }
        } catch(Exception e) {
            System.out.println(pcode);
            e.printStackTrace();
        }
    }
}
