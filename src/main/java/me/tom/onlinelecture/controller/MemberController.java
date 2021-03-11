package me.tom.onlinelecture.controller;

import javax.validation.Valid;
import me.tom.onlinelecture.dto.MemberDto;
import me.tom.onlinelecture.dto.MemberGrade;
import me.tom.onlinelecture.dto.MemberStatus;
import me.tom.onlinelecture.entity.Member;
import me.tom.onlinelecture.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** 생성자 주입 이유?
 * - 테스트 코드 작성 시 생성자 주입을 통해 원하는 객체만 사용 가능
 * - 스프링 내에서 순환 참조를 방지 할 수 있음.
 */
@RestController
@RequestMapping("/api/member")
public class MemberController {

  private final MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void signUpMemberInfo(@RequestBody @Valid MemberDto memberDto) {

    Member member =
        Member.builder()
            .email(memberDto.getEmail())
            .password(memberDto.getPassword())
            .memNickNm(memberDto.getMemNickNm())
            .name(memberDto.getName())
            .hp(memberDto.getHp())
            .memberStatus(MemberStatus.NORMAL)
            .memberGrade(MemberGrade.BRONZE)
            .build();

    memberService.saveMemberInfo(member);
  }


}
