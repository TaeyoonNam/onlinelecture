package me.tom.onlinelecture.service;

import lombok.RequiredArgsConstructor;
import me.tom.onlinelecture.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String memNo) throws UsernameNotFoundException {
    return memberRepository.findById(Long.valueOf(memNo)).orElseThrow(RuntimeException::new);
  }
}
