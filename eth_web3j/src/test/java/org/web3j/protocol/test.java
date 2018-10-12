package org.web3j.protocol;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.http.HttpService;

import mc.web3j.abi.FunctionEncoder;
import mc.web3j.abi.TypeReference;
import mc.web3j.abi.datatypes.Address;
import mc.web3j.abi.datatypes.Function;

import mc.web3j.abi.TypeReference;
import mc.web3j.abi.datatypes.Address;
import mc.web3j.abi.datatypes.Function;



public abstract class test implements Web3j {
//默认Windows ipc地址 \\.\pipe\geth.ipc

//	public static void main(String[] args) throws IOException {
////		Web3j web3 = new Web3j(new WindowsIpcService("/path/to/namedpipefile"));
////		Web3jService we = new WindowsIpcService("/path/to/namedpipefile");
////		String ipc = "\\\\"+"."+"\\"+"pipe"+"\\"+"geth.ipc";
////		
////		Web3j web3 = Web3j.build(new HttpService(""));
////		String jsonrpc = web3.ethAccounts().getJsonrpc();
////		System.err.println(jsonrpc);
////		EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(
////	             "0x89De0c0175060DF0F731782AcC8977Cc741cEc63", DefaultBlockParameterName.LATEST).send();
////System.out.println(ethGetTransactionCount);
//		 Web3j web3 = JsonRpc2_0Web3j.build(new HttpService("127.0.0.1:7585"));
//	        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
//	        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
//	        System.out.println(clientVersion);
//
//	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static <E> void main(String[] args) throws Exception {
//
        String a = "0x90b98a11000000000000000000000000028ec1c468d903a3e2a819a9707d5cccd0b91bc90000000000000000000000000000000000000000000000000000000000000064";
        String b = "0x90b98a11000000000000000000000000b6191034eb4b2c36f97235ae5a41e35fee4075c50000000000000000000000000000000000000000000000000000000000000064";
		
	}

}
