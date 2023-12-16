package oncall;

import oncall.presentation.MonthlyWorkerPlanner;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        MonthlyWorkerPlanner monthlyWorkerPlanner = new MonthlyWorkerPlanner();
        monthlyWorkerPlanner.start();
    }
}
