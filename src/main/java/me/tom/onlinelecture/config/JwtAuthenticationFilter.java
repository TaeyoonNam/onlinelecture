package me.tom.onlinelecture.config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Filter란?
 *    HTTP 요청과 응답을 변경할 수 있는 재사용 가능한 코드. 클라이언트로부터 오는 요청과 최종자원(JSP/서블릿/기타 문서) 사이에 위치하며
 *    클라이언트의 요청 정보를 알맞게 변경할 수 있다. 또한 필터는 최종 자원과 클라이언트로 가는 응답 사이에 위치해 최종 자원의 요청 결과를 알맞게 변경 할 수 있다.
 *    참고 : https://twofootdog.github.io/Spring-%ED%95%84%ED%84%B0(Filter)%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80/
 */
public class JwtAuthenticationFilter extends GenericFilterBean {

  private JwtTokenProvider jwtTokenProvider;

  // Jwt Provier 주입
  public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  // Request로 들어오는 Jwt Token의 유효성을 검증(jwtTokenProvider.validateToken)하는 filter를 filterChain에 등록
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
    if (token != null && jwtTokenProvider.validateToken(token)) {
      Authentication auth = jwtTokenProvider.getAuthentication(token);
      SecurityContextHolder.getContext().setAuthentication(auth);
    }
    filterChain.doFilter(request, response);
  }
}