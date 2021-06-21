package az.code.myauto.config;


import az.code.myauto.config.security.UserTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BaseConfig implements WebMvcConfigurer {
    final
    UserTokenInterceptor productServiceUserTokenInterceptor;

    public BaseConfig(UserTokenInterceptor productServiceUserTokenInterceptor) {
        this.productServiceUserTokenInterceptor = productServiceUserTokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(productServiceUserTokenInterceptor);
    }
}

