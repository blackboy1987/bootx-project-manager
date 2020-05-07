
package com.bootx.service.impl;

import com.bootx.common.CommonAttributes;
import com.bootx.common.Template;
import com.bootx.service.TemplateService;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService, ServletContextAware {

	private ServletContext servletContext;

	@Value("${template.loader_path}")
	private String[] templateLoaderPaths;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public List<Template> getAll() {
		try {
      InputStream settingXmlFile = new ClassPathResource(CommonAttributes.LX_XML_PATH).getInputStream();
      Document document = new SAXReader().read(settingXmlFile);
			List<Template> templates = new ArrayList<Template>();
      List<org.dom4j.Node> nodes = document.selectNodes("/setting/settingConfig");
      for (org.dom4j.Node node: nodes) {
        org.dom4j.Element element = (org.dom4j.Element)node;
				Template template = getTemplate(element);
				templates.add(template);
			}
			return templates;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Template> getList(Template.Type type) {
		if (type != null) {
			try {
        InputStream settingXmlFile = new ClassPathResource(CommonAttributes.LX_XML_PATH).getInputStream();
				Document document = new SAXReader().read(settingXmlFile);
				List<Template> templates = new ArrayList<Template>();
        List<org.dom4j.Node> nodes = document.selectNodes("/setting/settingConfig");
        for (org.dom4j.Node node: nodes) {
          org.dom4j.Element element = (org.dom4j.Element)node;
					Template template = getTemplate(element);
					templates.add(template);
				}
				return templates;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return getAll();
		}
	}

	public Template get(String id) {
		try {
      InputStream settingXmlFile = new ClassPathResource(CommonAttributes.LX_XML_PATH).getInputStream();
			Document document = new SAXReader().read(settingXmlFile);
			Element element = (Element) document.selectSingleNode("/setting/template[@id='" + id + "']");
			Template template = getTemplate(element);
			return template;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String read(String id) {
		Template template = get(id);
		return read(template);
	}

	public String read(Template template) {
		String templatePath = servletContext.getRealPath(templateLoaderPaths[0] + template.getTemplatePath());
		File templateFile = new File(templatePath);
		String templateContent = null;
		try {
			templateContent = FileUtils.readFileToString(templateFile, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return templateContent;
	}

	public void write(String id, String content) {
		Template template = get(id);
		write(template, content);
	}

	public void write(Template template, String content) {
		String templatePath = servletContext.getRealPath(templateLoaderPaths[0] + template.getTemplatePath());
		File templateFile = new File(templatePath);
		try {
			FileUtils.writeStringToFile(templateFile, content, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Template getTemplate(Element element) {
		String id = element.attributeValue("id");
		String type = element.attributeValue("type");
		String name = element.attributeValue("name");
		String templatePath = element.attributeValue("templatePath");
		String staticPath = element.attributeValue("staticPath");
		String description = element.attributeValue("description");

		Template template = new Template();
		template.setId(id);
		template.setType(Template.Type.valueOf(type));
		template.setName(name);
		template.setTemplatePath(templatePath);
		template.setStaticPath(staticPath);
		template.setDescription(description);
		return template;
	}

}
