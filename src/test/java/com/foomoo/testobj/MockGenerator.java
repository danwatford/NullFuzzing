package com.foomoo.testobj;

import static org.mockito.Mockito.mock;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class MockGenerator<T> extends Generator<T> {

	private boolean first = true;
	private T mockValue;

	public MockGenerator(Class<T> type) {
		super(type);
		mockValue = mock(type);
	}

	@Override
	public T generate(SourceOfRandomness random, GenerationStatus status) {
		if (first) {
			first = false;
			return null;
		} else {
			return mockValue;
		}
	}
}