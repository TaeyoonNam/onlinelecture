package me.tom.onlinelecture.controller;

import javax.validation.Valid;
import me.tom.onlinelecture.dto.MemberDto;
import me.tom.onlinelecture.entity.Member;
import me.tom.onlinelecture.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

  private final MemberService memberService;

  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @PostMapping("/api/member")
  public void signUpMemberInfo(@RequestBody @Valid MemberDto memberDto) {

    Member member =
        Member.builder()
            .name(memberDto.getName())
            .password(memberDto.getPassword())
            .email(memberDto.getEmail())
            .build();
  }
}
