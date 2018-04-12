package com.arawn.blog.cms.controller;

import com.arawn.blog.cms.entity.Manager;
import com.arawn.blog.cms.util.Md5Util;
import com.arawn.blog.cms.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 管理员Controller
 */
@Controller
@RequestMapping("/myManager")
public class ManagerController {

    /**
     * 登录
     * @param manager
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public String login(Manager manager, HttpServletResponse response) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(manager.getUsername(), Md5Util.md5(manager.getPassword(), Md5Util.SALT));
        JSONObject result = new JSONObject();
        try {
            subject.login(token);
            result.put("success", true);
        } catch (Exception e) {
            result.put("success", false);
            result.put("errorInfo", "用户名或密码错误");
        }
        ResponseUtil.write(response, result);
        return null;
    }
}
