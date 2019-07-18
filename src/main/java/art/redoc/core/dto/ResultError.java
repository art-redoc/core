package art.redoc.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultError {

    /**
     * Err msg.
     */
    private String errMsg;
    /**
     * Err code.
     */
    private String errCode;

    public ResultError() {
    }

    public ResultError(final String errMsg) {
        this.errMsg = errMsg;
    }

    public ResultError(final String errCode, final String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return String.format("{errCode:%s, errMsg:%s}", this.errCode, this.errMsg);
    }
}
