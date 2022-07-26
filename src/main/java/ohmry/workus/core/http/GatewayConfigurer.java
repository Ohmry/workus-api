package ohmry.workus.core.http;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * 모든 요청에 대해서 인증 및 권한을 확인할 수 있도록 Interceptor를 등록하는 설정 클래스
 * @version 0.1
 */
@Configuration
public class GatewayConfigurer implements WebMvcConfigurer {
    private final AuthenticationInterceptor authenticationInterceptor;
    private final RequestDenyInterceptor requestDenyInterceptor;

    public GatewayConfigurer(AuthenticationInterceptor authenticationInterceptor,
                             RequestDenyInterceptor requestDenyInterceptor) {
        this.authenticationInterceptor = authenticationInterceptor;
        this.requestDenyInterceptor = requestDenyInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                        .addPathPatterns("/user", "/group");
        registry.addInterceptor(requestDenyInterceptor)
                        .excludePathPatterns("/signup",  "/signin", "/user/**", "/error", "/console/**");
    }
}
