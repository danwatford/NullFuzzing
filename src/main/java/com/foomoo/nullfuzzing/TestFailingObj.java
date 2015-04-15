package com.foomoo.nullfuzzing;

public class TestFailingObj {
	public TestFailingObj(Dep1 d1, Dep2 d2, Dep3 d3) {
		// This is a broken implementation as it does not null-check d2
		if (d1 == null || d3 == null) {
			throw new IllegalArgumentException();
		}
	}
}
