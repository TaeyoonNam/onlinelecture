package me.tom.onlinelecture.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.Collections;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.tom.onlinelecture.config.JwtTokenProvider;
import me.tom.onlinelecture.dto.MemberDto;
import me.tom.onlinelecture.dto.MemberGrade;
import me.tom.onlinelecture.dto.MemberStatus;
import me.tom.onlinelecture.entity.Member;
import me.tom.onlinelecture.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** 생성자 주입 이유?
 * - 테스트 코드 작성 시 생성자 주입을 통해 원하는 객체만 사용 가능
 * - 스프링 내에서 순환 참조를 방지 할 수 있음.
 */
@Api(tags = {"1. Member"})
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

  private final MemberService memberService;
  private final JwtTokenProvider jwtTokenProvider;
  private final PasswordEncoder passwordEncoder;

  @ApiOperation(value = "회원정보 조회", notes = "회원정보 조회")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
  })
  @GetMapping("/info")
  public ResponseEntity<MemberDto> memberInfo() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Member member = (Member) authentication.getPrincipal();
    MemberDto memberDto = MemberDto.builder()
        .memNickNm(member.getMemNickNm())
        .email(member.getEmail())
        .memberGrade(member.getMemberGrade())
        .hp(member.getHp())
        .name(member.getName())
        .build();
    return ResponseEntity.ok(memberDto);
  }

  @ApiOperation(value = "회원 가입", notes = "회원 정보를 입력합니다.")
  @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
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
  public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest){
    Member member = memberService.findMemberByEmail(loginRequest.getEmail());
    if(!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
      throw new RuntimeException("비밀번호가 틀렸습니다.");
    }

    return ResponseEntity.ok(jwtTokenProvider.createToken(String.valueOf(member.getMemberNo()), member.getRoles()));
  }

  /**
   * 내부 클래스 생성이유
   *  - 내부에서만 사용되는 클래스를 생성함으로써 불필요한 클래스 파일 생성을 줄여준다.
   *  - 타입체킹을 위한 클래스 생
   */
  @Getter
  @AllArgsConstructor
  private static class LoginRequest{
    @NotBlank
    @Email(message = "이메일 정보를 입력하세요")
    private String email;
    @NotBlank
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;
  }
}
