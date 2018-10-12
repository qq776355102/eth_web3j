package org.web3j.protocol;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

/**
 * @author q_776355102
 * 
 * 创建发布合约
 *
 */
public class CreateContracTest {

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		// 设置需要的矿工费
		BigInteger GAS_PRICE = BigInteger.valueOf(22000000000L);
		BigInteger GAS_LIMIT = BigInteger.valueOf(4300000);
		// 调用的是kovan测试环境，这里使用的是infura这个客户端
		Web3j web3j = new JsonRpc2_0Web3j(new HttpService("http://localhost:7545"));
		Credentials credentials = Credentials
				.create("0c09d90eb9f77bf59e9093aa2506a5ebc1b13304250851a4cb383f67b0105e28");
		String ownAddress = "0xb6191034Eb4B2c36f97235ae5A41e35Fee4075C5";
		EthGetTransactionCount ethGetTransactionCount = web3j
				.ethGetTransactionCount(ownAddress, DefaultBlockParameterName.LATEST).sendAsync().get();
		BigInteger nonce = ethGetTransactionCount.getTransactionCount();
		
		System.out.println(nonce);
		
		

		String data = "0x6060604052341561000f57600080fd5b620f42406000803273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506102c5806100646000396000f30060606040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806390b98a1114610051578063f8b2cb4f146100ab575b600080fd5b341561005c57600080fd5b610091600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919080359060200190919050506100f8565b604051808215151515815260200191505060405180910390f35b34156100b657600080fd5b6100e2600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610251565b6040518082815260200191505060405180910390f35b6000816000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020541015610149576000905061024b565b816000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282540392505081905550816000808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055508273ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef846040518082815260200191505060405180910390a3600190505b92915050565b60008060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205490509190505600a165627a7a723058209dbc6a8eb7c92e51929bce05753530c9d909d9a6262f891ab9d4d2ebea4163930029";
			//BigInteger nonce, 
			//BigInteger gasPrice, 
			//BigInteger gasLimit, 
			//String to, 
			//String data
		BigInteger bigInteger = new BigInteger("0");

		String to = "0xb6191034Eb4B2c36f97235ae5A41e35Fee4075C5";
		RawTransaction rawTransaction = RawTransaction.createContractTransaction(nonce, GAS_PRICE, GAS_LIMIT,bigInteger, data);
		
		// 签名Transaction，这里要对交易做签名
		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
		String hexValue = Numeric.toHexString(signedMessage);

		// 发送交易
		EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
		String transactionHash = ethSendTransaction.getTransactionHash();

		
		
		
		// 获得到transactionHash后就可以到以太坊的网站上查询这笔交易的状态了
		System.out.println(transactionHash);
		

	}
}
