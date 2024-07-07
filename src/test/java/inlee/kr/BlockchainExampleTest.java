package inlee.kr;

import inlee.kr.model.SimpleBlock;
import inlee.kr.model.SimpleBlockchain;
import inlee.kr.util.BlockchainUtils;

import java.util.HashMap;
import java.util.Map;

public class BlockchainExampleTest {
    public static void main(String[] args) {
        int hashRounds = 5; // 해시 라운드 수
        int difficulty = 3; // 작업 증명 난이도
        SimpleBlockchain blockchain = new SimpleBlockchain(hashRounds, difficulty);

        // 데이터 추가 및 블록 생성
        Map<String, Object> data1 = new HashMap<>();
        data1.put("amount", 100);
        SimpleBlock newBlock1 = new SimpleBlock(blockchain.getBlockchain().get(blockchain.getBlockchain().size() - 1).getHash(), data1);
        blockchain.addBlock(newBlock1);

        Map<String, Object> data2 = new HashMap<>();
        data2.put("amount", 200);
        SimpleBlock newBlock2 = new SimpleBlock(blockchain.getBlockchain().get(blockchain.getBlockchain().size() - 1).getHash(), data2);
        blockchain.addBlock(newBlock2);

        // 블록체인 출력
        System.out.println("Blockchain:");
        BlockchainUtils.printBlockchain(blockchain);

        // 블록체인 유효성 검사
        boolean isValid = blockchain.isBlockchainValid();
        System.out.println(isValid);
    }
}