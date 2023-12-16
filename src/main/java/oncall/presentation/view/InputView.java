package oncall.presentation.view;

import oncall.application.info.WorkInfo;
import oncall.global.channel.Printer;
import oncall.global.channel.Reader;
import oncall.global.constant.Regex;
import oncall.global.constant.Standard;
import oncall.global.exception.GlobalError;
import oncall.global.exception.GlobalException;
import oncall.global.message.Ask;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    private static final String DEFAULT_SEPARATOR = ",";

    public WorkInfo.TargetPeriodInfo readTargetPeriod() {
        Printer.print(Ask.PERIOD);
        String periodInput = Reader.read().trim();
        validatePeriodInput(periodInput);
        String[] monthAndDay = periodInput.split(DEFAULT_SEPARATOR);
        int month = Integer.parseInt(monthAndDay[0].trim());
        String startDay = monthAndDay[1].trim();
        validateAvailablePeriod(month, startDay);
        return new WorkInfo.TargetPeriodInfo(month, startDay);
    }

    private void validatePeriodInput(String periodInput) {
        if (!periodInput.matches(Regex.REGEX_PATTERN_FOR_PERIOD_INPUT)) {
            throw new GlobalException(GlobalError.UNAVAILABLE_INPUT);
        }
    }

    private void validateAvailablePeriod(int month, String startDay) {
        if (month < 1 || month > 12) {
            throw new GlobalException(GlobalError.UNAVAILABLE_INPUT);
        }
        if (!Standard.DAYS_OF_WEEK.contains(startDay)) {
            throw new GlobalException(GlobalError.UNAVAILABLE_INPUT);
        }
    }

    public WorkInfo.WorkersInfo readCommonDayWorkers() {
        Printer.print(Ask.COMMON_DAYS_WORKERS);
        String workersInput = Reader.read().trim();
        validateWorkersInput(workersInput);
        List<String> names = splitNames(workersInput);
        return new WorkInfo.WorkersInfo(names);
    }
    public WorkInfo.WorkersInfo readHolidayWorkers() {
        Printer.print(Ask.HOLIDAYS_WORKERS);
        String workersInput = Reader.read().trim();
        validateWorkersInput(workersInput);
        List<String> names = splitNames(workersInput);
        validateWorkersDuplicate(names);
        return new WorkInfo.WorkersInfo(names);
    }
    private void validateWorkersInput(String workersInput) {
        if (!workersInput.matches(Regex.REGEX_PATTERN_FOR_WORKERS_INPUT)) {
            throw new GlobalException(GlobalError.UNAVAILABLE_INPUT);
        }
    }

    private void validateWorkersDuplicate(List<String> workersName) {
        long uniqueNameQuantity = workersName.stream()
                .distinct()
                .count();
        if (workersName.size() != uniqueNameQuantity) {
            throw new GlobalException(GlobalError.UNAVAILABLE_INPUT);
        }
    }

    private List<String> splitNames(String namesInput) {
        return Arrays.stream(namesInput.split(DEFAULT_SEPARATOR))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        InputView inputView = new InputView();
        System.out.println(inputView.readTargetPeriod());
        System.out.println(inputView.readCommonDayWorkers());
        System.out.println(inputView.readHolidayWorkers());
    }

}
