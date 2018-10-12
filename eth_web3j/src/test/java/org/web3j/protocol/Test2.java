package org.web3j.protocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.http.HttpService;

import mc.web3j.abi.FunctionEncoder;
import mc.web3j.abi.TypeReference;
import mc.web3j.abi.datatypes.Address;
import mc.web3j.abi.datatypes.Function;

public class Test2 {

	
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public static <E> void main(String[] args) throws IOException {
		Web3j web3j = new JsonRpc2_0Web3j(new HttpService("http://localhost:7545"));
		List<Address> arrayList = new ArrayList<Address>();
		arrayList.add(new Address("0xb6191034Eb4B2c36f97235ae5A41e35Fee4075C5"));
		List<TypeReference<Address>> arrayList2 = new ArrayList<TypeReference<Address>>();
		arrayList2.add(new TypeReference<Address>(){});
		Function function = new Function("getBalance", arrayList, arrayList2);
        String encodedFunction = FunctionEncoder.encode(function);
		String value = web3j.ethCall(Transaction.createEthCallTransaction("0xb6191034Eb4B2c36f97235ae5A41e35Fee4075C5","0xa26B62197D018D6aB9354E435423430e43D88375", encodedFunction),DefaultBlockParameterName.PENDING).send().getValue();


	}
}
