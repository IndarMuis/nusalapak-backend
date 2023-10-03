package com.nusalapak.security;

import org.springframework.beans.factory.annotation.Value;

public class SecurityConstants {

//    @Value("${jwt.expiration")
    public static final Long JWT_EXPIRATION = 70000L;

//    @Value("${jwt.header}")
    public static final String TOKEN_HEADER = "Authorization";

//    @Value("${jwt_prefix}")
    public static String JWT_PREFIX = "Bearer ";

//    @Value("${jwt.secret")
    public static String SECRET_KEY = "NcpY92ALsniDR2K8I/YiJbl5Jl3MWlS5GbmtzMqQj0MaHgNIsjzKTNmLrDZ7Uy0C2orSFnn/gmyvYWHIXV2JtrUEpvrDKnsaAywJVvG3xrc=";

}
