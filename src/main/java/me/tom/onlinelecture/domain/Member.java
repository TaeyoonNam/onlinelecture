package me.tom.onlinelecture.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "MEMBER")
public class Member {

  @Id @GeneratedValue
  @Column(name = "memberNo")
  private Long memberNo;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "NAME")
  private String name;

  @Builder
  public Member(String email, String password, String name) {
    this.email = email;
    this.password = password;
    this.name = name;
  }

}
