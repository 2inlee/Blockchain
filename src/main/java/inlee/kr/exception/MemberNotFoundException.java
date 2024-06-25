package inlee.kr.exception;

public class MemberNotFoundException extends BlockchainException {
    public MemberNotFoundException(Long memberId) {
        super("Member not found with ID: " + memberId);
    }
}