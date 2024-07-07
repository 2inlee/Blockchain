package inlee.kr.model;

import java.time.LocalDateTime;
import java.util.Map;

public interface Block {
    String calculateHash(int rounds);
    String getPreviousHash();
    void setPreviousHash(String previousHash);
    String getHash();
    void setHash(String hash);
    LocalDateTime getTimeStamp();
    void setTimeStamp(LocalDateTime timeStamp);
    Map<String, Object> getData();
    void setData(Map<String, Object> data);
}