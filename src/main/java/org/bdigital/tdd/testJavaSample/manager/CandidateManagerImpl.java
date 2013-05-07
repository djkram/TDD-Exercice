package org.bdigital.tdd.testJavaSample.manager;

import java.util.List;
import java.util.Vector;

import org.bdigital.tdd.testJavaSample.core.Evaluator;
import org.bdigital.tdd.testJavaSample.core.EvaluatorImpl;
import org.bdigital.tdd.testJavaSample.data.Candidate;
import org.bdigital.tdd.testJavaSample.service.InternetConnector;



public class CandidateManagerImpl implements CandidateManager {

	Evaluator evaluator = new EvaluatorImpl();
	InternetConnector connector = new InternetConnector();

	public Candidate getBestCandidate(int numCandidates) throws Exception {

		List<Candidate> listCandidates = new Vector<Candidate>();
		List<Candidate> listSuitableCandidates = new Vector<Candidate>();
		List<Candidate> listSuitableCandidatesWithRating = new Vector<Candidate>();

		for (int i = 0; i < numCandidates; i++) {

			Candidate candidate = connector.getCanidate();
			if(candidate != null)
			{
				listCandidates.add(candidate);
			}

		}

		if (listCandidates.isEmpty()) {
			throw new Exception("Any candidate presented");
		}
		
		
		for (Candidate candidate : listCandidates) {

			boolean isSuitable = evaluator.isSuitable(candidate.getAge(), candidate.getExperience(), candidate.getCost());

			if (isSuitable) {

				listSuitableCandidates.add(candidate);

			}
		}

		if (listSuitableCandidates.isEmpty()) {
			throw new Exception("Any Candidate Suitable");
		}

		Candidate result = null;

		Double maxRating = 0.0;
		for (Candidate candidate : listSuitableCandidates) {

			Double rating = evaluator.calculateRating(candidate.getAge(), candidate.getExperience(), candidate.getCost());

			candidate.setRating(rating);
			
			listSuitableCandidatesWithRating.add(candidate);
			
			if(maxRating < rating){
				maxRating = rating;
				result = candidate;
			}

		}
		
		// Normalize the ratings between 0 t0 10		
		result.setRating(evaluator.normalizeRating(result.getRating(), maxRating));	

		return result;
	}

	public void setEvaluator(Evaluator evaluator) {
		this.evaluator = evaluator;
	}

	public void setConnector(InternetConnector connector) {
		this.connector = connector;
	}

}
