package org.web3j.protocol;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.math.BigInteger;


/**
 * @author q_776355102
 *
 *	 发送eth原生交易
 */
public class SendRawTransactionForEth {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //设置需要的矿工费
        BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
        BigInteger GAS_LIMIT = BigInteger.valueOf(30400);

        //调用的是kovan测试环境，这里使用的是infura这个客户端
    	Web3j web3j = new JsonRpc2_0Web3j(new HttpService("http://localhost:7545"));
        //转账人账户地址
        String ownAddress = "0xb6191034Eb4B2c36f97235ae5A41e35Fee4075C5";
        //被转人账户地址
        String toAddress = "0x42294ca3F2836AaF761453e9BF1e18427353ff5a";
        //转账人私钥
        Credentials credentials = Credentials.create("0c09d90eb9f77bf59e9093aa2506a5ebc1b13304250851a4cb383f67b0105e28");
        //加载keystore文件
        //        Credentials credentials = WalletUtils.loadCredentials(
        //                "123",
        //                "/datadir/chain/keystore/UTC--2018-03-01T05-53-37.043Z--d1c82c71cc567d63fd53d5b91dcac6156e5b96b3");

        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                ownAddress, DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();

        //创建交易，这里是转20个以太币
        BigInteger value = Convert.toWei("20", Convert.Unit.ETHER).toBigInteger();
        RawTransaction rawTransaction = RawTransaction.createEtherTransaction(
                nonce, GAS_PRICE, GAS_LIMIT, toAddress, value);

        //签名Transaction，这里要对交易做签名
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        System.out.println(hexValue);

        //发送交易
        EthSendTransaction ethSendTransaction =
                web3j.ethSendRawTransaction(hexValue).sendAsync().get();
        String transactionHash = ethSendTransaction.getTransactionHash();

        //获得到transactionHash后就可以到以太坊的网站上查询这笔交易的状态了
        System.out.println(transactionHash);
    }

}