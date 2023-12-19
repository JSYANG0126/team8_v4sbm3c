package dev.mvc.team8;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import dev.mvc.movie.Movie;
import dev.mvc.review.Review;
import dev.mvc.tool.Tool;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // JSP 인식되는 경로: http://localhost:9093/movie/storage";
        registry.addResourceHandler("/movie/storage/**").addResourceLocations("file:///" +  Movie.getUploadDir());
        

        registry.addResourceHandler("/review/storage/**").addResourceLocations("file:///" +  Review.getUploadDir());
        
        // JSP 인식되는 경로: http://localhost:9091/attachfile/storage";
        // registry.addResourceHandler("/contents/storage/**").addResourceLocations("file:///" +  Tool.getOSPath() + "/attachfile/storage/");
        
        // JSP 인식되는 경로: http://localhost:9091/member/storage";
        // registry.addResourceHandler("/contents/storage/**").addResourceLocations("file:///" +  Tool.getOSPath() + "/member/storage/");
    }
 
}