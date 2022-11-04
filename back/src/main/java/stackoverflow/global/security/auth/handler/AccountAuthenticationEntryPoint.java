package stackoverflow.global.security.auth.handler;

import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import stackoverflow.global.exception.dto.ErrorResponse;
import stackoverflow.global.exception.exceptionCode.ExceptionCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccountAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ExceptionCode exception = (ExceptionCode) request.getAttribute("exception");
        if (exception.equals(ExceptionCode.ACCESS_TOKEN_EXPIRATION)) {
            Gson gson = new Gson();
            ExceptionCode exceptionCode = ExceptionCode.ACCESS_TOKEN_EXPIRATION;
            ErrorResponse errorResponse = new ErrorResponse(exceptionCode.getStatus(), "BusinessLogicException", exceptionCode.getMessage());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(exceptionCode.getStatus());
            response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
        }
    }
}
