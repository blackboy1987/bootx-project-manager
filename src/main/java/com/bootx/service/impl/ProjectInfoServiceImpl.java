
package com.bootx.service.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.dao.ProjectInfoDao;
import com.bootx.entity.ProjectInfo;
import com.bootx.service.ProjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Service - 角色
 *
 * @author blackboy
 * @version 1.0
 */
@Service
public class ProjectInfoServiceImpl extends BaseServiceImpl<ProjectInfo, Long> implements ProjectInfoService {

  @Autowired
  private ProjectInfoDao projectInfoDao;

  @Override
  public Page<ProjectInfo> findPage(Pageable pageable, String name, String memo, Date beginDate, Date endDate) {
    return projectInfoDao.findPage(pageable,name,memo,beginDate,endDate);
  }
}
