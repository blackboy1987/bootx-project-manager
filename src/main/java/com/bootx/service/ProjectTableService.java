
package com.bootx.service;

import com.bootx.entity.ProjectTable;

/**
 * Service - 角色
 *
 * @author blackboy
 * @version 1.0
 */
public interface ProjectTableService extends BaseService<ProjectTable, Long> {


  Integer build(ProjectTable projectTable);
}
