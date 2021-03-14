package me.tom.onlinelecture.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Collections;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.tom.onlinelecture.config.JwtTokenProvider;
import me.tom.onlinelecture.dto.MemberDto;
import me.tom.onlinelecture.dto.MemberGrade;
import me.tom.onlinelecture.dto.MemberStatus;
import me.tom.onlinelecture.entity.Member;
import me.tom.onlinelecture.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** 생성자 주입 이유?
 * - 테스트 코드 작성 시 생성자 주입을 통해 원하는 객체만 사용 가능
 * - 스프링 내에서 순환 참조를 방지 할 수 있음.
 */
@Api(tags = {"1. Member"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

  private final MemberService memberService;
  private final JwtTokenProvider jwtTokenProvider;
  private final PasswordEncoder passwordEncoder;

  @ApiOperation(value = "회원 가입", notes = "회원 정보를 입력합니다.")
  @PostMapping("/signup")
  @ResponseStatus(HttpStatus.CREATED)
  public void signUpMemberInfo(@RequestBody @Valid MemberDto memberDto) {

    Member member =
        Member.builder()
            .email(memberDto.getEmail())
            .password(passwordEncoder.encode(memberDto.getPassword()))
            .memNickNm(memberDto.getMemNickNm())
            .name(memberDto.getName())
            .hp(memberDto.getHp())
            .memberStatus(MemberStatus.NORMAL)
            .memberGrade(MemberGrade.BRONZE)
            .roles(Collections.singletonList("USER"))
            .build();

    memberService.saveMemberInfo(member);
  }

  @ApiOperation(value = "로그인", notes = "이메일 회원 로그인")
  @PostMapping(value = "/login")
  public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password){
    Member member = memberService.findMemberByEmail(email);
    if(!passwordEncoder.matches(password, member.getPassword())) {
      throw new RuntimeException("비밀번호가 틀렸습니다.");
    }

    return ResponseEntity.ok(jwtTokenProvider.createToken(String.valueOf(member.getMemberNo()), member.getRoles()));
  }
}
