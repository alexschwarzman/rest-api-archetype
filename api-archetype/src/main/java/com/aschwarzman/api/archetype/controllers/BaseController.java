package com.aschwarzman.api.archetype.controllers;

import com.aschwarzman.api.archetype.services.ControllerService;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController<S extends ControllerService> implements InitializingBean {

	@Autowired
	private S entityControllerService;

	@Override
	public void afterPropertiesSet() throws Exception {
		doInit();
	}

	protected void doInit() {}

	public S getControllerService() {
		return entityControllerService;
	}

}
