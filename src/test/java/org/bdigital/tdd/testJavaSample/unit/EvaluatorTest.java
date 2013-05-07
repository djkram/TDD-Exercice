package org.bdigital.tdd.testJavaSample.unit;

import org.bdigital.tdd.testJavaSample.core.Evaluator;
import org.bdigital.tdd.testJavaSample.core.EvaluatorImpl;

import junit.framework.Assert;
import junit.framework.TestCase;

public class EvaluatorTest extends TestCase {

	
	Evaluator evaluator = new EvaluatorImpl();
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCalculateRating() {
		
		Integer age = 0;
		Integer experience = 0;
		Integer cost = 0;
		
		Double result = evaluator.calculateRating(age, experience, cost); 
		
		Assert.assertNotNull(result);
		
		//Assert.assertEquals(4f, result);
		
		// First candidate
		age = 25;
		experience = 5;
		cost = 30000;
		
		Double candidate1 = evaluator.calculateRating(age, experience, cost); 
		
		// this is better for age
		age = 35;
		experience = 5;
		cost = 30000;
	
		Double candidate2 = evaluator.calculateRating(age, experience, cost); 
		
		// this is better for  age + experience
		age = 35;
		experience = 10;
		cost = 30000;
		
		Double candidate3 = evaluator.calculateRating(age, experience, cost); 
		
		// this is better for cost
		age = 35;
		experience = 10;
		cost = 20000;
		
		Double candidate4 = evaluator.calculateRating(age, experience, cost); 
		
		// this have more cost but is better for experience
		age = 35;
		experience = 15;
		cost = 40000;
		
		Double candidate5 = evaluator.calculateRating(age, experience, cost);
		
		// This have so much cost
		age = 35;
		experience = 15;
		cost = 70000;
		
		Double candidate6 = evaluator.calculateRating(age, experience, cost);

		// the better wil be the candidate5
		Assert.assertTrue(candidate6 < candidate5 && candidate5 > candidate4 && candidate4 > candidate3 && candidate3 > candidate2 && candidate2 > candidate1);
		
		
	}

	public void testIsSuitable() {
		
		//0 1 1
		int age = 34;
		int experience = 10;
		int cost = 25000;
				
		Boolean result = evaluator.isSuitable(age, experience, cost);
		Assert.assertFalse(result);
		
		
		// 1 1 1
		age = 35;
		experience = 11;
		cost = 24999;
				
		result = evaluator.isSuitable(age, experience, cost);
		Assert.assertTrue(result);
		
		// 1 0 1
		age = 36;
		experience = 4;
		cost = 1;
				
		result = evaluator.isSuitable(age, experience, cost);
		Assert.assertFalse(result);
		
		// 1 1 0
		age = 50000;
		experience = 50000;
		cost = 35001;
				
		result = evaluator.isSuitable(age, experience, cost);
		Assert.assertFalse(result);
		
		
	}
	
	public void testNormalize(){
		
		Double result1 = evaluator.normalizeRating(100d, 5000d);
		Double result2 = evaluator.normalizeRating(300d, 5000d);
		Double result3 = evaluator.normalizeRating(1200d, 5000d);
		Double result4 = evaluator.normalizeRating(5000d, 5000d);
		
		Assert.assertTrue(result4 > result1);
	}

}
