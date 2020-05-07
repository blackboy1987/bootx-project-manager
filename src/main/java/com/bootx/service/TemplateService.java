
package com.bootx.service;

import com.bootx.common.Template;

import java.util.List;


public interface TemplateService {

	List<Template> getAll();

	List<Template> getList(Template.Type type);

	Template get(String id);

	String read(String id);

	String read(Template template);

	void write(String id, String content);

	void write(Template template, String content);

}
