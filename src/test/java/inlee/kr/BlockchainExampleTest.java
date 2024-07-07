package inlee.kr;

import inlee.kr.model.Blockchain;
import inlee.kr.model.SimpleBlock;
import inlee.kr.model.SimpleBlockchain;
import inlee.kr.util.BlockchainUtils;

import java.util.HashMap;
import java.util.Map;

public class BlockchainExampleTest {
    public static void main(String[] args) {
        // 블록체인 생성 및 초기화
        int hashRounds = 5; // 해시 라운드 수
        SimpleBlockchain blockchain = new SimpleBlockchain(hashRounds);

        // 데이터 추가 및 블록 생성
        Map<String, Object> data1 = new HashMap<>();
        data1.put("amount", 100);
        BlockchainUtils.addDataToBlockchain(blockchain, data1, hashRounds);

        Map<String, Object> data2 = new HashMap<>();
        data2.put("amount", 200);
        BlockchainUtils.addDataToBlockchain(blockchain, data2, hashRounds);

        // 블록체인 출력
        System.out.println("Blockchain:");
        BlockchainUtils.printBlockchain(blockchain);

        // 블록체인 유효성 검사
        boolean isValid = BlockchainUtils.validateBlockchain(blockchain, hashRounds);
        System.out.println("Is blockchain valid? " + isValid);
    }
}