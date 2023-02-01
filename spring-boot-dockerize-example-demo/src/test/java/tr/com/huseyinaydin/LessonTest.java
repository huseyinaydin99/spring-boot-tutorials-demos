package tr.com.huseyinaydin;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tr.com.huseyinaydin.controller.Lesson;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public class LessonTest {
	Lesson myMath = new Lesson();

	// LeessonTest.sum
	// 1,2,3 => 6
	@Test
	public void sum_with3numbers() {
		System.out.println("Test1");
		assertEquals(6, myMath.getSum(new int[] { 1, 2, 3 }));
	}

	@Test
	public void sum_with1number() {
		System.out.println("Test2");
		assertEquals(3, myMath.getSum(new int[] { 3 }));
	}
}