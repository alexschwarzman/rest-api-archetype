package com.aschwarzman.api.archetype.controllers;

import com.aschwarzman.api.archetype.services.ControllerService;

public interface Controller<S extends ControllerService> {

	S getControllerService();

}
