package com.bootx.controller;

import com.bootx.common.Message;
import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.BaseEntity;
import com.bootx.entity.ProjectColumn;
import com.bootx.entity.ProjectTable;
import com.bootx.service.ProjectColumnService;
import com.bootx.service.ProjectTableService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/column")
public class ProjectColumnController extends BaseController {

  @Resource
  private ProjectTableService projectTableService;

  @Resource
  private ProjectColumnService projectColumnService;

  @PostMapping("/save")
  public Message save(ProjectColumn projectColumn, Long tableId){
    projectColumn.setProjectTable(projectTableService.find(tableId));
    if(!isValid(projectColumn, BaseEntity.Save.class)){
      return Message.error("参数错误");
    }
    projectColumnService.save(projectColumn);
    return Message.success("操作成功");
  }

  @PostMapping("/edit")
  @JsonView(BaseEntity.EditView.class)
  public ProjectTable edit(Long id){
    return projectTableService.find(id);
  }

  @PostMapping("/update")
  public Message update(ProjectColumn projectColumn){
    if(!isValid(projectColumn, BaseEntity.Update.class)){
      return Message.error("参数错误");
    }
    projectColumnService.update(projectColumn,"projectTable");
    return Message.success("操作成功");
  }

  @PostMapping("/list")
  @JsonView(BaseEntity.ListView.class)
  public Page<ProjectColumn> list(Pageable pageable, String name, String memo, Date beginDate, Date endDate){
    return projectColumnService.findPage(pageable,name,memo,beginDate,endDate);
  }

  @PostMapping("/delete")
  public Message delete(Long[] ids){
    projectColumnService.delete(ids);
    return Message.success("操作成功");
  }

}
