package com.fitcha.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarTest {
    public static void main(String[] args) {

        SimpleDateFormat dateFormat;
//        dateFormat = new SimpleDateFormat("yyyyMM"); // 년월 표시
        dateFormat = new SimpleDateFormat("yyyyMMdd"); // 년월일 표시

        Calendar cal = Calendar.getInstance();

        cal.set(2019, 1 - 1, 1); // 종료 날짜 셋팅
        String endDate = dateFormat.format(cal.getTime());

        cal.set(2018, 1 - 1, 1); // 시작 날짜 셋팅
        String startDate = dateFormat.format(cal.getTime());

        int i = 0;

        while (!startDate.equals(endDate)) { // 다르다면 실행, 동일 하다면 빠져나감

            if (i == 0) { // 최초 실행 출력
                System.out.println(dateFormat.format(cal.getTime()));
            }

//            cal.add(Calendar.MONTH, 1); // 1달 더해줌
            cal.add(Calendar.DATE, 1); //1일 더해줌
            startDate = dateFormat.format(cal.getTime()); // 비교를 위한 값 셋팅

            // +1달 출력
            System.out.println(dateFormat.format(cal.getTime()));

            i++;

        }

    }
}
