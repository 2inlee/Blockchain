package inlee.kr.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class TransactionBlock extends SimpleBlock {
    private List<Transaction> transactions;

    public TransactionBlock(String previousHash, List<Transaction> transactions) {
        super(previousHash, null);
        this.transactions = transactions;
        this.setHash(calculateHash(1));
    }

    @Override
    public String calculateHash(int rounds) {
        StringBuilder dataToHash = new StringBuilder();
        dataToHash.append(getPreviousHash()).append(getTimeStamp().toString());
        for (Transaction tx : transactions) {
            dataToHash.append(tx.getSender()).append(tx.getReceiver()).append(tx.getAmount());
        }
        return hash(dataToHash.toString(), rounds);
    }

    private String hash(String data, int rounds) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = data.getBytes(StandardCharsets.UTF_8);
            for (int i = 0; i < rounds; i++) {
                hashBytes = digest.digest(hashBytes);
            }
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}