package com.bootx.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bootx_project_Info")
public class ProjectInfo extends BaseEntity<Long> {

  /**
   * 项目包名
   */
  private String packageName;

  /**
   * 项目名称
   */
  private String name;

  /**
   * 代码生成的模版
   */
  @NotNull
  @Column(nullable = false)
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
}
