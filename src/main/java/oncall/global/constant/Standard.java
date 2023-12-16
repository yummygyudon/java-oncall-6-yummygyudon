package oncall.global.constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Standard {

    // 각 월별 총 일수
    public static final Map<Integer, Integer> TOTAL_DAYS_OF_MONTH = new HashMap<>();
    static {
        TOTAL_DAYS_OF_MONTH.put(1, 31);
        TOTAL_DAYS_OF_MONTH.put(2, 28);
        TOTAL_DAYS_OF_MONTH.put(3, 31);
        TOTAL_DAYS_OF_MONTH.put(4, 30);
        TOTAL_DAYS_OF_MONTH.put(5, 31);
        TOTAL_DAYS_OF_MONTH.put(6, 30);
        TOTAL_DAYS_OF_MONTH.put(7, 31);
        TOTAL_DAYS_OF_MONTH.put(8, 31);
        TOTAL_DAYS_OF_MONTH.put(9, 30);
        TOTAL_DAYS_OF_MONTH.put(10, 31);
        TOTAL_DAYS_OF_MONTH.put(11, 30);
        TOTAL_DAYS_OF_MONTH.put(12, 31);
    }

    // 한 해의 법정 공휴일
    public static List<List<Integer>> LEGAL_HOLIDAY_OF_YEAR = List.of(
            List.of(1, 1),
            List.of(3, 1),
            List.of(5, 5),
            List.of(6, 6),
            List.of(8, 15),
            List.of(10, 3),
            List.of(10, 9),
            List.of(12, 25)
    );

    // 전체 요일명
    public static final List<String> DAYS_OF_WEEK = List.of("월", "화", "수", "목", "금", "토", "일");

    // 주말 요일명
    public static final List<String> WEEK_END= List.of("토", "일");


}
