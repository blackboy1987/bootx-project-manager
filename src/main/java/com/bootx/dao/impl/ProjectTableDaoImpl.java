
package com.bootx.dao.impl;

import com.bootx.common.Page;
import com.bootx.common.Pageable;
import com.bootx.dao.ProjectTableDao;
import com.bootx.entity.ProjectTable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

/**
 * Dao - 角色
 *
 * @author blackboy
 * @version 1.0
 */
@Repository
public class ProjectTableDaoImpl extends BaseDaoImpl<ProjectTable, Long> implements ProjectTableDao {

  @Override
  public Page<ProjectTable> findPage(Pageable pageable, String name, String memo, Date beginDate, Date endDate) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<ProjectTable> criteriaQuery = criteriaBuilder.createQuery(ProjectTable.class);
    Root<ProjectTable> root = criteriaQuery.from(ProjectTable.class);
    criteriaQuery.select(root);
    Predicate restrictions = criteriaBuilder.conjunction();
    if (StringUtils.isNotEmpty(name)) {
      restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.get("name"), "%"+name+"%"));
    }
    if (StringUtils.isNotEmpty(memo)) {
      restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.get("memo"), "%"+memo+"%"));
    }
    if (beginDate != null) {
      restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"), beginDate));
    }
    if (endDate != null) {
      restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"), endDate));
    }
    criteriaQuery.where(restrictions);
    return super.findPage(criteriaQuery, pageable);
  }
}
