package com.interswitchng.techquest.authentication.sample.codes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GenerateBearerAuth {
	
	private static final String PASSPORT_OAUTH_TOKEN_RESOURCE_URL = "https://sandbox.interswitchng.com/passport/oauth/token";
	private static final String CLIENT_ID = "CLIENT_ID";
	private static final String CLIENT_SECRET = "CLIENT_SECRET";

	
	public static void main(String[] args) throws ClientProtocolException, IOException, ParseException {
		
		String resourceUrl = PASSPORT_OAUTH_TOKEN_RESOURCE_URL;
		
		String cipher = CLIENT_ID + ":" + CLIENT_SECRET;
		
		String cipher64 = new String(org.apache.commons.codec.binary.Base64.encodeBase64(cipher.getBytes()));
		
		String authorizationData = "Basic " + cipher64; // Authorization Data
		
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("grant_type", "client_credentials"));
		postParameters.add(new BasicNameValuePair("scope", "profile"));
		
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(resourceUrl);
		
		post.setHeader("Authorization", authorizationData);

		post.setEntity(new UrlEncodedFormEntity(postParameters));
		
		HttpResponse response = client.execute(post);
		
		int responseCode = response.getStatusLine().getStatusCode();
		
		// get response string
		HttpEntity httpEntity = response.getEntity();
		InputStream inputStream = httpEntity.getContent();
		StringBuffer responseString = new StringBuffer();

		int c;
		// Reading Response from server
		while ((c = inputStream.read()) != -1) {
			responseString.append((char) c);
		}
		
		org.json.simple.JSONObject json = (org.json.simple.JSONObject)new JSONParser().parse(responseString.toString());
		System.out.println(json.get("access_token"));
		System.out.println(responseCode);
	}

}
