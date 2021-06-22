package az.code.myauto.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "firebase")
public class FireBaseProperties {
    private String bucketName;
    private String imageUrl;
    private String json;
    private String jsonPath;
    private String urlFirstPart;
    private String urlSecondPart;
}
