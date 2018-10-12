package org.web3j.protocol;

import java.io.IOException;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

public class GenerateWalletFile {

	public static void main(String[] args) throws IOException, CipherException {

		// 生成keysore文件

		// String pwd = "password123";
		//File dir = new File("d:\\");

		// String generateFullNewWalletFile =
		// WalletUtils.generateFullNewWalletFile(pwd,dir);
		//System.err.println(dir);
		
		
		//加载解密keystore文件
		
		Credentials credentials = WalletUtils.loadCredentials("password123",
				"D:\\UTC--2018-07-27T03-00-04.77000000Z--5ce52a2e409c3f178af27dc8aebca8a857b68619.json");
		String address = credentials.getAddress();
		System.out.println(address);
		
	}

}
