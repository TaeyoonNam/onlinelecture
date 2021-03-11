package me.tom.onlinelecture.service;

import lombok.extern.log4j.Log4j2;
import me.tom.onlinelecture.entity.Member;
import me.tom.onlinelecture.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MemberService {

  private final MemberRepository memberRepository;

  @Autowired
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public void saveMemberInfo(Member member) {
    memberRepository.save(member);
  }
}
