/**
 * 
 */
package org.bdigital.tdd.testJavaSample.unit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bdigital.tdd.testJavaSample.core.EvaluatorMock;
import org.bdigital.tdd.testJavaSample.data.Candidate;
import org.bdigital.tdd.testJavaSample.manager.CandidateManagerImpl;
import org.bdigital.tdd.testJavaSample.service.InternetConnector;


import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author mpv
 *
 */
public class CandidateManagerTest extends TestCase {

	CandidateManagerImpl candidateManager;
	EvaluatorMock        evaluator;
	
	
	
	public void testExecuteTheCandidatesProcesCouldNotGetCandidates(){
		
		evaluator = new EvaluatorMock();
	
		InternetConnector internetConnector = mock(InternetConnector.class);
		when(internetConnector.getCanidate()).thenReturn(null);
		
		candidateManager = new CandidateManagerImpl();
		candidateManager.setConnector(internetConnector);
		candidateManager.setEvaluator(evaluator);

		try {
			candidateManager.getBestCandidate(10);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().contains("Any candidate presented"));
		}
	
	}
	
	public void testExecuteTheCandidatesProcesNotWithoutCandidates(){
	
		evaluator = new EvaluatorMock();
	
		InternetConnector internetConnector = mock(InternetConnector.class);
		Candidate  candidate= new Candidate("Jose", 10, 2, 100000);	
		when(internetConnector.getCanidate()).thenReturn(null).thenReturn(candidate);
		
		candidateManager = new CandidateManagerImpl();
		candidateManager.setConnector(internetConnector);
		candidateManager.setEvaluator(evaluator);

		try {
			candidateManager.getBestCandidate(10);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().contains("Any Candidate Suitable"));
		}
	
	}
	
	
	public void testExecuteTheCandidatesProcesJohn() throws Exception{
		
		evaluator = new EvaluatorMock();
		
		InternetConnector internetConnector = mock(InternetConnector.class);
		
		Candidate  candidate1= new Candidate("Jose", 10, 2, 100000);
		Candidate  candidate2= new Candidate("John", 40, 1, 29000);
		Candidate  candidate3= new Candidate("Jan", 40, 1, 30000);
		Candidate  candidate4= new Candidate("Juan", 40, 1, 50000);
		
		when(internetConnector.getCanidate()).thenReturn(null)
		.thenReturn(candidate1)
		.thenReturn(candidate2)
		.thenReturn(candidate3)
		.thenReturn(candidate4);
		
		candidateManager = new CandidateManagerImpl();
		candidateManager.setConnector(internetConnector);
		candidateManager.setEvaluator(evaluator);
		
		Candidate candidate = candidateManager.getBestCandidate(4);
		
		Assert.assertEquals("John", candidate.getName());
		
	}
	
}
