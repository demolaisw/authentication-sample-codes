package com.interswitchng.techquest.authentication.sample.codes;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import com.interswitchng.techquest.authentication.sample.codes.utils.PassportAuth;

public class PassportAuthSample {

	public static final String RESOURCE_URL = "https://sandbox.interswitchng.com/api/v1/payment/purchases";

	private static final String TIMESTAMP = "TIMESTAMP";
	private static final String NONCE = "NONCE";
	private static final String SIGNATURE_METHOD = "SIGNATURE_METHOD";
	private static final String SIGNATURE = "SIGNATURE";
	private static final String AUTHORIZATION = "AUTHORIZATION";

	private static final String CLIENT_ID = "CLIENT_ID";
	private static final String CLIENT_SECRET = "CLIENT_SECRET";
	
	public static void main(String[] args) throws Exception {
		getPassportAuth();
	}

	public static void getPassportAuth() throws UnsupportedEncodingException, NoSuchAlgorithmException {

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
		HashMap<String, String> passportAuth = PassportAuth.generatePassportAuth(httpMethod, resourceUrl,
				clientId, clientSecretKey, null, signatureMethod);

		// Print generated values
		System.out.println("Authorization: " + passportAuth.get(AUTHORIZATION));
		System.out.println("Timestamp: " + passportAuth.get(TIMESTAMP));
		System.out.println("Nonce: " + passportAuth.get(NONCE));
		System.out.println("Signature: " + passportAuth.get(SIGNATURE));
		System.out.println("SignatureMethod: " + passportAuth.get(SIGNATURE_METHOD));

	}

}
