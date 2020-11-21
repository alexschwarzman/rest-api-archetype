package com.aschwarzman.api.archetype.services;

import com.aschwarzman.api.infrastructure.model.Element;

import java.io.Serializable;

public interface DomainControllerService<K extends Serializable, E extends Element<K>> extends SearchControllerService<E>, HeadByIdControllerService<K>, GetByIdControllerService<K, E>, CreateControllerService<K, E>, UpdateControllerService<K, E>, DeleteControllerService<K> {

}
