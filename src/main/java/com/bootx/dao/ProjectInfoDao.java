
package com.bootx.dao;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.entity.ProjectInfo;

import java.util.Date;

/**
 * Dao - 角色
 *
 * @author blackboy
 * @version 1.0
 */
public interface ProjectInfoDao extends BaseDao<ProjectInfo, Long> {

  Page<ProjectInfo> findPage(Pageable pageable, String name, String memo, Date beginDate, Date endDate);
}
