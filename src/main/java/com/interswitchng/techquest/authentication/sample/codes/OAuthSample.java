package com.interswitchng.techquest.authentication.sample.codes;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import com.interswitchng.techquest.authentication.sample.codes.utils.OAuth;

public class OAuthSample {

	public static final String RESOURCE_URL = "https://sandbox.interswitchng.com/api/v1/payment/purchases";

	private static final String TIMESTAMP = "TIMESTAMP";
	private static final String NONCE = "NONCE";
	private static final String SIGNATURE_METHOD = "SIGNATURE_METHOD";
	private static final String SIGNATURE = "SIGNATURE";
	private static final String AUTHORIZATION = "AUTHORIZATION";

	private static final String CLIENT_ID = "CLIENT_ID";
	private static final String CLIENT_SECRET = "CLIENT_SECRET";
	
	public static void main(String[] args) throws Exception {
		getOAuth();
	}

	public static void getOAuth() throws UnsupportedEncodingException, NoSuchAlgorithmException {

		// Authentication is done via a POST Method.
		String httpMethod = "POST";

		// This is the request resource URL.
		String resourceUrl = RESOURCE_URL;

		// get clientId from Interswitch Developer Console.
		String clientId = CLIENT_ID;

		// get clientSecretKey from Interswitch Developer Console
		String clientSecretKey = CLIENT_SECRET;

		// Signature Method is the discretion of developer,
		// but we recommend at least SHA-256
		String signatureMethod = "SHA1";

		// This our Authorization details that we'll add to our headers,
		// the InterswitchAuth configuration can be found under Authentications
		// above.
		HashMap<String, String> oAuth = OAuth.generateOAuth(httpMethod, resourceUrl,
				clientId, clientSecretKey, null, signatureMethod);

		// Print generated values
		System.out.println("Authorization: " + oAuth.get(AUTHORIZATION));
		System.out.println("Timestamp: " + oAuth.get(TIMESTAMP));
		System.out.println("Nonce: " + oAuth.get(NONCE));
		System.out.println("Signature: " + oAuth.get(SIGNATURE));
		System.out.println("SignatureMethod: " + oAuth.get(SIGNATURE_METHOD));

	}

}
