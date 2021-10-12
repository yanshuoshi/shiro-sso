package com.example.project_b.shiro;

import com.example.project_b.core.entity.User;
import com.example.project_b.core.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm类（Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。）
 *      1、用户身份认证（即登陆验证）
 *      2、权限认证授权
 *
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 该方法具体操作有：1、检查提交的进行认证的令牌信息
     * 2、根据令牌信息从数据源(通常为数据库)中获取用户信息
     * 3、对用户信息进行匹配验证。
     * 4、验证通过将返回一个封装了用户信息的 SimpleAuthenticationInfo 实例。
     * 5、验证失败则抛出 AuthenticationException 异常信息。
     *
     * @description 获取身份验证信息(即登陆验证)： ( 执行完Subject.login() 后进入此方法)
     * @param token 用户身份信息 token
     * @return      返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("————身份认证方法————");

        // 1、根据token 获取用户提交用户信息
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        // 2、根据令牌信息从数据源(通常为数据库)中获取用户信息
        User user = userService.findByUserName(userName);

        // 3、对用户信息进行匹配验证。
        if (user == null) {
            //throw new UserException(ResponseCodeEnum.USER_USERNAME_ERROR);
        } else {
            String userPassword = user.getUserPassword();
            if (!userPassword.equals(password)) {
                //throw new UserException(ResponseCodeEnum.USER_PASSWORD_ERROR);
            }
        }

        // 4、验证通过将返回一个封装了用户信息的 SimpleAuthenticationInfo 实例。
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userName,      // 用户名
                password,      // 密码
                getName()      // realm name
        );
        return authenticationInfo;
    }

    /**
     * @description 获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        System.out.println("————权限认证方法————");

        return null;
    }

}
