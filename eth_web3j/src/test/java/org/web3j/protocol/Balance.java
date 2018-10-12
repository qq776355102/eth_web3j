package org.web3j.protocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import mc.web3j.abi.FunctionEncoder;
import mc.web3j.abi.TypeReference;
import mc.web3j.abi.datatypes.Address;
import mc.web3j.abi.datatypes.Function;


/**
 * @author q_776355102
 *
 *	查询eth与eth代币余额
 */
public class Balance{

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		
		//ETH余额
		Web3j web3j = new JsonRpc2_0Web3j(new HttpService("http://localhost:7545"));
		String address = "0xb6191034Eb4B2c36f97235ae5A41e35Fee4075C5";
		EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
		if (ethGetBalance != null) {
			// 打印账户余额
			System.out.println(ethGetBalance.getBalance());
			// 将单位转为以太,方面查看
			System.out.println(Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER));
		}
		
		//代币余额
		List<TypeReference<Address>> outputParametersList = new ArrayList<TypeReference<Address>>();
		outputParametersList.add(new TypeReference<Address>(){});
		
		@SuppressWarnings("rawtypes")
		Function function = new Function(
				"getBalance", //合约方法名
				Arrays.asList(new Address("0xb6191034Eb4B2c36f97235ae5A41e35Fee4075C5")),//合约方法参数
				outputParametersList //返回参数设置
				);
		//data 交易串
        String encodedFunction = FunctionEncoder.encode(function); //转换成16进制data
		//16进制token余额
        String value = web3j.ethCall(Transaction.createEthCallTransaction("0xb6191034Eb4B2c36f97235ae5A41e35Fee4075C5","0xa26B62197D018D6aB9354E435423430e43D88375", encodedFunction),DefaultBlockParameterName.PENDING).send().getValue();
		//19进制余额
        String tokenBalance = Numeric.toBigInt(Numeric.cleanHexPrefix(value)).toString(10);
		System.out.println(tokenBalance);
	}
}
