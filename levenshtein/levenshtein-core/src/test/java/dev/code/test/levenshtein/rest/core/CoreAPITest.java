package dev.code.test.levenshtein.rest.core;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.security.SecureRandom;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CoreAPITest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	private CoreAPI coreAPI;

	@Before
	public void setUp() throws Exception {
		coreAPI = new CoreAPI();
	}

	@Test
	public final void shouldThrowExceptionIfFirstWordIsNull() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("First Word");
		coreAPI.calculate(null, "MySecondWord");
	}

	@Test
	public final void shouldThrowExceptionIfSecondWordIsNull() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("Second Word");
		coreAPI.calculate("MyFirstWord", null);
	}

	@Test
	public void ifTwoWordsAreEqualsShouldBe0() throws Exception {
		String firstWord = "MyWord";
		assertThat(coreAPI.calculate(firstWord, firstWord), is(0));
	}
	
	@Test
	public void ifTwoWordsAreEqualsWithDifferentCaseShouldBe0IfNotSpecified() throws Exception {
		String firstWord = "myword";
		String secondWord = "MYWORD";
		assertThat(coreAPI.calculate(firstWord, secondWord), is(0));
	}
	
	@Test
	public void ifTwoWordsAreEqualsWithDifferentCaseShouldBe0IfCaseInsensitiveIsSpecified() throws Exception {
		String firstWord = "myword";
		String secondWord = "MYWORD";
		assertThat(coreAPI.calculate(firstWord, secondWord,false), is(0));
	}
	
	
	
	@Test
	public void ifTwoWordsAreEqualsWithDifferentCaseShouldBeMaxLengthIfCaseSensitiveIsSpecified() throws Exception {
		String firstWord = "myword";
		String secondWord = "MYWORD";
		assertThat(coreAPI.calculate(firstWord, secondWord, true), is(firstWord.length()));
	}
	
	@Test
	public void ifTwoWordsAreEqualsWithMixedDifferentCaseShouldBeMaxLengthIfCaseSensitiveIsSpecified() throws Exception {
		String firstWord = "myWorD";
		String secondWord = "MYwORd";
		assertThat(coreAPI.calculate(firstWord, secondWord, true), is(firstWord.length()));
	}

	@Test
	public void ifFirstWordIsEmptyTheDistanceIsTheSecondWordLength() throws Exception {
		String firstWord = "";
		String secondWord = randomString();
		assertThat(coreAPI.calculate(firstWord, secondWord), is(secondWord.length()));
	}

	@Test
	public void ifSecondWordIsEmptyTheDistanceIsTheFirstWordLength() throws Exception {
		String firstWord = randomString();
		String secondWord = "";
		assertThat(coreAPI.calculate(firstWord, secondWord), is(firstWord.length()));
	}

	@Test
	public void ifBothWordsAreEmptyTheDistanceIsZero() throws Exception {
		String firstWord = "";
		String secondWord = "";
		assertThat(coreAPI.calculate(firstWord, secondWord), is(0));
	}

	@Test
	public void shouldBeGeneratedForVeryLongString() throws Exception {
		int len = 10000;
		assertThat(coreAPI.calculate(randomString(len), randomString(len)), is(lessThanOrEqualTo(len)));
	}

	@Test
	public void shouldBeThreeGivenTheExampleInRequirements() throws Exception {
		assertThat(coreAPI.calculate("kitten", "sitting"), is(3));
	}
	
	@Test
	public void shouldBeThreeGivenTheExampleReversedInRequirements() throws Exception {
		assertThat(coreAPI.calculate("sitting", "kitten"), is(3));
	}

	private String randomString() {
		return randomString((int) (Math.random() * 10));
	}

	String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		SecureRandom rnd = new SecureRandom();
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

}
