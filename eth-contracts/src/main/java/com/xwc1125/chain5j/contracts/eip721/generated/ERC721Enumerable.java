package com.xwc1125.chain5j.contracts.eip721.generated;

import java.math.BigInteger;
import java.util.Arrays;

import com.xwc1125.chain5j.abi.datatypes.Address;
import com.xwc1125.chain5j.abi.TypeReference;
import com.xwc1125.chain5j.abi.datatypes.Function;
import com.xwc1125.chain5j.abi.datatypes.Type;
import com.xwc1125.chain5j.abi.datatypes.generated.Uint256;
import com.xwc1125.chain5j.crypto.Credentials;
import com.xwc1125.chain5j.protocol.Web3j;
import com.xwc1125.chain5j.protocol.core.RemoteCall;
import com.xwc1125.chain5j.tx.Contract;
import com.xwc1125.chain5j.tx.TransactionManager;
import com.xwc1125.chain5j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the com.xwc1125.chain5j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.1.1.
 */
public class ERC721Enumerable extends Contract {
    private static final String BINARY = "Bin file was not provided";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TOKENOFOWNERBYINDEX = "tokenOfOwnerByIndex";

    public static final String FUNC_TOKENBYINDEX = "tokenByIndex";

    @Deprecated
    protected ERC721Enumerable(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ERC721Enumerable(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ERC721Enumerable(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ERC721Enumerable(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> tokenOfOwnerByIndex(String _owner, BigInteger _index) {
        final Function function = new Function(FUNC_TOKENOFOWNERBYINDEX, 
                Arrays.<Type>asList(new Address(_owner),
                new Uint256(_index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> tokenByIndex(BigInteger _index) {
        final Function function = new Function(FUNC_TOKENBYINDEX, 
                Arrays.<Type>asList(new Uint256(_index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static ERC721Enumerable load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC721Enumerable(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ERC721Enumerable load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC721Enumerable(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ERC721Enumerable load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ERC721Enumerable(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ERC721Enumerable load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ERC721Enumerable(contractAddress, web3j, transactionManager, contractGasProvider);
    }
}
