package art.redoc.core.exceptions;

import art.redoc.core.conts.ApiResultCode;

public class CoreRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 7028619727249732142L;
    private final String code;
    private Object[] params;

    public CoreRuntimeException() {
        this.code = ApiResultCode.FAILED_STATUS;
    }

    public CoreRuntimeException(final String code) {
        this.code = code;
    }

    public CoreRuntimeException(final String code, final Object... params) {
        this.code = code;
        this.params = params;
    }

    public CoreRuntimeException(final String code, final String defaultMessage) {
        super(defaultMessage);
        this.code = code;
    }

    public CoreRuntimeException(final String code, final String defaultMessage, final Object... params) {
        super(defaultMessage);
        this.code = code;
        this.params = params;
    }

    public CoreRuntimeException(final String code, final String defaultMessage, final Throwable cause) {
        super(defaultMessage, cause);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public Object[] getParams() {
        return this.params;
    }

}
