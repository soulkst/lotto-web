package dev.kirin.toy.lottoweb.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class ApiRequestFilter extends OncePerRequestFilter {
    private static final String API_URI_REGEX = "^/api/.*";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean isApiRequest = request.getRequestURI().matches(API_URI_REGEX);

        ContentCachingRequestWrapper wrappingRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappingResponse = new ContentCachingResponseWrapper(response);

        if(isApiRequest) {
            log.debug("(request) url = {}, method = {}", request.getRequestURL().toString(), request.getMethod());
        }
        filterChain.doFilter(wrappingRequest, wrappingResponse);

        if(isApiRequest) {
            log.debug("(response) responseBody : {}", new String(wrappingResponse.getContentAsByteArray()));
        }
        wrappingResponse.copyBodyToResponse();
    }


}
