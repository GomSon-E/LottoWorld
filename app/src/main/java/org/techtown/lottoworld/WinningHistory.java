package org.techtown.lottoworld;

public class WinningHistory {
    WinningNumber winningNumber;
    int rank;

    public WinningHistory(WinningNumber winningNumber, int rank) {
        this.winningNumber = winningNumber;
        this.rank = rank;
    }

    public WinningNumber getWinningNumber() {
        return winningNumber;
    }

    public void setWinningNumber(WinningNumber winningNumber) {
        this.winningNumber = winningNumber;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
