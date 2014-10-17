package com.foomoo.nullfuzzing;

import org.junit.Assume;
import org.junit.Rule;
import org.junit.contrib.theories.Theories;
import org.junit.contrib.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import com.foomoo.nullfuzzing.Dep1;
import com.foomoo.nullfuzzing.Dep2;
import com.foomoo.nullfuzzing.Dep3;
import com.foomoo.nullfuzzing.TestObj;
import com.pholser.junit.quickcheck.ForAll;
import com.pholser.junit.quickcheck.From;

@RunWith(Theories.class)
public class ContribTheoryWithGeneratorsTest {

	@Rule
	public ExpectedException expected = ExpectedException.none();

	// This will cause an error as expected due to the missing null check in
	// TestObj's constructor.
	@Theory
	public void throwsIfArgIsNull(
			@ForAll(sampleSize = 2) @From(MockGenerator.class) Dep1 d1,
			@ForAll(sampleSize = 2) @From(MockGenerator.class) Dep2 d2,
			@ForAll(sampleSize = 2) @From(MockGenerator.class) Dep3 d3) {
		
		Assume.assumeTrue(d1 == null || d2 == null || d3 == null);

		expected.expect(IllegalArgumentException.class);
		new TestObj(d1, d2, d3);
	}
}
