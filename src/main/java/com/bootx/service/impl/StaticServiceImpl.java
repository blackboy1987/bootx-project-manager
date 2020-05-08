
package com.bootx.service.impl;

import com.bootx.entity.ProjectTable;
import com.bootx.service.StaticService;
import com.bootx.service.TemplateService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class StaticServiceImpl implements StaticService, ServletContextAware {

	private ServletContext servletContext;

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Autowired
	private TemplateService templateService;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Transactional(readOnly = true)
	public int build(String templatePath, String staticPath, Map<String, Object> model) {
		Assert.hasText(templatePath,"");
		Assert.hasText(staticPath,"");

		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		Writer writer = null;
		try {
			freemarker.template.Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templatePath);
			File staticFile = new File(servletContext.getRealPath(staticPath));
			File staticDirectory = staticFile.getParentFile();
			if (!staticDirectory.exists()) {
				staticDirectory.mkdirs();
			}
      System.out.println(staticFile.getAbsolutePath());
			fileOutputStream = new FileOutputStream(staticFile);
			outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
			writer = new BufferedWriter(outputStreamWriter);
			template.process(model, writer);
			writer.flush();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(writer);
			IOUtils.closeQuietly(outputStreamWriter);
			IOUtils.closeQuietly(fileOutputStream);
		}
		return 0;
	}

	@Transactional(readOnly = true)
	public int build(String templatePath, String staticPath) {
		return build(templatePath, staticPath, null);
	}


	@Transactional(readOnly = true)
	public int build(ProjectTable projectTable) {
		Assert.notNull(projectTable,"");

		delete(projectTable);
		int buildCount = 0;
    Map<String,String> projectTemplates = projectTable.getTemplatePaths();
    Map<String, Object> model = new HashMap<>();
    model.put("project", projectTable.getProjectInfo());
    model.put("projectTable", projectTable);
    for (String key:projectTemplates.keySet()) {
      buildCount += build(key,projectTemplates.get(key), model);

    }
   return buildCount;
	}


	@Transactional(readOnly = true)
	public int delete(String staticPath) {
		Assert.hasText(staticPath);

		File staticFile = new File(servletContext.getRealPath(staticPath));
		if (staticFile.exists()) {
			staticFile.delete();
			return 1;
		}
		return 0;
	}

	@Transactional(readOnly = true)
	public int delete(ProjectTable projectTable) {
		Assert.notNull(projectTable);

		return 1;
	}

}
