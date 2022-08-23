package org.techtown.lottoworld;

public class WinningNumber {
    int round;
    String date;

    int[] winningNums = new int[7];

    public int[] getWinningNums() {
        return winningNums;
    }

    public void setWinningNums(int[] winningNums) {
        this.winningNums = winningNums;
    }

    int total;
    int odd;
    int even;

    public WinningNumber() {
        this.round = 0;
        this.total = 0;
        this.odd = 0;
        this.even = 0;
    }

    public WinningNumber(int round, String date, int[] winningNums) {
        this.round = round;
        this.date = date;
        this.winningNums = winningNums;
        setOddnEven();
        setTotal();
    }

    public int getTotal() {
        return total;
    }

    public void setTotal() {
        for(int num: winningNums){
            total += num;
        }
    }

    public void setOddnEven() {
        for(int num : winningNums){
            if(num % 2 == 0){
                even += 1;
            }
        }
        odd = 7 - even;
    }

    public int getEven() {
        return even;
    }

    public int getOdd() {
        return odd;
    }


    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }




    public String numberString(){

        StringBuilder sb = new StringBuilder(); // well estimated buffer
        for (int num : winningNums) {
            if (sb.length() > 0)
                sb.append(" ");
            sb.append(num);
        }
        return sb.toString();
    }

}
