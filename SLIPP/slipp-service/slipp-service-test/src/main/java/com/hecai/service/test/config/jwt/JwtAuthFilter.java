package com.hecai.service.test.config.jwt;

import com.alibaba.fastjson.JSONObject;
import com.hecai.common.config.restdata.Code;
import com.hecai.common.config.restdata.RestData;
import com.hecai.common.util.ExpiryMap;
import com.hecai.common.util.HttpContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    private static final PathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    JwtUtil jwtUtil;
    @Value("${token.flag}")
    private String flag;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //是否开启请求验证
        if (flag.equals("true")){
            if (request.getMethod().equals("OPTIONS")) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = request.getHeader("Authorization");
            String userId = request.getHeader("userId");

            ServletContext servletContext = request.getSession().getServletContext();
            ExpiryMap<String, String> loginMap = (ExpiryMap<String, String>) servletContext.getAttribute("loginMap");

            if (!isProtectedUrl(request)) {
                try {
                    //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
                    jwtUtil.validateToken(token);
                } catch (Exception e) {
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("application/json;charset=utf-8");
                    JSONObject json = (JSONObject) JSONObject.toJSON(new RestData<>(Code.INFO_ERROR_1003));
                    response.getWriter().write(json.toJSONString());
                    return;
                }

                if (loginMap==null){
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("application/json;charset=utf-8");
                    JSONObject json = (JSONObject) JSONObject.toJSON(new RestData<>(Code.INFO_ERROR_1002));
                    response.getWriter().write(json.toJSONString());
                    return;
                }

                String mapToken = loginMap.get(userId);
                if (mapToken==null){
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("application/json;charset=utf-8");
                    JSONObject json = (JSONObject) JSONObject.toJSON(new RestData<>(Code.INFO_ERROR_1002));
                    response.getWriter().write(json.toJSONString());
                    return;
                }

                if (!token.equals(mapToken)) {
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("application/json;charset=utf-8");
                    JSONObject json = (JSONObject) JSONObject.toJSON(new RestData<>(Code.INFO_ERROR_1002,"账号在其他地方登录！",null));
                    response.getWriter().write(json.toJSONString());
                    return;
                }else {
                    String body = HttpContextUtils.getBodyString(request);
                    log.info("来自ip：{} ,来自用户：{} ,请求地址：{} ,请求参数：{}",request.getHeader("ipAddr"),userId,request.getRequestURI(),body);
                }

                //刷新跃活时间
                loginMap.put(userId, token, 60 * 60 * 1000);
                servletContext.setAttribute("loginMap", loginMap);

            }
        }

        //如果jwt令牌通过了检测, 那么就把request传递给后面的RESTful api
        filterChain.doFilter(request, response);
    }


    //放行的api
    private boolean isProtectedUrl(HttpServletRequest request) {
//        return pathMatcher.match("/admin/**", request.getServletPath());
        List<String> list = Arrays.asList(
                "/admin/auth/login",
                "/admin/home/homeTop",
                "/admin/auth/exit"
//                "/doc**",
//                "/doc.html#/webapp**",
//                "/swagger**",
//                "/api-docs**"
        );

        for (String s : list) {
            if (pathMatcher.match(s,request.getServletPath())) return true;
        }

        return false;
    }

}