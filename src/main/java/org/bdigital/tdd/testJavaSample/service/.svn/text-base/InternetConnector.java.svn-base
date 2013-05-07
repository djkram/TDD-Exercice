package org.bdigital.tdd.testJavaSample.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bdigital.tdd.testJavaSample.data.Candidate;




public class InternetConnector{
	 private Log logger = LogFactory.getLog(this.getClass());


	private static final long maxAttemps = 10l;
	private String urlCandidates = "http://tdd-testing.appspot.com/" ;
	
	
	public Candidate getCanidate(){
		
		String result = null;
		try {
			result = getResource(urlCandidates);
		}catch (Exception e) {
			logger.warn("Could not get server response from url[" + urlCandidates + "]");
			return null;
		}
		
	    Candidate candidate = parseResult(result);
	    if(candidate == null)
	    {
	    	logger.warn("Could not parse server response[" + result + "]");
	    }
		
		return candidate;

	}

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

	public String getResource(URL url) {

        HttpURLConnection connection = null;
        InputStream is = null;
        InputStreamReader input;
        BufferedReader reader =  null;
        StringBuilder builder = new StringBuilder();
       
       
        try {
            // System.out.println(url.toString());

            connection = (HttpURLConnection) url.openConnection();
        
            //connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Accept-Language", "en");
            connection.setRequestMethod("GET");

            boolean connected = false;
            // we do some attempts to avoid unavailable resources due to
            // temporary network congestion or other little problems.
            int attempts = 1;
            do {
                if (maxAttemps <= attempts++) {
                    final String msg = "Connection to " + url
                            + " not available.";
                   
                    throw new Exception(msg);
                }

                boolean attempt = true;
                try {
                    connection.connect();
                } catch (UnknownHostException e) {
                    attempt = false;
                }
                /*
                 * Response Codes All resources return appropriate HTTP status
                 * codes, the most important ones are: 200 OK Everything went
                 * fine 401 Unauthorized You forgot to add the OAuth
                 * authorization or your OAuth signing is not correct 404 Not
                 * Found Either you are requesting an invalid URL or the
                 * resource in question doesn't exist (no such place, for
                 * example). 500 Internal Server Error We did something wrong.
                 * We get automatically notified about all 500 errors and will
                 * investigate them. 503 Service Unavailable
                 */

                if (attempt) {
                    int code = connection.getResponseCode();
                    switch (code) {
                    case 200:
                        connected = true;
                        is = connection.getInputStream();
                        try {
                			input = new InputStreamReader(is, "ISO-8859-1" );
                		} catch (UnsupportedEncodingException e1) {
                        	throw new Exception("UnsupportedEncodingException: " + "ISO-8859-1");
                		}
                        reader = new BufferedReader(input);
                        break;
                    case 401:
                    case 404:
                    case 500:
                    case 503:
                    default:
                        connected = false;
                        break;
                    }
                }
            } while (!connected);
               
            String line;
            try {

                line = reader.readLine();       
                while (line != null) {
                	builder.append(line);
                    line = reader.readLine();
                }

            } catch (IOException e) {
                final String message = "Error reading from terra: " + url;
                throw new Exception(message);
            }

             ////////////////////////// MOCK CREATOR
             /////////////////////////////
             // THIS CODE CAN BE USED TO GENERATE A FILE WITH THE RESOURCES
             // (AS IN THE CASE OF A MOCK)
//             File f = new File("spain.xml");
//             FileOutputStream outStream = null;
//             try {
//             outStream = new FileOutputStream(f, true);
//             PrintStream ps = new PrintStream(outStream);
//             ps.println(url);
//             ps.println(builder.toString());
//             outStream.flush();
//             ps.flush();
//             ps.close();
//             outStream.close();
//             } catch (IOException e) {
//             e.printStackTrace();
//             } catch (Exception e) {
//             e.printStackTrace();
//             }
             /////////////////////////////////////////////////////////////////////

            if (is != null)
                try {
                    is.close();
                } catch (Exception ignore) {
                }
            if (connection != null)
            {
                connection.disconnect();
            }

        } catch (Exception e) {
        	  logger.error(e.getCause() + " | " + e.getMessage());
        } finally {

            if (is != null)
            {
                try {
                    is.close();
                } catch (Exception ignore) {
                }
            }

            if (connection != null)
            {
                connection.disconnect();
            }
        }
        return builder.toString();
    }

	public String getResource(String url) throws Exception {
		URL realUrl;
		try {
			realUrl = new URL(url);
		} catch (MalformedURLException e) {
			final String message = "MalformedURLException in getResource. \""
					+ url + "\"";
			logger.warn(message);
			return null;
		}
		return this.getResource(realUrl);
	}

	public void setUrlCandidates(String urlCandidates) {
		this.urlCandidates = urlCandidates;
	}
	
	
}
