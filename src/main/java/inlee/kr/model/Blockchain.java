package inlee.kr.model;

import java.util.List;

public interface Blockchain<T extends Block> {
    void addBlock(T block);
    boolean isBlockchainValid();
    List<T> getBlockchain();
    void initializeChain();
}