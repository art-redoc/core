package art.redoc.core.dto;

import art.redoc.core.conts.ApiResultCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

public class ResultDTO<T> extends AbstractResultDTO {

    @JsonProperty(value = "data", index = 2)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    @Setter
    private T data;

    public ResultDTO() {
    }

    public ResultDTO(final String apiResultCode) {
        this.apiResultCode = apiResultCode;
    }

    public static ResultDTO<Void> success() {
        final ResultDTO<Void> result = new ResultDTO<>(ApiResultCode.SUCCESSFUL_STATUS);
        return result;
    }

    public static <T> ResultDTO<T> success(final String apiResultCode, final T data) {
        final ResultDTO<T> result = new ResultDTO<>(apiResultCode);
        result.setData(data);
        return result;
    }

    public static <T> ResultDTO<T> success(final T data) {
        final ResultDTO<T> result = new ResultDTO<>(ApiResultCode.SUCCESSFUL_STATUS);
        result.setData(data);
        return result;
    }

    public static ResultDTO<Void> failure() {
        final ResultDTO<Void> result = new ResultDTO<>(ApiResultCode.FAILED_STATUS);
        return result;
    }

    public static ResultDTO<Void> failure(final ResultError error) {
        final ResultDTO<Void> result = new ResultDTO<>(ApiResultCode.FAILED_STATUS);
        result.setError(error);
        return result;
    }

    public static <T> ResultDTO<T> failure(final T data, final ResultError error) {
        final ResultDTO<T> result = new ResultDTO<>(ApiResultCode.FAILED_STATUS);
        result.setData(data);
        result.setError(error);
        return result;
    }
}
