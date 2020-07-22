package com.success.websocket.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Constants {
  private static final String[] SPRING_SECURITY_ALLOWED_URLS =
      new String[] {
        "/p/**",
        "/favicon.ico",
        "/scripts/**",
        "/myendpoint/**"
      };

  public static final List<String> ALLOWED_URLS_LIST =
      Collections.unmodifiableList(Arrays.asList(SPRING_SECURITY_ALLOWED_URLS));

  private Constants() {}
}
