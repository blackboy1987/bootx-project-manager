
package com.bootx.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.ProjectColumn;
import com.bootx.service.ProjectColumnService;
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
public class ProjectColumnServiceImpl extends BaseServiceImpl<ProjectColumn, Long> implements ProjectColumnService {

  @Resource
  private ProjectColumnService projectColumnService;

  @Override
  public Page<ProjectColumn> findPage(Pageable pageable, String name, String memo, Date beginDate, Date endDate) {
    return projectColumnService.findPage(pageable,name,memo,beginDate,endDate);
  }
}
