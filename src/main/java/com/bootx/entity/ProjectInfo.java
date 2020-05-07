package com.bootx.entity;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bootx_project_Info")
public class ProjectInfo extends BaseEntity<Long> {

  @JsonView({ListView.class,EditView.class})
  private String dataSourceType;

  /**
   * 项目包名
   */
  @JsonView({ListView.class,EditView.class})
  private String packageName;

  /**
   * 项目名称
   */
  @JsonView({ListView.class,EditView.class})
  private String name;

  @Length(max = 1000)
  @Column(length = 1000)
  @JsonView({ListView.class,EditView.class})
  private String memo;

  @JsonView({ListView.class,EditView.class})
  private String tablePrefix;

  @NotNull
  @Column(nullable = false)
  @JsonView({ListView.class,EditView.class})
  private Integer status;

  /**
   * 代码生成的模版
   */
  @NotNull(groups = Save.class)
  @Column(nullable = false)
  @JsonView({ListView.class,EditView.class})
  private String template;

  @OneToMany(mappedBy = "projectInfo", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
  private Set<ProjectTable> projectTables = new HashSet<>();

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public Set<ProjectTable> getProjectTables() {
    return projectTables;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setProjectTables(Set<ProjectTable> projectTables) {
    this.projectTables = projectTables;
  }

  public String getTemplate() {
    return template;
  }

  public void setTemplate(String template) {
    this.template = template;
  }

  public String getDataSourceType() {
    return dataSourceType;
  }

  public void setDataSourceType(String dataSourceType) {
    this.dataSourceType = dataSourceType;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public String getTablePrefix() {
    return tablePrefix;
  }

  public void setTablePrefix(String tablePrefix) {
    this.tablePrefix = tablePrefix;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
