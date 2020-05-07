
package com.bootx.common;

import java.io.Serializable;

public class ProjectTemplate implements Serializable {

  private String entityStaticPath;
  private String daoStaticPath;
  private String daoImplStaticPath;
  private String serviceStaticPath;
  private String serviceImplStaticPath;
  private String controllerStaticPath;

  private String entityTemplatePath;
  private String daoTemplatePath;
  private String daoImplTemplatePath;
  private String serviceTemplatePath;
  private String serviceImplTemplatePath;
  private String controllerTemplatePath;


  public String getEntityStaticPath() {
    return entityStaticPath;
  }

  public void setEntityStaticPath(String entityStaticPath) {
    this.entityStaticPath = entityStaticPath;
  }

  public String getDaoStaticPath() {
    return daoStaticPath;
  }

  public void setDaoStaticPath(String daoStaticPath) {
    this.daoStaticPath = daoStaticPath;
  }

  public String getDaoImplStaticPath() {
    return daoImplStaticPath;
  }

  public void setDaoImplStaticPath(String daoImplStaticPath) {
    this.daoImplStaticPath = daoImplStaticPath;
  }

  public String getServiceStaticPath() {
    return serviceStaticPath;
  }

  public void setServiceStaticPath(String serviceStaticPath) {
    this.serviceStaticPath = serviceStaticPath;
  }

  public String getServiceImplStaticPath() {
    return serviceImplStaticPath;
  }

  public void setServiceImplStaticPath(String serviceImplStaticPath) {
    this.serviceImplStaticPath = serviceImplStaticPath;
  }

  public String getControllerStaticPath() {
    return controllerStaticPath;
  }

  public void setControllerStaticPath(String controllerStaticPath) {
    this.controllerStaticPath = controllerStaticPath;
  }

  public String getEntityTemplatePath() {
    return entityTemplatePath;
  }

  public void setEntityTemplatePath(String entityTemplatePath) {
    this.entityTemplatePath = entityTemplatePath;
  }

  public String getDaoTemplatePath() {
    return daoTemplatePath;
  }

  public void setDaoTemplatePath(String daoTemplatePath) {
    this.daoTemplatePath = daoTemplatePath;
  }

  public String getDaoImplTemplatePath() {
    return daoImplTemplatePath;
  }

  public void setDaoImplTemplatePath(String daoImplTemplatePath) {
    this.daoImplTemplatePath = daoImplTemplatePath;
  }

  public String getServiceTemplatePath() {
    return serviceTemplatePath;
  }

  public void setServiceTemplatePath(String serviceTemplatePath) {
    this.serviceTemplatePath = serviceTemplatePath;
  }

  public String getServiceImplTemplatePath() {
    return serviceImplTemplatePath;
  }

  public void setServiceImplTemplatePath(String serviceImplTemplatePath) {
    this.serviceImplTemplatePath = serviceImplTemplatePath;
  }

  public String getControllerTemplatePath() {
    return controllerTemplatePath;
  }

  public void setControllerTemplatePath(String controllerTemplatePath) {
    this.controllerTemplatePath = controllerTemplatePath;
  }
}
