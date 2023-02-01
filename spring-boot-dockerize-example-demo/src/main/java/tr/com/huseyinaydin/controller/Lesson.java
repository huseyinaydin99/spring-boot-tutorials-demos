package tr.com.huseyinaydin.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public class Lesson {
	
	public int getSum(int[] numbers) {
		int sum = 0;
		for (int i : numbers) {
			sum += i;
		}
		return sum;
	}
	
}