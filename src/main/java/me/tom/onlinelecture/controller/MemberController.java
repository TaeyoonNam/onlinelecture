package me.tom.onlinelecture.controller;

import me.tom.onlinelecture.domain.Member;
import me.tom.onlinelecture.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

  private final MemberService memberService;

  public MemberController (MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping
  public String hello() {
    memberService.saveMemberInfo(Member.builder()
        .email("tom@localhost.com")
        .password("tomPassword")
        .name("남태윤")
        .build());
    return "hello";
  }

}
