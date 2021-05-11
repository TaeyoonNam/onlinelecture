package me.tom.onlinelecture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
@Table(name = "LECTURE_OPT")
public class LectureOpt {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "LCTR_SEQ")
  private Long lctrSeq;

  @ManyToOne
  @JoinColumn(name = "LCTR_NO")
  private Lecture lecture;

  @NotNull
  @Column(name = "LCTR_OPT_NM")
  private String lctrOptNm;

}
