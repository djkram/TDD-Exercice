/**
 * 
 */
package org.bdigital.tdd.testJavaSample.it;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bdigital.tdd.testJavaSample.core.EvaluatorImpl;
import org.bdigital.tdd.testJavaSample.data.Candidate;
import org.bdigital.tdd.testJavaSample.manager.CandidateManager;
import org.bdigital.tdd.testJavaSample.manager.CandidateManagerImpl;
import org.bdigital.tdd.testJavaSample.service.InternetConnector;


import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author mpv
 * 
 */
public class CandidateManagerTestSystem extends TestCase {

	CandidateManagerImpl candidateManager;
	EvaluatorImpl evaluator;

	public void testExecuteTheCandidatesProcesCouldNotGetCandidates() {

		evaluator = new EvaluatorImpl();

		InternetConnector internetConnector = new InternetConnector();

		candidateManager = new CandidateManagerImpl();
		candidateManager.setConnector(internetConnector);
		candidateManager.setEvaluator(evaluator);

		try {
			Candidate candidate = candidateManager.getBestCandidate(10);
			
			Assert.assertNotNull(candidate);
			assertNotNull(candidate.getName());
			assertNotNull(candidate.getAge());
			assertNotNull(candidate.getCost());
			assertNotNull(candidate.getExperience());
			
		} catch (Exception e) {		
			fail();
		}

	}

}
