package me.tom.onlinelecture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
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
  @Column(name = "NAME")
  private String name;

  @Column(name = "HP")
  private String hp;

  @Builder
  public Member(String email, String password, String name) {
    this.email = email;
    this.password = password;
    this.name = name;
  }
}
