package org.scoula.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.util.ResourceBundle;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { ServletConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();

        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return new Filter[] {characterEncodingFilter};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");

        // application.properties 값으로 Multipart 설정
        ResourceBundle bundle = ResourceBundle.getBundle("application");

        String location = bundle.getString("file.upload.location");
        long maxFileSize = Long.parseLong(bundle.getString("file.upload.max-file-size"));
        long maxRequestSize = Long.parseLong(bundle.getString("file.upload.max-request-size"));
        int fileSizeThreshold = Integer.parseInt(bundle.getString("file.upload.file-size-threshold"));

        MultipartConfigElement multipartConfig = new MultipartConfigElement(
                location,
                maxFileSize,
                maxRequestSize,
                fileSizeThreshold
        );
        registration.setMultipartConfig(multipartConfig);
    }
}