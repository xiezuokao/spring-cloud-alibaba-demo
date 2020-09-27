package com.hecai.service.test.config.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    /** 签名密匙 */
    @Value("${token.key}")
    private String SECRET;

    /** 启用认证状态 */
    @Value("${token.status}")
    private boolean status;

    /** token有效时长 */
    @Value("${token.timeOut}")
    private long timeOut;

    /**
     * 生成Token
     * @param username
     * @return
     */
    public String generateToken(String username) {
    	long nowMillis=System.currentTimeMillis();
    	//有效时长
    	long validMillis=timeOut;
    	Date date=new Date(nowMillis);
        HashMap<String, Object> map = new HashMap<>();
        //you can put any data in the map
        map.put("username", username);
        
        JwtBuilder builder=Jwts.builder()
        		.setClaims(map)//设置声明，一般添加不敏感信息
        		.setIssuedAt(date)//设置签发时间
        		.setNotBefore(date)//设置Token在这个时间之前不被承认
        		.setExpiration(new Date(nowMillis+validMillis))//设置过期时间
        		.signWith(SignatureAlgorithm.HS512, SECRET);//设置签名使用的签名算法和签名使用的秘钥
        return "Bearer "+builder.compact();//jwt前面一般都会加Bearer
    }

    //检查Token是否有效
    public void validateToken(String token) {
        if (!status){
            return;
        }
        try {
            // parse the token.
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace("Bearer ",""))
                    .getBody();
        }catch (Exception e){
            throw new IllegalStateException("Invalid Token. "+e.getMessage());
        }
    }
}