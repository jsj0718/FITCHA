package com.fitcha.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fitcha.model.dbconn.DBConnect;
import com.fitcha.model.vo.CountryVO;
import com.fitcha.model.vo.GenreVO;

public class CountryDAO {
    public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (pstmt != null && !pstmt.isClosed()) {
                pstmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // 선택된 나라 조회
    public ArrayList<CountryVO> selectCountryList() {
        String SQL = "SELECT COUNTRYID, COUNTRYNAME "
                + "FROM COUNTRY";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        ArrayList<CountryVO> clist = new ArrayList<>();
        try {
            conn = DBConnect.getInstance();
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CountryVO cvo = new CountryVO();
                cvo.setCountryId(rs.getInt(1));
                cvo.setCountryName(rs.getString(2));   
                clist.add(cvo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, conn);
        }

        return clist;
    }
}
