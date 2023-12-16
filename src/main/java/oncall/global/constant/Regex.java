package oncall.global.constant;

public abstract class Regex {
    /**
     * 입력 형식
     */
    // 날짜 입력
    public static String REGEX_PATTERN_FOR_PERIOD_INPUT = "([0-9]+,[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]+)";

    public static String REGEX_PATTERN_FOR_WORKERS_INPUT = "^([\\s]*[\\s]*[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]+[\\s]*)(,[\\s]*[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]+[\\s]*)*(,[\\s]*[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]+[\\s]*)$";

}
