package org.bdigital.tdd.testJavaSample.core;

import java.math.BigDecimal;
import java.util.List;

import org.bdigital.tdd.testJavaSample.data.Candidate;



/**
 * @author mpv
 *
 */
public class EvaluatorImpl implements Evaluator {


	public Double calculateRating(Integer age, Integer experience, Integer cost) {
		
		Double result = 0.0;
		
		Integer d1 = (age + (experience*experience));
		Double d2 = Math.sqrt(cost);
		

		
		if(d2!=0){
			
			result = d1/d2;
			
		}
		
		return result;
		
//        // Normalize the position between 1 to 0
//		Double tempRating = ((10.0/numCandidates*result));
//        BigDecimal tempRating1Decimal = new BigDecimal( tempRating ).setScale( 1,BigDecimal.ROUND_DOWN );
//		
//		return tempRating1Decimal.doubleValue();
	}

	public boolean isSuitable(int age, int experience, int cost) {
		
		if(age >= 35 && experience >= 10 && cost<=35000 )
		{
			return true;
		}	
		return false;
	}

	public Double normalizeRating(Double rating, Double maxRating) {

      // Normalize the position between 10 to 0
		Double tempRating = ((10.0/maxRating*rating));
        BigDecimal tempRating1Decimal = new BigDecimal( tempRating ).setScale( 1,BigDecimal.ROUND_DOWN );
		
		return tempRating1Decimal.doubleValue();
		
	}


}
