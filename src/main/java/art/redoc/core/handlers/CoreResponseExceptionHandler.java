package art.redoc.core.handlers;

import art.redoc.core.dto.ResultDTO;
import art.redoc.core.dto.ResultError;
import art.redoc.core.exceptions.CoreRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Slf4j
@ControllerAdvice
public class CoreResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    protected MessageSource messageSource;

    /**
     * (CustomRuntimeException)
     */
    @ExceptionHandler(CoreRuntimeException.class)
    public ResponseEntity<Object> exceptionHandler(final CoreRuntimeException ex, final HttpServletRequest request) {
        final String method = request.getMethod();
        final String url = request.getRequestURL().toString();

        log.error(method + " " + url, ex);

        final String code = ex.getCode().toString();
        final String message = this.getLocalMessage(code, ex.getMessage(), ex.getParams());

        log.error("[" + code + "] - " + message);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final ResultDTO<Void> resultDTO = ResultDTO.failure(new ResultError(code, message));

        return new ResponseEntity<>(resultDTO, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected String getLocalMessage(final String code, final String defaultMsg, final Object... params) {
        final Locale local = LocaleContextHolder.getLocale();
        return this.messageSource.getMessage(code, params, defaultMsg, local);
    }
}
