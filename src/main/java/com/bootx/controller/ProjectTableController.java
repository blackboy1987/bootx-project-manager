package com.bootx.controller;

import com.bootx.common.Message;
import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.BaseEntity;
import com.bootx.entity.ProjectTable;
import com.bootx.service.ProjectInfoService;
import com.bootx.service.ProjectTableService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/entity")
public class ProjectTableController extends BaseController {

  @Resource
  private ProjectTableService projectTableService;

  @Resource
  private ProjectInfoService projectInfoService;

  @PostMapping("/save")
  public Message save(ProjectTable projectTable,Long projectId){
    projectTable.setProjectInfo(projectInfoService.find(projectId));
    if(!isValid(projectTable, BaseEntity.Save.class)){
      return Message.error("参数错误");
    }
    projectTableService.save(projectTable);
    return Message.success("操作成功");
  }

  @PostMapping("/edit")
  @JsonView(BaseEntity.EditView.class)
  public ProjectTable edit(Long id){
    return projectTableService.find(id);
  }

  @PostMapping("/update")
  public Message update(ProjectTable projectTable){
    if(!isValid(projectTable, BaseEntity.Update.class)){
      return Message.error("参数错误");
    }
    projectTableService.update(projectTable,"projectColumns","projectInfo");
    return Message.success("操作成功");
  }

  @PostMapping("/list")
  @JsonView(BaseEntity.ListView.class)
  public Page<ProjectTable> list(Pageable pageable, String name, String memo, Date beginDate, Date endDate){
    return projectTableService.findPage(pageable,name,memo,beginDate,endDate);
  }

  @PostMapping("/delete")
  public Message delete(Long[] ids){
    projectTableService.delete(ids);
    return Message.success("操作成功");
  }

}
