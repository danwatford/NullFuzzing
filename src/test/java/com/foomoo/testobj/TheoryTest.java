package com.foomoo.testobj;

import org.junit.Assume;
import org.junit.Rule;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class TheoryTest {

	@DataPoints
	public static final Dep1[] D1S = new Dep1[] { new Dep1(), null };

	@DataPoints
	public static final Dep2[] D2S = new Dep2[] { new Dep2(), null };

	@DataPoints
	public static final Dep3[] D3S = new Dep3[] { new Dep3(), null };

	@Rule
	public ExpectedException expected = ExpectedException.none();

	// This will cause an error as expected due to the missing null check in
	// TestObj's constructor.
	@Theory
	public void throwsIfArgIsNull(Dep1 d1, Dep2 d2, Dep3 d3) {
		Assume.assumeTrue(d1 == null || d2 == null || d3 == null);

		expected.expect(IllegalArgumentException.class);
		new TestObj(d1, d2, d3);
	}
}
