package oncall.application;

import oncall.application.info.WorkInfo;
import oncall.global.constant.Standard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorkerPlannerSystem {
    private final List<WorkInfo.AssignmentDetail> assignmentDetails;
    private int targetMonth;
    private int endDateOfMonth;
    private List<String> weekdayWorkers;
    private List<String> holidayWorkers;
    private int indexOfDay;
    private int indexOfWeekDayWorker;
    private int indexOfHolidayWorker;

    public WorkerPlannerSystem() {
        this.assignmentDetails = new ArrayList<>();
    }

    public void registerPeriod(WorkInfo.TargetPeriodInfo periodInfo) {
        int month = periodInfo.month();
        String startDay = periodInfo.startDay();
        this.targetMonth = month;
        this.endDateOfMonth = Standard.TOTAL_DAYS_OF_MONTH.get(targetMonth);
        this.indexOfDay = Standard.DAYS_OF_WEEK.indexOf(startDay);

    }
    public void registerWeekdayWorker(WorkInfo.WorkersInfo workersInfo) {
        this.weekdayWorkers = workersInfo.workerNames();
        this.indexOfWeekDayWorker = 0;

    }
    public void registerHolidayWorker(WorkInfo.WorkersInfo workersInfo) {
        this.holidayWorkers = workersInfo.workerNames();
        this.indexOfHolidayWorker = 0;
    }

    public List<WorkInfo.AssignmentDetail> getTotalPlan() {
        return Collections.unmodifiableList(assignmentDetails) ;
    }

    public void planSchedule() {
        int currentDate = 1;
        while (assignmentDetails.size() < endDateOfMonth) {
            fetchAllIndex();
            planForDate(currentDate);

            currentDate++;
        }
    }

    private void planForDate(int currentDate) {
        String targetDay = Standard.DAYS_OF_WEEK.get(indexOfDay);
        String targetWorker = "";
        targetWorker = pickWorker(currentDate, targetDay, targetWorker);
        assignmentDetails.add(
                new WorkInfo.AssignmentDetail(
                        targetMonth, currentDate, targetDay, isLegalHoliday(targetMonth, currentDate),targetWorker
                )
        );
        indexOfDay++;
    }

    private String pickWorker(int currentDate, String targetDay, String targetWorker) {
        boolean isHoliday = isHoliday(targetMonth, currentDate, targetDay);
        if (isHoliday) {
            targetWorker = holidayWorkers.get(indexOfHolidayWorker);
            indexOfHolidayWorker ++ ;
        }
        if (!isHoliday) {
            targetWorker = weekdayWorkers.get(indexOfWeekDayWorker);
            indexOfWeekDayWorker ++ ;
        }
        return targetWorker;
    }

    private void fetchAllIndex() {
        // 요일 한바퀴 돌았을 때
        if (indexOfDay > 6) {
            indexOfDay = 0;
        }
        // 평일 배치자가 한 바퀴 돌았을 때
        if (indexOfWeekDayWorker >= weekdayWorkers.size()) {
            indexOfWeekDayWorker = 0;
        }
        // 주말 배치자 한 바퀴 돌았을 때
        if (indexOfHolidayWorker >= holidayWorkers.size()) {
            indexOfHolidayWorker =  0;
        }
    }

    private boolean isLegalHoliday(int month, int date) {
        return Standard.LEGAL_HOLIDAY_OF_YEAR.stream()
                .anyMatch(holiday -> holiday.get(0) == month && holiday.get(1) == date);
    }
    private boolean isHoliday(int month, int date, String day) {
        boolean isLegalHoliday =isLegalHoliday(month, date);
        boolean isWeekend = Standard.WEEK_END.contains(day);
        return isLegalHoliday || isWeekend;
    }

}
