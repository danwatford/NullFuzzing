package com.foomoo.nullfuzzing;

import org.junit.Assume;
import org.junit.Rule;
import org.junit.contrib.theories.DataPoints;
import org.junit.contrib.theories.Theories;
import org.junit.contrib.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import com.foomoo.nullfuzzing.Dep1;
import com.foomoo.nullfuzzing.Dep2;
import com.foomoo.nullfuzzing.Dep3;
import com.foomoo.nullfuzzing.TestObj;

/**
 * Note that this test is running with the contributed JUnit Theories rather than experimental Theories.
 */
@RunWith(Theories.class)
public class ContribTheoryTest {

	@DataPoints
	public static final Dep1[] D1S = new Dep1[] { new Dep1(), null };

	@DataPoints
	public static final Dep2[] D2S = new Dep2[] { new Dep2(), null };

	@DataPoints
	public static final Dep3[] D3S = new Dep3[] { new Dep3(), null };

	@Rule
	public ExpectedException expected = ExpectedException.none();

	// This will cause an error as expected due to the missing null check in
	// the test object's constructor.
	@Theory
	public void throwsIfArgIsNull(Dep1 d1, Dep2 d2, Dep3 d3) {
		Assume.assumeTrue(d1 == null || d2 == null || d3 == null);

		expected.expect(IllegalArgumentException.class);
		new TestFailingObj(d1, d2, d3);
	}
}
