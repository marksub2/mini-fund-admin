import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

class Trade {
    private String name;
    private int number;
    private BigDecimal price;

    public Trade(String name, int number, BigDecimal price) {
        if (number <= 0){
            throw new IllegalArgumentException("수량은 0보다 커야 합니다: " + number);
        }
        this.name = name;
        this.number = number;
        this.price = price;
    }

    public BigDecimal getAmount() {
        BigDecimal qty = BigDecimal.valueOf(number);
        return qty.multiply(price);
    }
}
class Fund {
    private String name;
    private List<Trade> trades;

    
    public Fund(String name){
        this.name = name;
        this.trades = new ArrayList<>();

    }


    public void addTrade(Trade trade){
        //거래를 추가하는 기능
        trades.add(trade);

    }
    public BigDecimal getTotalAmount(){
        //모든 거래 금액의 합을 돌려주는 기능
        BigDecimal total = BigDecimal.ZERO;
        for (Trade trade : trades ) {
            total = total.add(trade.getAmount());
        }
        return total;
    }

}
public class Main {
    public static void main(String[] args) {



        Fund fund = new Fund("첫번째 펀드");
        fund.addTrade(new Trade("삼성전자", 3, new BigDecimal("0.1")));
        try{
            fund.addTrade(new Trade("현대자동차", 0, new BigDecimal("5")));
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        fund.addTrade(new Trade("LG전자", 5, new BigDecimal("0.5")));
        fund.addTrade(new Trade("SK하이닉스", 10, new BigDecimal("5.5")));

        System.out.println(fund.getTotalAmount());

    }
}