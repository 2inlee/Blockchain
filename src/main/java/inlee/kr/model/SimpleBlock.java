package inlee.kr.model;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;

public class SimpleBlock implements Block {
    private String previousHash;
    private String hash;
    private LocalDateTime timeStamp;
    private Map<String, Object> data;

    public SimpleBlock(String previousHash, Map<String, Object> data) {
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = LocalDateTime.now();
        this.hash = calculateHash();
    }

    @Override
    public String calculateHash() {
        String dataToHash = previousHash + data.toString() + timeStamp.toString();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
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

    @Override
    public String getPreviousHash() {
        return previousHash;
    }

    @Override
    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    @Override
    public String getHash() {
        return hash;
    }

    @Override
    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    @Override
    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public Map<String, Object> getData() {
        return data;
    }

    @Override
    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
