package inlee.kr.util;

import inlee.kr.model.Block;
import inlee.kr.model.Blockchain;
import inlee.kr.model.SimpleBlock;

import java.util.Map;

public class BlockchainUtils {

    public static <T extends Block> boolean validateBlockchain(Blockchain<T> blockchain, int hashRounds) {
        for (int i = 1; i < blockchain.getBlockchain().size(); i++) {
            T currentBlock = blockchain.getBlockchain().get(i);
            T previousBlock = blockchain.getBlockchain().get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash(hashRounds))) {
                return false;
            }
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }

    public static <T extends Block> void printBlockchain(Blockchain<T> blockchain) {
        for (T block : blockchain.getBlockchain()) {
            System.out.println(block);
        }
    }

    public static <T extends Block> void addDataToBlockchain(Blockchain<T> blockchain, Map<String, Object> data, int hashRounds) {
        T latestBlock = blockchain.getBlockchain().get(blockchain.getBlockchain().size() - 1);
        T newBlock = (T) new SimpleBlock(latestBlock.getHash(), data);
        ((SimpleBlock) newBlock).setHash(((SimpleBlock) newBlock).calculateHash(hashRounds));
        blockchain.addBlock(newBlock);
    }
}