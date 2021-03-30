package io.intelligence.ppmtool.security;

public class SecurityConstraints {
  public static final String SIGN_UP_URLS = "/api/users/**";
  public static final String H2_URL = "h2-console/**";
  public static final String SECRET_KEY = "SecretKeyToGenerateJWTs";
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";
  public static final long TOKEN_EXPIRATION_TIME = 1000000; //300 Seconds
}
