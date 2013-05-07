package org.bdigital.tdd.testJavaSample.unit;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bdigital.tdd.testJavaSample.data.Candidate;
import org.bdigital.tdd.testJavaSample.service.InternetConnector;


import junit.framework.Assert;
import junit.framework.TestCase;

public class InternetConnectorTest extends TestCase {
	
	public Candidate parseResult(String serverResponse){
		Candidate candidate = new Candidate();
		Pattern candidatePattern = Pattern.compile("^(.*?),(.*?),(.*?),(.*?)$");
		Matcher candidateMatcher = candidatePattern.matcher(serverResponse);
		if (candidateMatcher.find()) {
			try{
				candidate.setName(candidateMatcher.group(1));
				candidate.setAge(new Integer(candidateMatcher.group(2)));
				candidate.setExperience(new Integer(candidateMatcher.group(3)));
				candidate.setCost(new Integer(candidateMatcher.group(4)));
				return candidate;
			}
			catch(NumberFormatException e){
			   return null;
			}
		}
		return null;
	}



	public void testParseResult() {
		InternetConnector internetConnector = new InternetConnector();
		Candidate result = internetConnector.parseResult("hola 2 2 2");
		Assert.assertNull(result);
		
		internetConnector = new InternetConnector();
		result = internetConnector.parseResult("hola,2,a,2");
		Assert.assertNull(result);
		
		internetConnector = new InternetConnector();
		result = internetConnector.parseResult("Juan,1,2,3");
		Assert.assertTrue(result.getAge().compareTo(1)==0);
		Assert.assertTrue(result.getExperience().compareTo(2)==0);
		Assert.assertTrue(result.getCost().compareTo(3)==0);
	}

	public void testGetResource() throws Exception {
		InternetConnector internetConnector = new InternetConnector();
		String result = internetConnector.getResource("http://noexite");
		assertEquals(0,result.length());	
		
	}
	
	public void testGetCandidate() throws Exception{
		InternetConnector internetConnector = new InternetConnector();
		
		InternetConnector spy = spy(internetConnector);
		
		spy.setUrlCandidates("Could not get");
		when(spy.getResource("Could not get")).thenThrow(new Exception());
		
		Candidate result = spy.getCanidate();
		Assert.assertNull(result);
		
		
		spy.setUrlCandidates("Could not parse");
		when(spy.getResource("Could not parse")).thenReturn("nullParse");
		when(spy.parseResult("nullParse")).thenReturn(null);
		result = spy.getCanidate();
		Assert.assertNull(result);
		
		spy.setUrlCandidates("ok");
		when(spy.getResource("ok")).thenReturn("ok");
		when(spy.parseResult("ok")).thenReturn(new Candidate("juan", 3,3,3));
		result = spy.getCanidate();
		Assert.assertNotNull(result);
		Assert.assertTrue(result.getName().compareTo("juan")==0);
		
		
	}

}