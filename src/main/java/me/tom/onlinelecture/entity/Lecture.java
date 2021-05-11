package me.tom.onlinelecture.entity;

import java.time.LocalDateTime;
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
@Table(name = "LECTURE")
public class Lecture {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "LCTR_NO")
  private Long lctrNo;

  @NotNull
  @Column(name = "LCTR_NM", nullable = false, length = 200)
  private String lctrNm;

  @ManyToOne
  @JoinColumn(name = "CATEGORY_NO")
  private Category category;

  @NotNull
  @Column(name = "START_DATE", columnDefinition = "TimeStamp not null default now() comment '판매시작'")
  private LocalDateTime startDate;

  @NotNull
  @Column(name = "END_DATE", columnDefinition = "TimeStamp not null default now() comment '판매종료'")
  private LocalDateTime endDate;

  @NotNull
  @Column(name = "DISP_YN")
  private boolean dispYn;
}
