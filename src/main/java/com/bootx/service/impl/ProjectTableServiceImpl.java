
package com.bootx.service.impl;

import com.bootx.entity.ProjectTable;
import com.bootx.service.ProjectTableService;
import com.bootx.service.StaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service - 角色
 *
 * @author blackboy
 * @version 1.0
 */
@Service
public class ProjectTableServiceImpl extends BaseServiceImpl<ProjectTable, Long> implements ProjectTableService {

  @Autowired
  private StaticService staticService;

  @Override
  public Integer build(ProjectTable projectTable) {
    return staticService.build(projectTable);
  }
}
