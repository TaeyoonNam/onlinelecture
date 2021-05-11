package me.tom.onlinelecture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
@Table(name = "ORDCLMDTL")
public class OrdclmDtl {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ORDCLMDTL_NO")
  private Long ordclmDtlNo;

  @ManyToOne
  @JoinColumn(name = "ORDCLM_NO")
  private Ordclm ordclm;

  @NotNull
  @Column(name = "ORDCLMDTL_SEQ", columnDefinition = "tinyint(2) not null comment '주문상세 순서'")
  private Integer ordclmDtlSeq;

  @NotNull
  @Column(name = "ORDCLM_STAT", columnDefinition = "varchar(2) not null comment '주문/취소 상태'")
  private String ordclmStat;

  @NotNull
  @Column(name = "CATEGORY_NO", insertable = false, updatable = false)
  private Long categoryNo;

  @NotNull
  @Column(name = "LCTR_NO", insertable = false, updatable = false)
  private Long lctrNo;

  @NotNull
  @Column(name = "LCTR_NO")
  private String lctrNm;
}
