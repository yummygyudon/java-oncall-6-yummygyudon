package oncall.global.exception;

import oncall.global.exception.base.ErrorBase;
import oncall.global.exception.base.WorkerPlannerException;

public class GlobalException extends WorkerPlannerException {
    public GlobalException(ErrorBase error) {
        super(error);
    }
}
