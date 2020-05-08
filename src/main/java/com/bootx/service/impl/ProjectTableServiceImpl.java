
package com.bootx.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.dao.ProjectTableDao;
import com.bootx.entity.ProjectTable;
import com.bootx.service.ProjectTableService;
import com.bootx.service.StaticService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Service - 角色
 *
 * @author blackboy
 * @version 1.0
 */
@Service
public class ProjectTableServiceImpl extends BaseServiceImpl<ProjectTable, Long> implements ProjectTableService {

  @Resource
  private StaticService staticService;

  @Resource
  private ProjectTableDao projectTableDao;

  @Override
  public Integer build(ProjectTable projectTable) {
    return staticService.build(projectTable);
  }

  @Override
  public Page<ProjectTable> findPage(Pageable pageable, String name, String memo, Date beginDate, Date endDate) {
    return projectTableDao.findPage(pageable,name,memo,beginDate,endDate);
  }
}
