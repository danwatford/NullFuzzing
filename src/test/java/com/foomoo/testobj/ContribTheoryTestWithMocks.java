package com.foomoo.testobj;

import static org.mockito.Mockito.mock;

import org.junit.Assume;
import org.junit.Rule;
import org.junit.contrib.theories.DataPoints;
import org.junit.contrib.theories.Theories;
import org.junit.contrib.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class ContribTheoryTestWithMocks {

	@DataPoints
	public static final Dep1[] D1S = new Dep1[] { mock(Dep1.class), null };

	@DataPoints
	public static final Dep2[] D2S = new Dep2[] { mock(Dep2.class), null };

	@DataPoints
	public static final Dep3[] D3S = new Dep3[] { mock(Dep3.class), null };

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
