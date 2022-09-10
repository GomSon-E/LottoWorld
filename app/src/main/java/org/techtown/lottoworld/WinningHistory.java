package org.techtown.lottoworld;

public class WinningHistory implements Comparable<WinningHistory>{
    WinningNumber winningNumber;
    int rank;

    public WinningHistory(WinningNumber winningNumber, int rank) {
        this.winningNumber = winningNumber;
        this.rank = rank;
    }

    @Override
    public int compareTo(WinningHistory winningHistory) {
        if (winningHistory.rank < rank) {
            return 1;
        }
        else if (winningHistory.rank > rank) {
            return -1;
        }
        return 0;
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
