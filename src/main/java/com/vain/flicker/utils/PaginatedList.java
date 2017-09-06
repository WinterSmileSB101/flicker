package com.vain.flicker.utils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
public class PaginatedList<E> extends ArrayList<E> {

	private String next;
	private String self;

	public PaginatedList(String next, String self) {
		this.next = next;
		this.self = self;
	}

	public PaginatedList(int initialCapacity, String next, String self) {
		super(initialCapacity);
		this.next = next;
		this.self = self;
	}

	public PaginatedList(Collection<E> c, String next, String self) {
		super(c);
		this.next = next;
		this.self = self;
	}

	public String getNext() {
		return next;
	}

	public String getSelf() {
		return self;
	}
}
