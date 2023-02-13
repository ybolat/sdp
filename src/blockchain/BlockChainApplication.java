package blockchain;

import java.util.Arrays;
import java.util.List;

public class BlockChainApplication {
    public static void main(String[] args) {
        Transaction transaction = new Transaction("FlowerStore", "Alimzhan", 1005L);
        Transaction secondTransaction = new Transaction("FlowerStore", "Erabeast", 1007L);
        Transaction thirdTransaction = new Transaction("FlowerStore", "Abdualitos", 1500L);
        System.out.println(transaction.hashCode());
        System.out.println(secondTransaction.hashCode());
        System.out.println(thirdTransaction.hashCode());
        Block firstBlock = new Block(0, Arrays.asList(transaction, secondTransaction));
        System.out.println(firstBlock.hashCode());
        Block secondBlock = new Block(firstBlock.hashCode(), List.of(thirdTransaction));
        System.out.println(secondBlock.hashCode());

//        -2079111845
//                -957340390
    }
}
