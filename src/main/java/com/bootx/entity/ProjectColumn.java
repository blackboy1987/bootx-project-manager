package com.bootx.entity;

import com.bootx.common.BaseAttributeConverter;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bootx_project_column")
public class ProjectColumn extends BaseEntity<Long> {

  /**
   * 字段名称
   */
  private String name;

  /**
   * 字段描述
   */
  private String memo;

  /**
   * 字段类型
   */
  private String type;

  /**
   * 字段类型
   */
  private Boolean isNull;
  /**
   * 字段类型
   */
  private Boolean isUnique;
  /**
   * 字段类型
   */
  private Boolean updateIgnore;

  private String dataType;

  @Column(length = 300)
  @Convert(converter = RegexpConverter.class)
  @JsonView({BaseEntity.EditView.class})
  private List<String> regexps = new ArrayList<>();

  private Boolean showList;

  private Boolean searchList;

  private Boolean orderList;

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


  public Boolean getUpdateIgnore() {
    return updateIgnore;
  }

  public void setUpdateIgnore(Boolean updateIgnore) {
    this.updateIgnore = updateIgnore;
  }

  public String getDataType() {
    return dataType;
  }

  public void setDataType(String dataType) {
    this.dataType = dataType;
  }

  public List<String> getRegexps() {
    return regexps;
  }

  public void setRegexps(List<String> regexps) {
    this.regexps = regexps;
  }

  public Boolean getShowList() {
    return showList;
  }

  public void setShowList(Boolean showList) {
    this.showList = showList;
  }

  public Boolean getSearchList() {
    return searchList;
  }

  public void setSearchList(Boolean searchList) {
    this.searchList = searchList;
  }

  public Boolean getOrderList() {
    return orderList;
  }

  public void setOrderList(Boolean orderList) {
    this.orderList = orderList;
  }

  public Boolean getIsNull() {
    return isNull;
  }

  public void setIsNull(Boolean isNull) {
    this.isNull = isNull;
  }

  public Boolean getIsUnique() {
    return isUnique;
  }

  public void setIsUnique(Boolean isUnique) {
    this.isUnique = isUnique;
  }

  /**
   * 首字母小写
   * @return
   */
  @Transient
  public String uncapitalizeName(){
    return StringUtils.uncapitalize(getName());
  }

  /**
   * 首字母大写
   * @return
   */
  public String capitalizeName(){
    return StringUtils.capitalize(getName());
  }


  @Converter
  public static class RegexpConverter extends BaseAttributeConverter<List> {
  }
}
