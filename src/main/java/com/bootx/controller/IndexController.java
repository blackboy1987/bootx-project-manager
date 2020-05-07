package com.bootx.controller;

import com.bootx.entity.ProjectTable;
import com.bootx.service.ProjectTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {

  @Autowired
  private ProjectTableService projectTableService;

  @GetMapping
  public Integer index(){
    ProjectTable projectTable = projectTableService.find(1L);

    return projectTableService.build(projectTable);

  }
}
