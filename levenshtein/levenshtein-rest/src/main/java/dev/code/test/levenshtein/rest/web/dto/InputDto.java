package dev.code.test.levenshtein.rest.web.dto;

public class InputDto {

	public CharSequence firstWord;
	public CharSequence secondWord;
	public boolean caseSensitive;
	
	@Override
	public String toString() {
		return "InputDto [firstWord=" + firstWord + ", secondWord=" + secondWord + ", caseSensitive=" + caseSensitive + "]";
	}
	
	
}
