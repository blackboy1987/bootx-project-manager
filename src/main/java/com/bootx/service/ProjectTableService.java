
package com.bootx.service;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.ProjectInfo;
import com.bootx.entity.ProjectTable;

import java.util.Date;

/**
 * Service - 角色
 *
 * @author blackboy
 * @version 1.0
 */
public interface ProjectTableService extends BaseService<ProjectTable, Long> {


  Integer build(ProjectTable projectTable);

  Page<ProjectTable> findPage(Pageable pageable, String name, String memo, Date beginDate, Date endDate);

}
