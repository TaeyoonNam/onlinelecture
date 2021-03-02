package me.tom.onlinelecture.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import me.tom.onlinelecture.dto.MemberGrade;
import me.tom.onlinelecture.dto.MemberStatus;

@Entity
@Getter
@Builder
@Table(name = "MEMBER")
public class Member {

  @Id
  @GeneratedValue
  @Column(name = "MEMBER_NO")
  private Long memberNo;

  @NotNull
  @Column(name = "EMAIL")
  private String email;

  @NotNull
  @Column(name = "PASSWORD")
  private String password;

  @NotNull
  @Column(name = "MEM_NICK_NM")
  private String memNickNm;

  @NotNull
  @Column(name = "NAME")
  private String name;

  @Column(name = "HP")
  private String hp;

  @NotNull @Column(name = "MEM_STATUS")
  @Enumerated(EnumType.STRING)
  private MemberStatus memberStatus;

  @NotNull @Column(name = "MEM_GRADE")
  @Enumerated(EnumType.STRING)
  private MemberGrade memberGrade;

  @NotNull @Column(name = "REG_DTS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime regDts;

  @NotNull @Column(name = "MOD_DTS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime modDts;

}
