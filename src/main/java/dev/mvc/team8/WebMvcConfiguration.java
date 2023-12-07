package dev.mvc.team8;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import dev.mvc.movie.Movie;
import dev.mvc.tool.Tool;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
<<<<<<< HEAD
        // Windows: path = "C:/kd/deploy/MR_v2sbm3c_blog/mreview/storage";
        // ▶ file:///C:/kd/deploy/MR_v2sbm3c_blog/mreview/storage
      
        // Ubuntu: path = "/home/ubuntu/deploy/MR_v2sbm3c_blog/mreview/storage";
        // ▶ file:////home/ubuntu/deploy/MR_v2sbm3c_blog/mreview/storage
      
        // JSP 인식되는 경로: http://localhost:9092/mreview/storage";
        registry.addResourceHandler("/mreview/storage/**").addResourceLocations("file:///" +  Movie.getUploadDir());
        
        // JSP 인식되는 경로: http://localhost:9092/attachfile/storage";
        // registry.addResourceHandler("/mreview/storage/**").addResourceLocations("file:///" +  Tool.getOSPath() + "/attachfile/storage/");
        
        // JSP 인식되는 경로: http://localhost:9092/member/storage";
        // registry.addResourceHandler("/mreview/storage/**").addResourceLocations("file:///" +  Tool.getOSPath() + "/member/storage/");
=======
      
        // JSP 인식되는 경로: http://localhost:9093/movie/storage";
        registry.addResourceHandler("/movie/storage/**").addResourceLocations("file:///" +  Movie.getUploadDir());
        
        // JSP 인식되는 경로: http://localhost:9091/attachfile/storage";
        // registry.addResourceHandler("/contents/storage/**").addResourceLocations("file:///" +  Tool.getOSPath() + "/attachfile/storage/");
        
        // JSP 인식되는 경로: http://localhost:9091/member/storage";
        // registry.addResourceHandler("/contents/storage/**").addResourceLocations("file:///" +  Tool.getOSPath() + "/member/storage/");
>>>>>>> 1c06a85fbf9235f66b5c5eb3dcc0d48d5d5f20a7
    }
 
}