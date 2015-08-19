package com.interswitchng.techquest.authentication.sample.codes;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import com.interswitchng.techquest.authentication.sample.codes.utils.InterswitchAuth;

public class InterswitchAuthSample {

	public static final String RESOURCE_URL = "https://sandbox.interswitchng.com/api/v1/payment/purchases";

	private static final String TIMESTAMP = "TIMESTAMP";
	private static final String NONCE = "NONCE";
	private static final String SIGNATURE_METHOD = "SIGNATURE_METHOD";
	private static final String SIGNATURE = "SIGNATURE";
	private static final String AUTHORIZATION = "AUTHORIZATION";

	private static final String CLIENT_ID = "20FCA3E4D0574CF8B494E4619713606C";
	private static final String CLIENT_SECRET = "k1F4EXtYfKHxUCtVSLfRp6U+wrqdr+l/17acNDf/aOFekBxTTMB+TmfOxnfp4rTy";

	public static void main(String[] args) throws Exception {
		getInterswitchAuth();
	}

	public static void getInterswitchAuth() throws UnsupportedEncodingException, NoSuchAlgorithmException {

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
		HashMap<String, String> interswitchAuth = InterswitchAuth.generateInterswitchAuth(httpMethod, resourceUrl,
				clientId, clientSecretKey, null, signatureMethod);

		// Print generated values
		System.out.println("Authorization: " + interswitchAuth.get(AUTHORIZATION));
		System.out.println("Timestamp: " + interswitchAuth.get(TIMESTAMP));
		System.out.println("Nonce: " + interswitchAuth.get(NONCE));
		System.out.println("Signature: " + interswitchAuth.get(SIGNATURE));
		System.out.println("SignatureMethod: " + interswitchAuth.get(SIGNATURE_METHOD));

	}

}
