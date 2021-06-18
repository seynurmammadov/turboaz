package az.code.myauto.security;

import az.code.myauto.models.UserData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;

@Component
public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader("Authorization");
        if (auth != null) {
            UserData user = new UserData();
            String[] chunks = auth.split("\\.");
            Base64.Decoder decoder = Base64.getDecoder();
            String data = new String(decoder.decode(chunks[1]));
            JsonNode payload = new ObjectMapper().readValue(data, JsonNode.class);
            user.setFullName(payload.get("name").textValue());
            user.setPhoneNumber(payload.get("phoneNumber").textValue());
            user.setEmail(payload.get("email").textValue());
            user.setRegisterTime(
                    LocalDateTime.ofEpochSecond(payload.get("createdDate").longValue(),
                            0,
                            ZoneOffset.ofHours(4)));
            request.setAttribute("user", user);
            return true;
        }
        return false;
    }
}