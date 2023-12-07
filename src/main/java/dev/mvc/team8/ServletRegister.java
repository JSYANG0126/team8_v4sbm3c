package dev.mvc.team8;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.mvc.tool.Download;

// 서블릿을 등록함.
@Configuration
public class ServletRegister {
  @Bean
  public ServletRegistrationBean getServletRegistrationBean() {


      ServletRegistrationBean registrationBean = new ServletRegistrationBean(new Download());
      registrationBean.addUrlMappings("/download"); // 접근 주소

      return registrationBean;
  }
}