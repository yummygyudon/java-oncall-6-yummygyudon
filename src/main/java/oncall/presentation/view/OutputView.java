package oncall.presentation.view;

import oncall.application.info.WorkInfo;
import oncall.global.channel.Printer;
import oncall.global.exception.base.WorkerPlannerException;


public class OutputView {

    private static final String ASSIGNMENT_DETAIL_FOR_COMMON_DAY_FORMAT = "%d월 %d일 %s %s";
    private static final String ASSIGNMENT_DETAIL_FOR_LEGAL_HOLIDAY_FORMAT = "%d월 %d일 %s(휴일) %s";

    public void printBlank() {
        Printer.printBlankLine();
    }
    public void printErrorMessage(WorkerPlannerException exception) {
        Printer.printLine(exception.getMessage());
    }


    public void printAssignmentDetail(WorkInfo.AssignmentDetail detail) {
        String targetFormat = ASSIGNMENT_DETAIL_FOR_COMMON_DAY_FORMAT;
        if (detail.isLegalHoliday()) {
            targetFormat = ASSIGNMENT_DETAIL_FOR_LEGAL_HOLIDAY_FORMAT;
        }
        Printer.printLine(String.format(targetFormat, detail.month(), detail.date(), detail.day(), detail.workerName()));
    }

}
