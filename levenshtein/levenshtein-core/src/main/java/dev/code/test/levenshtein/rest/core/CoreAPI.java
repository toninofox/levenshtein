package dev.code.test.levenshtein.rest.core;

import java.util.Arrays;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class CoreAPI implements ICoreAPI {

	private static final Logger log = LoggerFactory.getLogger(CoreAPI.class);

	@Override
	public int calculate(CharSequence firstWord, CharSequence secondWord) {
		return calculate(firstWord, secondWord, false);
	}

	@Override
	public int calculate(CharSequence firstWord, CharSequence secondWord, boolean caseSensitive) {
		log.debug("Calculating distance between {} and {} case Sensitive? {}", firstWord, secondWord, caseSensitive);
		Objects.requireNonNull(firstWord, "First Word cannot be Null");
		Objects.requireNonNull(secondWord, "Second Word cannot be Null");

		if (!caseSensitive) {
			firstWord = firstWord.toString().toLowerCase();
			secondWord = secondWord.toString().toLowerCase();
		}

		if (firstWord.equals(secondWord)) {
			log.debug("Words are equals");
			return 0;
		}

		int firstLength = firstWord.length();
		int secondLength = secondWord.length();

		if (firstLength == 0) {
			log.debug("First Word is empty. Returning the second word length");
			return secondLength;
		}

		if (secondLength == 0) {
			log.debug("Second Word is empty. Returning the First word length");
			return firstLength;
		}

		if (firstLength > secondLength) {
			log.debug("swap the input strings to consume less memory");
			final CharSequence tmp = firstWord;
			firstWord = secondWord;
			secondWord = tmp;
			firstLength = secondLength;
			secondLength = secondWord.length();
		}

		int[] firstArray = firstWord.codePoints().toArray();
		int[] secondArray = secondWord.codePoints().toArray();

		int lastCost = secondArray.length;
		int[] a0 = new int[lastCost + 1];
		int[] a1 = new int[lastCost + 1];
		Arrays.setAll(a0, i -> i);

		for (int i = 0; i < firstArray.length; i++) {
			a1[0] = i + 1;

			for (int j = 0; j < lastCost; j++) {
				int cost = (firstArray[i] == secondArray[j]) ? 0 : 1;
				int next = a1[j] + 1;
				int previous = a0[j + 1] + 1;
				int previousCost = a0[j] + cost;
				a1[j + 1] = getMin(next, previous, previousCost);
			}

			prepareNextIteration(a0, a1);
		}

		int result = a1[lastCost];
		log.debug("Distance between {} and {} is {}", firstWord, secondWord, result);

		return result;
	}

	private int getMin(int next, int previous, int previousCost) {
		return Math.min(next, Math.min(previous, previousCost));
	}

	void prepareNextIteration(int[] v0, int[] v1) {
		Arrays.setAll(v0, j -> v1[j]);
	}

}
