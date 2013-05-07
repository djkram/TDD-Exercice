package org.bdigital.tdd.testJavaSample.core;

public class EvaluatorMock implements Evaluator {

	public boolean isSuitable(int age, int experience, int cost) {
		if(age > 35)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public Double calculateRating(Integer age, Integer experience, Integer cost) {

		if(cost<50000){
			return 3.0;
		}
		
		if(cost<40000){
			return 5.0;
		}
		
		if(cost<30000){
			return 7.0;
		}
		

		return 1.0;
	}

	public Double normalizeRating(Double rating, Double maxRating) {

		return rating;
	}

}
