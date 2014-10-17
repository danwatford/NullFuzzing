package com.foomoo.nullfuzzing;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class MockGenerator<T> extends Generator<T> {

	private T mockValue;

	public MockGenerator(Class<T> type) {
		super(type);
		mockValue = mock(type);
		when(mockValue.toString()).thenReturn(type.toString());
	}

	@Override
	public T generate(SourceOfRandomness random, GenerationStatus status) {
		T retVal = mockValue;
		mockValue = null;
		return retVal;
	}
}