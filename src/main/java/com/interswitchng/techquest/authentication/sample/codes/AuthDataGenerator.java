package com.interswitchng.techquest.authentication.sample.codes;

import com.interswitchng.techquest.authentication.sample.codes.utils.SecureDecryptor;
import com.interswitchng.techquest.system.service.util.api.SystemConstantsApi;

import hsmprobe.util.Base64;

public class AuthDataGenerator {
	
	public static void main(String args[])
	{
		String pin = "1234";
		String pan = "5060990580000166902";
		String expiryDate = "5003";
		String cvv2 = "111";
		String version = "";
		String otpIdentifier = "";
		String authData = pan + "D" + pin + "D" + expiryDate + "D" + cvv2 + "D" + version + "D" + otpIdentifier;
		
		try
		{
			String modulus = "9c7b3ba621a26c4b02f48cfc07ef6ee0aed8e12b4bd11c5cc0abf80d5206be69e1891e60fc88e2d565e2fabe4d0cf630e318a6c721c3ded718d0c530cdf050387ad0a30a336899bbda877d0ec7c7c3ffe693988bfae0ffbab71b25468c7814924f022cb5fda36e0d2c30a7161fa1c6fb5fbd7d05adbef7e68d48f8b6c5f511827c4b1c5ed15b6f20555affc4d0857ef7ab2b5c18ba22bea5d3a79bd1834badb5878d8c7a4b19da20c1f62340b1f7fbf01d2f2e97c9714a9df376ac0ea58072b2b77aeb7872b54a89667519de44d0fc73540beeaec4cb778a45eebfbefe2d817a8a8319b2bc6d9fa714f5289ec7c0dbc43496d71cf2a642cb679b0fc4072fd2cf";
			String privateExponent = "4913cc0183c7a4a74b5405db55a15db8942f38c8cd7974b3644f6b625d22451e917345baa9750be9f8d10da47dbb45e602c86a6aa8bc1e7f7959561dbaaf35e78a8391009c8d86ee11da206f1ca190491bd765f04953765a2e55010d776044cb2716aee6b6f2f1dc38fce7ab0f4eafec8903a73555b4cf74de1a6bfc7f6a39a869838e3678dcbb96709068358621abf988e8049d5c07d128c5803e9502c05c3e38f94658480621a3e1c75fb4e39773e6eec50f5ef62958df864874ef0b00a0fb86f8382d1657381bc3c283567927f1f68d60205fd7ca1197265dd85c173badc1a15044f782602a9e14adc56728929c646c24fe8e10d26afc733158841d9ed4d1";
			String publicExponent = "010001";

			SystemConstantsApi systemConstantsApi = null;
			SecureDecryptor secureDecryptor = SecureDecryptor.getInstance(systemConstantsApi, modulus, privateExponent, publicExponent);
			
			byte []encryptedAuthDataBytes = secureDecryptor.encrypt(authData, systemConstantsApi);
			
			String encryptedAuthDataBase64 = Base64.encodeBytes(encryptedAuthDataBytes).replaceAll("\\r|\\n", "");
			
			System.out.println("AuthData: " + authData);
			System.out.println("Encrypted AuthData: " + encryptedAuthDataBase64);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
