
package com.bootx.dao;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.ProjectColumn;

import java.util.Date;

/**
 * Dao - 角色
 *
 * @author blackboy
 * @version 1.0
 */
public interface ProjectColumnDao extends BaseDao<ProjectColumn, Long> {

  Page<ProjectColumn> findPage(Pageable pageable, String name, String memo, Date beginDate, Date endDate);

}
