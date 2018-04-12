package com.arawn.blog.cms.resolver;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 * Created by ArawN on 2017/11/19.
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    private static Logger logger = Logger.getLogger(GlobalExceptionResolver.class);

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        logger.error("系统异常", exception);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/errorPage");
        return modelAndView;
    }
}
