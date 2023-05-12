package com.amazon.dmataccountmanger;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class temp {

	private static byte[] getSHA(String input) throws NoSuchAlgorithmException{
		MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
		return mDigest.digest(input.getBytes(StandardCharsets.UTF_8));
	}
	
	private static String toHexString(byte[] hash) {
		BigInteger number = new BigInteger(1,hash);
		StringBuilder hexString = new StringBuilder(number.toString(16));
		
		while(hexString.length()<64) {
			hexString.insert(0,'0');
			
		}
		return hexString.toString();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		Scanner scanner = new Scanner(System.in);	
		System.out.println("Enter String to be encrypted:");
		String inpString = scanner.nextLine();
		
		System.out.println("Output: "+toHexString(getSHA(inpString)));
	
	
}
}