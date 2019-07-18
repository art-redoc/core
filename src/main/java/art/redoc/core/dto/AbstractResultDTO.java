package art.redoc.core.dto;

import art.redoc.core.conts.ApiResultCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractResultDTO {
    /**
     * Error code.
     */
    @Getter
    @Setter
    @JsonProperty(value = "code", index = 0)
    protected String apiResultCode;

    /**
     * Error detail.
     */
    protected ResultError error;

    @JsonIgnore
    public boolean isSuccess() {
        return this.apiResultCode.equals(ApiResultCode.SUCCESSFUL_STATUS) ? true : false;
    }

    @JsonIgnore
    public boolean isFailure() {
        return this.apiResultCode.equals(ApiResultCode.SUCCESSFUL_STATUS) ? false : true;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "error", index = 1)
    public ResultError getError() {
        return this.error;
    }

    protected void setError(final ResultError error) {
        this.error = error;
    }

    @JsonIgnore
    public String errorToString() {
        if ((this.error != null)) {
            final StringBuilder builder = new StringBuilder();
            builder.append("Error : [");
            builder.append(this.error.toString());
            builder.append("]");
            return builder.toString();
        } else {
            return "error : []";
        }
    }
}
