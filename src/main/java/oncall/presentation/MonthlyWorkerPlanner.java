package oncall.presentation;

import oncall.application.WorkerPlannerSystem;
import oncall.application.info.WorkInfo;
import oncall.global.exception.base.WorkerPlannerException;
import oncall.presentation.view.InputView;
import oncall.presentation.view.OutputView;

import java.util.List;

public class MonthlyWorkerPlanner {

    private final InputView inputView;
    private final OutputView outputView;
    private final WorkerPlannerSystem plannerSystem;

    public MonthlyWorkerPlanner() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.plannerSystem = new WorkerPlannerSystem();
    }

    public void start() {
        askPeriod();
        askWorkers();

        plan();
    }

    // 비상 근무 월 정보 받기
    private void askPeriod() {
        boolean isAvailable = false;
        while (!isAvailable) {
            try {
                WorkInfo.TargetPeriodInfo targetPeriodInfo = inputView.readTargetPeriod();
                plannerSystem.registerPeriod(targetPeriodInfo);
                isAvailable = true;
            } catch (WorkerPlannerException exception) {
                outputView.printErrorMessage(exception);
            }
        }
    }


    // 평일 & 휴일 같은 메서드에서 받기 ( 평일 받기로 돌아가야함)
    private void askWorkers() {
        boolean isAvailable = false;
        while (!isAvailable) {
            try {
                WorkInfo.WorkersInfo weekdayWorkersInfo = inputView.readCommonDayWorkers();
                WorkInfo.WorkersInfo holidayWorkersInfo = inputView.readHolidayWorkers();
                plannerSystem.registerWeekdayWorker(weekdayWorkersInfo);
                plannerSystem.registerHolidayWorker(holidayWorkersInfo);
                isAvailable = true;
            } catch (WorkerPlannerException exception) {
                outputView.printErrorMessage(exception);
            }
        }
    }

    private void plan() {
        plannerSystem.planSchedule();
        List<WorkInfo.AssignmentDetail> totalPlan = plannerSystem.getTotalPlan();

        outputView.printBlank();
        for (WorkInfo.AssignmentDetail assignmentDetail : totalPlan) {
            outputView.printAssignmentDetail(assignmentDetail);
        }
    }

}
