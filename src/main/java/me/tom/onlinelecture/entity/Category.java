package me.tom.onlinelecture.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
@Table(name = "CATEGORY")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CATEGORY_NO")
  private Long categoryNo;

  @NotNull
  @Column(name = "CATEGORY_NUM")
  private String categoryNum;

  @NotNull
  @Column(name = "CATEGORY_NM")
  private String categoryNm;

  @OneToMany(mappedBy = "category")
  private List<Lecture> lectures = new ArrayList<>();
}
