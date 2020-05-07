package com.bootx.controller;

import com.bootx.common.Message;
import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.BaseEntity;
import com.bootx.entity.ProjectInfo;
import com.bootx.service.ProjectInfoService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/project")
public class ProjectInfoController extends BaseController {

  @Autowired
  private ProjectInfoService projectInfoService;

  @PostMapping("/save")
  public Message save(ProjectInfo projectInfo){
    projectInfo.setStatus(0);
    projectInfo.setTemplate("springboot");
    if(!isValid(projectInfo, BaseEntity.Save.class)){
      return Message.error("参数错误");
    }
    projectInfoService.save(projectInfo);
    return Message.success("操作成功");
  }

  @PostMapping("/edit")
  @JsonView(BaseEntity.EditView.class)
  public ProjectInfo edit(Long id){
    return projectInfoService.find(id);
  }

  @PostMapping("/update")
  public Message update(ProjectInfo projectInfo){
    if(!isValid(projectInfo, BaseEntity.Update.class)){
      return Message.error("参数错误");
    }
    projectInfoService.update(projectInfo,"template","status","projectTables");
    return Message.success("操作成功");
  }

  @PostMapping("/list")
  @JsonView(BaseEntity.ListView.class)
  public Page<ProjectInfo> list(Pageable pageable, String name, String memo, Date beginDate, Date endDate){
    return projectInfoService.findPage(pageable,name,memo,beginDate,endDate);
  }

  @PostMapping("/delete")
  public Message delete(Long[] ids){
    projectInfoService.delete(ids);
    return Message.success("操作成功");
  }

}
