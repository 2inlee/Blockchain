package inlee.kr.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleBlockchain implements Blockchain<SimpleBlock> {
    private List<SimpleBlock> blockchain;
    private int hashRounds;

    public SimpleBlockchain(int hashRounds) {
        this.blockchain = new ArrayList<>();
        this.hashRounds = hashRounds;
        initializeChain();
    }

    @Override
    public void addBlock(SimpleBlock block) {
        SimpleBlock latestBlock = blockchain.get(blockchain.size() - 1);
        block.setPreviousHash(latestBlock.getHash());
        block.setHash(block.calculateHash(hashRounds));
        blockchain.add(block);
    }

    @Override
    public boolean isBlockchainValid() {
        for (int i = 1; i < blockchain.size(); i++) {
            SimpleBlock currentBlock = blockchain.get(i);
            SimpleBlock previousBlock = blockchain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash(hashRounds))) {
                return false;
            }
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<SimpleBlock> getBlockchain() {
        return blockchain;
    }

    @Override
    public void initializeChain() {
        Map<String, Object> genesisData = new HashMap<>();
        genesisData.put("message", "Genesis Block");
        this.blockchain.add(new SimpleBlock("0", genesisData));
    }
}