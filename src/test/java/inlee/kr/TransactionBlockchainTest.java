package inlee.kr;

import inlee.kr.model.Transaction;
import inlee.kr.model.TransactionBlock;
import inlee.kr.model.SimpleBlockchain;
import inlee.kr.util.BlockchainUtils;

import java.security.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionBlockchainTest {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        int hashRounds = 5; // 해시 라운드 수
        SimpleBlockchain blockchain = new SimpleBlockchain(hashRounds);

        // 키 생성
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        // 트랜잭션 생성 및 서명
        Transaction tx1 = new Transaction("Alice", "Bob", 50);
        tx1.signTransaction(privateKey);

        // 트랜잭션 블록 생성
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(tx1);
        TransactionBlock block = new TransactionBlock(blockchain.getBlockchain().get(blockchain.getBlockchain().size() - 1).getHash(), transactions);
        blockchain.addBlock(block);

        // 블록체인 출력
        System.out.println("Blockchain:");
        BlockchainUtils.printBlockchain(blockchain);

        // 블록체인 유효성 검사
        boolean isValid = BlockchainUtils.validateBlockchain(blockchain, hashRounds);
        System.out.println("Is blockchain valid? " + isValid);
    }
}