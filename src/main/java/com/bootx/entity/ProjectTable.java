package com.bootx.entity;


import com.bootx.common.CommonAttributes;
import com.bootx.util.FreemarkerUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Entity
@Table(name = "bootx_project_table")
public class ProjectTable extends BaseEntity<Long>{

  /**
   * 表名称
   */
  @JsonView({ListView.class,EditView.class})
  private String name;

  @JsonView({ListView.class,EditView.class})
  private String memo;

  @JsonView({ListView.class,EditView.class})
  private String alias;

  @JsonView({ListView.class,EditView.class})
  private String parentClass;

  @ManyToOne(fetch = FetchType.LAZY)
  private ProjectInfo projectInfo;

  @OneToMany(mappedBy = "projectTable",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
  private Set<ProjectColumn> projectColumns = new HashSet<>();


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

  public Set<ProjectColumn> getProjectColumns() {
    return projectColumns;
  }

  public void setProjectColumns(Set<ProjectColumn> projectColumns) {
    this.projectColumns = projectColumns;
  }

  public ProjectInfo getProjectInfo() {
    return projectInfo;
  }

  public void setProjectInfo(ProjectInfo projectInfo) {
    this.projectInfo = projectInfo;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getParentClass() {
    return parentClass;
  }

  public void setParentClass(String parentClass) {
    this.parentClass = parentClass;
  }

  private static Map<String,String> templates=null;

  static {
    try {
      templates = new HashMap<>();
      InputStream settingXmlFile = new ClassPathResource(CommonAttributes.LX_XML_PATH).getInputStream();
      Document document = new SAXReader().read(settingXmlFile);

      List<org.dom4j.Node> nodes = document.selectNodes("/setting/template[@type='springboot']");
      for (org.dom4j.Node node:nodes) {
        org.dom4j.Element element = (org.dom4j.Element) node;
        templates.put(element.attributeValue("templatePath"),element.attributeValue("staticPath"));
      }
      nodes = document.selectNodes("/setting/template[@type='ant_ts']");
      for (org.dom4j.Node node:nodes) {
        org.dom4j.Element element = (org.dom4j.Element) node;
        templates.put(element.attributeValue("templatePath"),element.attributeValue("staticPath"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @JsonProperty
  @Transient
  public Map<String,String> getTemplatePaths() {
    Map<String,String> templatePaths = new HashMap<>();
    Map<String, Object> model = new HashMap<>();
    model.put("id", getId());
    model.put("now", new Date());
    model.put("createdDate", getCreatedDate());
    model.put("modifyDate", getLastModifiedDate());
    model.put("projectName", getProjectInfo().getName());
    model.put("packageName", getProjectInfo().getPackageName().replace(".","/"));
    model.put("tableName", getName());
    model.put("template",StringUtils.isNotEmpty(getProjectInfo().getTemplate())?getProjectInfo().getTemplate():"default");
    try {
      for (String key:templates.keySet()) {
        templatePaths.put(FreemarkerUtils.process(key, model),FreemarkerUtils.process(templates.get(key), model));
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TemplateException e) {
      e.printStackTrace();
    }
    return templatePaths;
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

  @Transient
  public List<String> getImportPackages(){
    List<String> importPackages = new ArrayList<>();
    for (ProjectColumn projectColumn:getProjectColumns()){
      if(StringUtils.equalsAnyIgnoreCase("date",projectColumn.getType())){
        importPackages.add("java.util.Date");
      }else if(StringUtils.equalsAnyIgnoreCase("BigDecimal",projectColumn.getType())){
        importPackages.add("java.math.BigDecimal");
      }else if(StringUtils.equalsAnyIgnoreCase("List",projectColumn.getType())){
        importPackages.add("java.util.List");
        importPackages.add("java.util.ArrayList");
      }else if(StringUtils.equalsAnyIgnoreCase("Set",projectColumn.getType())){
        importPackages.add("java.util.Set");
        importPackages.add("java.util.HashSet");
      }
    }

    return importPackages;
  }

  @Transient
  @JsonView({EditView.class})
  public Long getProjectId(){
    if(projectInfo!=null){
      return projectInfo.getId();
    }
    return null;
  }

  @Transient
  @JsonView({ListView.class})
  public String getProjectName(){
    if(projectInfo!=null){
      return projectInfo.getName();
    }
    return null;
  }

}
