package inlee.kr.model;

import java.security.*;
import java.util.Base64;

public class Transaction {
    private String sender;
    private String receiver;
    private double amount;
    private String signature;

    public Transaction(String sender, String receiver, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }

    public String getSignature() {
        return signature;
    }

    public void signTransaction(PrivateKey privateKey) {
        String data = sender + receiver + amount;
        signature = sign(data, privateKey);
    }

    public boolean verifyTransaction(PublicKey publicKey) {
        String data = sender + receiver + amount;
        return verify(data, signature, publicKey);
    }

    private String sign(String data, PrivateKey privateKey) {
        try {
            Signature rsa = Signature.getInstance("SHA256withRSA");
            rsa.initSign(privateKey);
            rsa.update(data.getBytes());
            byte[] signatureBytes = rsa.sign();
            return Base64.getEncoder().encodeToString(signatureBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean verify(String data, String signature, PublicKey publicKey) {
        try {
            Signature rsa = Signature.getInstance("SHA256withRSA");
            rsa.initVerify(publicKey);
            rsa.update(data.getBytes());
            byte[] signatureBytes = Base64.getDecoder().decode(signature);
            return rsa.verify(signatureBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}