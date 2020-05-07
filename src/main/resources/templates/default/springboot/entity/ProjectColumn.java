package com.bootx.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bootx_project_column")
public class ProjectColumn extends BaseEntity<Long> {

  private String name;

  private String memo;

  private String type;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false,updatable = false)
  private ProjectTable projectTable;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ProjectTable getProjectTable() {
    return projectTable;
  }

  public void setProjectTable(ProjectTable projectTable) {
    this.projectTable = projectTable;
  }
}
