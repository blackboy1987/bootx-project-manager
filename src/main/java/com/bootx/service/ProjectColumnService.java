
package com.bootx.service;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.ProjectColumn;
import com.bootx.entity.ProjectInfo;

import java.util.Date;

/**
 * Service - 角色
 *
 * @author blackboy
 * @version 1.0
 */
public interface ProjectColumnService extends BaseService<ProjectColumn, Long> {

  Page<ProjectColumn> findPage(Pageable pageable, String name, String memo, Date beginDate, Date endDate);

}
