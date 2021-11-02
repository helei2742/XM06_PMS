package org.xm06.pms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan
public class InterceptorConfig implements WebMvcConfigurer {


    /**
     * 静态文件映射
     * addResourceHandler指的是对外暴露的访问路径，addResourceLocations指的是文件放置的目录
     * 映射之后，Controller返回到 html ，使用映射后的路径
     * 如下将 static 下的静态文件映射到根路径下，
     * 文件实际路径 /static/semantic/semantic.min.js 映射之后
     * 在 html 中就可以通过 semantic/semantic.min.js 路径访问静态文件
     *
     * 也可映射绝对路径,例如下面注释，把D盘static中的静态文件，映射到项目根路径下
     * */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/templates/static/");
        registry.addResourceHandler("/index/static/**")
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/templates/static/");
    }

}