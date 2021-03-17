package com.party.beers.exception;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collections;
import java.util.stream.Collector;

@ControllerAdvice
public class ControllerAdviceTransform implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return !methodParameter.getParameterType().getSimpleName().equals("ResponseEntity");
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (methodParameter.getMethod() != null) {
            if (methodParameter.getMethod().getName().startsWith("save")
                    || methodParameter.getMethod().getName().startsWith("update")
                    || methodParameter.getMethod().getName().startsWith("delete")) {
                return Collections.singletonMap("result", "success");
            } else return new DataObject(o);
        }
        return o;
    }
}
