package eth_web3j;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;


public class test {

	@SuppressWarnings("unchecked")
	public static <E> void main(String[] args) throws Throwable {
//		Object[] param = {"0xf86d808504a817c80082dac09442294ca3f2836aaf761453e9bf1e18427353ff5a8901158e460913d00000801ba0fe0b60910ff60db96a2ed74bc1383905fedf28514e8259e53e287760181dd3eba01cfbd54bfe8caf67315cd37d14dfd76f4c2a96207e4c755fba50bea01302c6eb"};
//		 aJsonObject = {
//			  "from": "0xb60e8dd61c5d32be8058bb8eb970870f07233155",
//			  "to": "0xd46e8dd67c5d32be8058bb8eb970870f07244567",
//			  "gas": "0x76c0", // 30400
//			  "gasPrice": "0x9184e72a000", // 10000000000000
//			  "value": "0x9184e72a", // 2441406250
//			  "data": "0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb970870f072445675058bb8eb970870f072445675"
//			};
		 JSONObject param = new JSONObject();
		 
//		 param.put("from", "0xb6191034Eb4B2c36f97235ae5A41e35Fee4075C5");
//		 param.put("to",  "0x028eC1c468D903a3e2A819A9707d5cCcd0B91bc9");
//		 param.put("gas", "0x76c0");
//		 param.put("gasPrice", "0x9184e72a000");
//		 param.put("value", "0x9184e72a");
//		 String methodName = "eth_sendTransaction";
//		 Object[] pObjects = new Object[] {param}; 
//         JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://127.0.0.1:7545"));
//         Object address = client.invoke(methodName, pObjects, Object.class);
//
//         System.out.println(address.toString());
//
		 		JSONObject jsonObject = new JSONObject();
//		 param.put("data", "0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb970870f072445675058bb8eb970870f072445675");
		 		Object[] pObjects = new Object[] {"0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb970870f072445675058bb8eb970870f072445675"};
		jsonObject.put("jsonrpc", 2.0);
		jsonObject.put("method", "eth_sendRawTransaction");
		jsonObject.put("params", pObjects);
		jsonObject.put("id", 1);
//		
		 BigInteger GAS_PRICE = BigInteger.valueOf(10000000000000L);
	        BigInteger GAS_LIMIT = BigInteger.valueOf(30400);

	        //调用的是kovan测试环境，这里使用的是infura这个客户端
	    	Web3j web3j = new JsonRpc2_0Web3j(new HttpService("http://localhost:7545"));
	        //转账人账户地址
	        String ownAddress = "0xb6191034Eb4B2c36f97235ae5A41e35Fee4075C5";
	        //被转人账户地址
	        String toAddress = "0x42294ca3F2836AaF761453e9BF1e18427353ff5a";
	        //转账人私钥
	        Credentials credentials = Credentials.create("a33dd1e4a9dfd7b880c90da1880c4111589172b3f7c1801cdd263917f7b2f33d");
		
		String from = Transfer.sendFunds(web3j, credentials, toAddress, BigDecimal.valueOf(2L), Convert.Unit.ETHER).send().getFrom();
		System.out.println(from);
		String doPost = HttpClient.doPost("HTTP://127.0.0.1:7545", jsonObject.toString());
	}
}
