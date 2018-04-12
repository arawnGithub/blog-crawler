package com.arawn.blog.cms.realm;

import com.arawn.blog.cms.entity.Manager;
import com.arawn.blog.cms.service.ManagerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class MyRealm extends AuthorizingRealm {

    @Resource
    private ManagerService managerService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        Manager manager = managerService.getByUsername(username);
        if (manager != null) {
            SecurityUtils.getSubject().getSession().setAttribute("currentUser", manager);
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(manager.getUsername(), manager.getPassword(), "xxx");
            return authenticationInfo;
        }
        return null;
    }

}