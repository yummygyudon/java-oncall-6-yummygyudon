package oncall.global.exception.base;

public class WorkerPlannerException extends IllegalArgumentException{
    private static final String ERROR_MESSAGE_HEADER = "[ERROR] ";

    public WorkerPlannerException(ErrorBase error) {
        super(ERROR_MESSAGE_HEADER + error.getErrorMessage());
    }

}
