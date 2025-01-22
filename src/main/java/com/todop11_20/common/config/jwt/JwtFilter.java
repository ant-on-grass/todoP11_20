package com.todop11_20.common.config.jwt;


import com.todop11_20.common.domain.enums.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter implements Filter {

  private final JwtUtil jwtUtil;


  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {

    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    String uri = request.getRequestURI();

    if (uri.startsWith("/signin") || uri.startsWith("/signup")) {
      filterChain.doFilter(servletRequest, servletResponse);
      return;
    }

    String bearerJwt = request.getHeader("Authorization");

    if (bearerJwt == null) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "토큰이 존재하지 않습니다.");
      return;
    }

    String jwt = jwtUtil.substringToken(request);

    try {
      Claims claims = jwtUtil.extractClaims(jwt);
      if (claims == null) {
        response.sendError(400, "잘못된 토큰입니다.");
        return;
      }

      //UserRole userRole = UserRole.valueOf(claims.get("userRole", String.class));

      request.setAttribute("id", Long.parseLong(claims.getSubject()));
      request.setAttribute("email", claims.get("email"));
      //request.setAttribute("userRole", claims.get("userRole"));

//      if (uri.contains("/admin")) {
//        if (!UserRole.ADMIN.equals(userRole)) {
//          response.sendError(401, "권한이 없습니다.");
//          return;
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
//        return;
//      }

      filterChain.doFilter(servletRequest, servletResponse);
    } catch (SecurityException | MalformedJwtException e) {
      log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.", e);
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않는 JWT 서명입니다.");
    } catch (ExpiredJwtException e) {
      log.error("Expired JWT token, 만료된 JWT token 입니다.", e);
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "만료된 JWT 토큰입니다.");
    } catch (UnsupportedJwtException e) {
      log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.", e);
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "지원되지 않는 JWT 토큰입니다.");
    } catch (Exception e) {
      log.error("Invalid JWT token, 유효하지 않는 JWT 토큰 입니다.", e);
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "유효하지 않는 JWT 토큰입니다.");
    }
  }


}

