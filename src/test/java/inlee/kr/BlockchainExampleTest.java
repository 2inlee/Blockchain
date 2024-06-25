package inlee.kr;

import inlee.kr.model.SimpleBlock;
import inlee.kr.model.SimpleBlockchain;
import inlee.kr.util.BlockchainUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class BlockchainExampleTest {

    @Test
    public void testBlockchain() {
        SimpleBlockchain blockchain = new SimpleBlockchain();

        Map<String, Object> data1 = new HashMap<>();
        data1.put("amount", 100);
        data1.put("sender", "Alice");
        data1.put("receiver", "Bob");

        Map<String, Object> data2 = new HashMap<>();
        data2.put("amount", 50);
        data2.put("sender", "Bob");
        data2.put("receiver", "Charlie");

        BlockchainUtils.addDataToBlockchain(blockchain, data1);
        BlockchainUtils.addDataToBlockchain(blockchain, data2);

        assertTrue(BlockchainUtils.validateBlockchain(blockchain));
    }
}