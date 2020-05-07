package com.bootx.entity;


import com.bootx.common.CommonAttributes;
import com.bootx.common.ProjectTemplate;
import com.bootx.util.FreemarkerUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
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
  private String name;

  private String memo;

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

  private static String entityStaticPath;
  private static String daoStaticPath;
  private static String daoImplStaticPath;
  private static String serviceStaticPath;
  private static String serviceImplStaticPath;
  private static String controllerStaticPath;


  private static String entityTemplatePath;
  private static String daoTemplatePath;
  private static String daoImplTemplatePath;
  private static String serviceTemplatePath;
  private static String serviceImplTemplatePath;
  private static String controllerTemplatePath;

  static {
    try {
      InputStream settingXmlFile = new ClassPathResource(CommonAttributes.LX_XML_PATH).getInputStream();
      Document document = new SAXReader().read(settingXmlFile);
      org.dom4j.Element element = (org.dom4j.Element) document.selectSingleNode("/setting/template[@id='entity']");
      entityStaticPath = element.attributeValue("staticPath");
      element = (org.dom4j.Element) document.selectSingleNode("/setting/template[@id='dao']");
      daoStaticPath = element.attributeValue("staticPath");
      element = (org.dom4j.Element) document.selectSingleNode("/setting/template[@id='daoImpl']");
      daoImplStaticPath = element.attributeValue("staticPath");
      element = (org.dom4j.Element) document.selectSingleNode("/setting/template[@id='service']");
      serviceStaticPath = element.attributeValue("staticPath");
      element = (org.dom4j.Element) document.selectSingleNode("/setting/template[@id='serviceImpl']");
      serviceImplStaticPath = element.attributeValue("staticPath");
      element = (org.dom4j.Element) document.selectSingleNode("/setting/template[@id='controller']");
      controllerStaticPath = element.attributeValue("staticPath");


      element = (org.dom4j.Element) document.selectSingleNode("/setting/template[@id='entity']");
      entityTemplatePath = element.attributeValue("templatePath");
      element = (org.dom4j.Element) document.selectSingleNode("/setting/template[@id='dao']");
      daoTemplatePath = element.attributeValue("templatePath");
      element = (org.dom4j.Element) document.selectSingleNode("/setting/template[@id='daoImpl']");
      daoImplTemplatePath = element.attributeValue("templatePath");
      element = (org.dom4j.Element) document.selectSingleNode("/setting/template[@id='service']");
      serviceTemplatePath = element.attributeValue("templatePath");
      element = (org.dom4j.Element) document.selectSingleNode("/setting/template[@id='serviceImpl']");
      serviceImplTemplatePath = element.attributeValue("templatePath");
      element = (org.dom4j.Element) document.selectSingleNode("/setting/template[@id='controller']");
      controllerTemplatePath = element.attributeValue("templatePath");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @JsonProperty
  @Transient
  public ProjectTemplate getProjectTemplate() {
    ProjectTemplate projectTemplate = new ProjectTemplate();
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
      projectTemplate.setEntityStaticPath(FreemarkerUtils.process(entityStaticPath, model));
      projectTemplate.setDaoStaticPath(FreemarkerUtils.process(daoStaticPath, model));
      projectTemplate.setDaoImplStaticPath(FreemarkerUtils.process(daoImplStaticPath, model));
      projectTemplate.setServiceStaticPath(FreemarkerUtils.process(serviceStaticPath, model));
      projectTemplate.setServiceImplStaticPath(FreemarkerUtils.process(serviceImplStaticPath, model));
      projectTemplate.setControllerStaticPath(FreemarkerUtils.process(controllerStaticPath, model));

      projectTemplate.setEntityTemplatePath(FreemarkerUtils.process(entityTemplatePath, model));
      projectTemplate.setDaoTemplatePath(FreemarkerUtils.process(daoTemplatePath, model));
      projectTemplate.setDaoImplTemplatePath(FreemarkerUtils.process(daoImplTemplatePath, model));
      projectTemplate.setServiceTemplatePath(FreemarkerUtils.process(serviceTemplatePath, model));
      projectTemplate.setServiceImplTemplatePath(FreemarkerUtils.process(serviceImplTemplatePath, model));
      projectTemplate.setControllerTemplatePath(FreemarkerUtils.process(controllerTemplatePath, model));
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TemplateException e) {
      e.printStackTrace();
    }
    return projectTemplate;
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

}
