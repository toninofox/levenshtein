package dev.code.test.levenshtein.rest.core;

public interface ICoreAPI {

	int calculate(CharSequence firstWord, CharSequence secondWord);

	int calculate(CharSequence firstWord, CharSequence secondWord, boolean caseSensitive);

}
