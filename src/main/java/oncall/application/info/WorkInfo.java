package oncall.application.info;

import java.util.List;

public abstract class WorkInfo {
    public record TargetPeriodInfo(
            int month,
            String startDay
    ) {
    }
    public record WorkersInfo(
            List<String> workerNames
    ) {
    }

    public record AssignmentDetail(

            int month,
            int date,
            String day,
            boolean isLegalHoliday,
            String workerName
    ) {
    }

}
