// src/main/java/inlee/kr/util/BlockchainUtils.java
package inlee.kr.util;

import inlee.kr.model.Block;
import inlee.kr.model.Blockchain;
import inlee.kr.model.SimpleBlock;

import java.util.Map;

public class BlockchainUtils {

    public static <T extends Block> boolean validateBlockchain(Blockchain<T> blockchain) {
        return blockchain.isBlockchainValid();
    }

    public static <T extends Block> void printBlockchain(Blockchain<T> blockchain) {
        for (T block : blockchain.getBlockchain()) {
            System.out.println(block);
        }
    }

    public static <T extends Block> void addDataToBlockchain(Blockchain<T> blockchain, Map<String, Object> data) {
        T latestBlock = blockchain.getBlockchain().get(blockchain.getBlockchain().size() - 1);
        T newBlock = (T) new SimpleBlock(latestBlock.getHash(), data);
        blockchain.addBlock(newBlock);
    }
}