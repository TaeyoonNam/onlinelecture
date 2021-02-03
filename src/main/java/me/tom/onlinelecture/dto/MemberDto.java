package me.tom.onlinelecture.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberDto {

  @NotBlank
  @Email(message = "이메일 정보를 입력해주시기 바랍니다.")
  private String email;

  @NotBlank(message = "패스워드를 입력해주시기 바랍니다.")
  private String password;

  @NotBlank(message = "이름을 입력해주시기 바랍니다.")
  private String name;

  @Pattern(
      regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$",
      message = "휴대폰 번호를 입력해주시기 바랍니다.")
  private String hp;
}
