package az.code.myauto.config.security;

import az.code.myauto.models.dtos.UserDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@Component
public class UserTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader("Authorization");
        if (auth != null) {
            String[] chunks = auth.split("\\.");
            String data = new String(Base64.getDecoder().decode(chunks[1]));
            JsonNode payload = new ObjectMapper().readValue(data, JsonNode.class);
            request.setAttribute("user",
                    UserDTO.builder()
                            .username(payload.get("preferred_username").textValue())
                            .fullName(payload.get("name").textValue())
                            .email(payload.get("email").textValue())
                            .phoneNumber(payload.get("phoneNumber").textValue())
                            .build());
        }
        return true;
    }
}