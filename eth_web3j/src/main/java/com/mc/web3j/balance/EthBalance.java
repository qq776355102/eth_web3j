package com.mc.web3j.balance;

import java.io.IOException;
import java.math.BigDecimal;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;


/**
 * @author mc qq_776355102
 * @since JDK1.8
 * @date 2018年8月27日
 * @version 1.0
 */
public class EthBalance {

	public static String getEthBalance(String address) throws IOException {
		//eth余额
		//此处使用的是infura主网
		Web3j web3j = new JsonRpc2_0Web3j(new HttpService("https://mainnet.infura.io/42Qwu27jRgopw62EVBGn"));
//		String address = "0xb6191034Eb4B2c36f97235ae5A41e35Fee4075C5";
		EthGetBalance ethGetBalance;
			ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
			if (ethGetBalance != null) {
				// 打印账户余额
				System.out.println(ethGetBalance.getBalance());
				// 将单位转为以太,方面查看
				BigDecimal bal = Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER);
				return bal.toPlainString();
//				System.out.println(Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER));
			}
		return address;
	}
}
