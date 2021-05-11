package me.tom.onlinelecture.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
@Table(name = "ORDCLM")
public class Ordclm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ORDCLM_NO")
  private Long ordclmNo;

  @NotNull
  @Column(name = "PRICE", columnDefinition = "int unsigned not null comment '가격'")
  private Integer price;

  @Column(name = "REG_NO", columnDefinition = "int unsigned not null comment '등록자'")
  private Long regNo;

  @Column(name = "REG_DTS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime regDts;

  @Column(name = "MOD_NO", columnDefinition = "int unsigned not null comment '수정자'")
  private Long modNo;

  @Column(name = "MOD_DTS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime modDts;
}
