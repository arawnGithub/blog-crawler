package com.arawn.blog.cms.util;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 响应工具类
 */
public class ResponseUtil {

    /**
     * 向客户端输出对象
     * @param response
     * @param o
     * @throws Exception
     */
    public static void write(HttpServletResponse response, Object o) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }
}
