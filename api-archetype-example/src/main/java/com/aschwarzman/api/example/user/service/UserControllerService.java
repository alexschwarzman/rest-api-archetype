package com.aschwarzman.api.example.user.service;

import com.aschwarzman.api.archetype.exceptions.ConflictException;
import com.aschwarzman.api.archetype.exceptions.NotFoundException;
import com.aschwarzman.api.archetype.services.DomainControllerService;
import com.aschwarzman.api.example.user.model.User;
import com.aschwarzman.api.infrastructure.rql.QueryContext;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserControllerService implements DomainControllerService<Long, User> {

	private Map<Long, User> storage = new ConcurrentHashMap();

	@Override
	public Map<String, String> getFieldAliases() {
		return Collections.emptyMap();
	}

	@Override
	public List<User> search(Map<String, String> headers, QueryContext query) {
		return new ArrayList(storage.values());
	}

	@Override
	public boolean exists(Map<String, String> headers, Long id) {
		return storage.containsKey(id);
	}

	@Override
	public User get(Map<String, String> headers, Long id) {
		return storage.get(id);
	}

	@Override
	public boolean delete(Map<String, String> headers, Long id) throws NotFoundException {
		User remove = storage.remove(id);
		if (remove == null) {
			throw new NotFoundException(id);
		}
		return true;
	}

	@Override
	public Long create(Map<String, String> headers, User element) throws ConflictException {
		Long id = element.getId();
		if (id != null && storage.containsKey(id)) {
			throw new ConflictException(id);
		}
		if (id == null) {
			id = (long)storage.size();
		}
		User old = storage.put(id, element);
		if (old != null) {
			throw new ConflictException(id);
		}
		return id;
	}

	@Override
	public User update(Map<String, String> headers, Long id, User element) throws NotFoundException {
		if (storage.containsKey(id)) {
			storage.put(id, element);
		} else {
			throw new NotFoundException(id);
		}
		return element;
	}

}
