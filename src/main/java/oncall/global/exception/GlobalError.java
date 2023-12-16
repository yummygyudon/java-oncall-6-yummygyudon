package oncall.global.exception;

import oncall.global.exception.base.WorkerPlannerError;

public enum GlobalError implements WorkerPlannerError {
    UNAVAILABLE_INPUT("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    ;

    private final String errorMessage;

    GlobalError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
