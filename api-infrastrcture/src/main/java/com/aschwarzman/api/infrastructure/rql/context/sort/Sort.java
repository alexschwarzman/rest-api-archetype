package com.aschwarzman.api.infrastructure.rql.context.sort;

import com.aschwarzman.api.infrastructure.rql.context.QueryPart;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author aschwarzman
 * @since Jul 29, 2020
 */
public class Sort implements QueryPart {

	private List<SortPart> parts = new ArrayList();

	public Sort(List<SortPart> parts) {
		this.parts = parts;
	}

	public List<SortPart> getParts() {
		return parts;
	}

	@Override
	public String toString() {
		return "Sort [parts=" + parts + "]";
	}

}
