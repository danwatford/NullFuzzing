package com.foomoo.nullfuzzing;

public class TestObj {
	public TestObj(Dep1 d1, Dep2 d2, Dep3 d3) {
		if (d1 == null || d2 == null || d3 == null) {
			throw new IllegalArgumentException();
		}
	}
}
