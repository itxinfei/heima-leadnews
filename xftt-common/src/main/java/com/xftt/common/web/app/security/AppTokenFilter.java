package com.xftt.common.web.app.security;


import tools.jackson.databind.ObjectMapper;
import com.xftt.common.common.contants.Contants;
import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.common.enums.AppHttpCodeEnum;
import com.xftt.model.user.pojos.ApUser;
import com.xftt.utils.jwt.AppJwtUtil;
import com.xftt.utils.threadlocal.AppThreadLocalUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

@Order(2)
@WebFilter(filterName = "appTokenFilter" ,urlPatterns = "/*")
public class AppTokenFilter extends GenericFilterBean {

    public  void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest request = (HttpServletRequest)req;
        ResponseResult<?> result = checkToken(request);
        // 测试和开发环境不过滤
        if(result==null||!Contants.isProd()){
            chain.doFilter(req,res);
        }else{
            res.setCharacterEncoding(Contants.CHARTER_NAME);
            res.setContentType("application/json");
            res.getOutputStream().write((new ObjectMapper().writeValueAsString(result)).getBytes());
        }
    }

    /**
     * 判断TOKEN信息，并设置为上下文
     * @param request
     * @return
     * 如果验证不通过则返回对应的错误，否则返回null继续执行
     */
    public ResponseResult checkToken(HttpServletRequest request){
        String token = request.getHeader("token");
        ResponseResult<?> rr = null;
        if(StringUtils.isNotEmpty(token)) {
            Claims claims = AppJwtUtil.getClaimsBody(token);
            int result = AppJwtUtil.verifyToken(claims);
            // 有效的token
            if (result == 0||result==-1) {
                ApUser user = new ApUser();
                user.setId(Long.valueOf((Integer)claims.get("id")));
                user = findUser(user);
                if(user.getId()!=null) {
                    AppThreadLocalUtils.setUser(user);
                    //验证成功 发送用户刷新消息
                    sendUserRefresh(user);
                }else{
                    rr = ResponseResult.setAppHttpCodeEnum(AppHttpCodeEnum.TOKEN_INVALID);
                }
            }else if(result==1){
                // 过期
                rr = ResponseResult.setAppHttpCodeEnum(AppHttpCodeEnum.TOKEN_EXPIRE);
            }else if(result==2){
                // TOKEN错误
                rr = ResponseResult.setAppHttpCodeEnum(AppHttpCodeEnum.TOKEN_INVALID);
            }
        }else{
            rr = ResponseResult.setAppHttpCodeEnum(AppHttpCodeEnum.TOKEN_REQUIRE);
        }
        return rr;
    }

    /**
     * 发送用户刷新消息
     * @param user
     */
    private void sendUserRefresh(ApUser user) {
        //http invoke send kafka
    }

    public ApUser findUser(ApUser user){
        user.setName("test");
        return user;
    }

}
