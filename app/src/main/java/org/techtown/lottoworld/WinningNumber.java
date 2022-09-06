package org.techtown.lottoworld;

public class WinningNumber {
    int round;//회차
    String date;// 당첨일

    int[] winningNums = new int[7]; // 보너스 넘버까지 7개
    int total;
    int even; // 짝수만 지정하고 홀수는 6 - even 으로 코딩

    public WinningNumber() {
        round = 0;
        total = 0;
        even = 0;
    }

    public WinningNumber(int round, String date, int[] winningNums) {
        this.round = round;
        this.date = date;
        this.winningNums = winningNums;
    }

    public int[] getWinningNums() {
        return winningNums;
    }

    public void setWinningNums(int[] winningNums) {
        this.winningNums = winningNums;
    }


    public int getTotal() {
        total = 0;
        for(int i = 0; i < 6; i++){
            total += winningNums[i];
        }
        return total;
    }

    public int getEven() {
        even = 0;
        for(int i = 0; i < 6; i++){
            if(winningNums[i] % 2 ==0){
                even ++;
            }
        }
        return even;
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




    public String numberString(){ // 당첨번호 보너스를 제외한 6개를 붙인 문자열

        StringBuilder sb = new StringBuilder(); // well estimated buffer

        for (int num : winningNums) {
            if (sb.length() > 0)
                sb.append(" ");
            if(num != winningNums[6]){
                sb.append(num);
            }
        }
        return sb.toString();
    }

}
