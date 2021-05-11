package me.tom.onlinelecture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TEACHER")
public class Teacher {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TEACHER_NO")
  private Long teacherNo;

  @NotNull
  @Column(name = "EMAIL", nullable = false)
  private String email;

  @NotNull
  @Column(name = "PASSWORD")
  private String password;

  @NotNull
  @Column(name = "TCHR_NICK_NM")
  private String tchrNickNm;

  @NotNull
  @Column(name = "NAME")
  private String name;

  @Column(name = "HP")
  private String hp;

}
