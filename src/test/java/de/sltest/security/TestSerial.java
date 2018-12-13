package de.sltest.security;

import de.slfw.security.SerialGenerartor;

public class TestSerial {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SerialGenerartor sg = new SerialGenerartor();
		System.out.println(sg.genearteEncryptedSerial("DBH222"));
		System.out.println((int)'0');
		System.out.println(SerialGenerartor.generateSerial(10));
	}
}
