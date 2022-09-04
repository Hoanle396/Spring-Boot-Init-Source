package com.courses.edu.helper;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class EncryptionDecryption {
	public static String encryptPassword(String inputPassword) {
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		return encryptor.encryptPassword(inputPassword);
	}

	public static boolean checkPassword(String inputPassword, String encryptedStoredPassword) {
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		return encryptor.checkPassword(inputPassword, encryptedStoredPassword);
	}

	public static void main(String args[]) throws Exception {
		String target = "imparator";
		String encrypted = EncryptionDecryption.encryptPassword(target);
		boolean decrypted = EncryptionDecryption.checkPassword("imparator", encrypted);

		System.out.println("String To Encrypt: " + target);
		System.out.println("Encrypted String:" + encrypted);
		System.out.println("Decrypted String:" + decrypted);

	}
}