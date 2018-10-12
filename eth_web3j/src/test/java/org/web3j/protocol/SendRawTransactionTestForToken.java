package org.web3j.protocol;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

import mc.web3j.abi.FunctionEncoder;
import mc.web3j.abi.TypeReference;
import mc.web3j.abi.datatypes.Address;
import mc.web3j.abi.datatypes.Function;
import mc.web3j.abi.datatypes.Type;
import mc.web3j.abi.datatypes.generated.Uint256;

/**
 * @author q_776355102
 *
 * 发送代币原生交易
 */
public class SendRawTransactionTestForToken {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <E> void main(String[] args) throws InterruptedException, ExecutionException {
		// 设置需要的矿工费
		BigInteger gasPrice = BigInteger.valueOf(22000000000L);
		BigInteger gasLimit = BigInteger.valueOf(4300000);
		Web3j web3j = new JsonRpc2_0Web3j(new HttpService("http://localhost:7545"));
		// 发件者地址
		String ownAddress = "0xb6191034Eb4B2c36f97235ae5A41e35Fee4075C5";
		//发件人私钥用于签名交易
		Credentials credentials = Credentials
				.create("0c09d90eb9f77bf59e9093aa2506a5ebc1b13304250851a4cb383f67b0105e28");
		EthGetTransactionCount ethGetTransactionCount = web3j
				.ethGetTransactionCount(ownAddress, DefaultBlockParameterName.LATEST).sendAsync().get();
		//获得交易nonce
		BigInteger nonce = ethGetTransactionCount.getTransactionCount();
		// 合约地址
		String to = "0xa26B62197D018D6aB9354E435423430e43D88375";
		//调用合约的参数
		List<Type<Object>> inputParametersList = new ArrayList<Type<Object>>();
		Type address = new Address("0x42294ca3F2836AaF761453e9BF1e18427353ff5a");
		inputParametersList.add(address);
		Type uint256 = new Uint256(BigInteger.valueOf(100L));
		inputParametersList.add(uint256);
		Function function = new Function("sendCoin", inputParametersList, Arrays.asList(new TypeReference<Type<String>>() {}));
		// 转换成16进制data
		String encodedFunction = FunctionEncoder.encode(function);
		// 获得原生交易
		RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, to,
				encodedFunction);
		// 签名rawTransaction，这里要对交易做签名
		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
		String hexValue = Numeric.toHexString(signedMessage);
		// 发送交易
		EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
		String transactionHash = ethSendTransaction.getTransactionHash();
		// 获得到transactionHash后就可以到以太坊的网站上查询这笔交易的状态了
		System.out.println(transactionHash);
	}

}
