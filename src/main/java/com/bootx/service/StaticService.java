
package com.bootx.service;

import com.bootx.entity.ProjectTable;

import java.util.Map;


public interface StaticService {

	int build(String templatePath, String staticPath, Map<String, Object> model);

	int build(String templatePath, String staticPath);

	int build(ProjectTable projectTable);


	int delete(String staticPath);

	int delete(ProjectTable projectTable);


}
