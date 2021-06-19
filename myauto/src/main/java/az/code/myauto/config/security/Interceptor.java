package az.code.myauto.config.security;

import az.code.myauto.models.UserData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@Component
public class Interceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader("Authorization");
        if (auth != null) {
            String[] chunks = auth.split("\\.");
            String data = new String(Base64.getDecoder().decode(chunks[1]));
            JsonNode payload = new ObjectMapper().readValue(data, JsonNode.class);

            UserData user = new UserData();

            user.setFullName(payload.get("name").textValue());
            user.setPhoneNumber(payload.get("phoneNumber").textValue());
            user.setUsername(payload.get("preferred_username").textValue());
            user.setEmail(payload.get("email").textValue());
            request.setAttribute("user", user);
            return true;
        }
        return true;
    }
}