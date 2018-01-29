package com.sreekanth.backend;

import java.util.ArrayList;
import java.util.List;

public class Path {

    public static int MAXIMUM_COST = 50;

    private List<Integer> rowsTraversed = new ArrayList<Integer>();
    private int totalCost = 0;
    private int expectedLength = 0;

    Path(int expectedLength) {
        this.expectedLength = expectedLength;
    }

    Path(Path anotherPath) {
        this.totalCost = anotherPath.totalCost;
        this.expectedLength = anotherPath.expectedLength;
        for (int rowTraversed : anotherPath.rowsTraversed) {
            this.rowsTraversed.add(rowTraversed);
        }
    }

    public List<Integer> getRowsTraversed() {
        return rowsTraversed;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int getPathLength() {
        return rowsTraversed.size();
    }

    public void addRowWithCost(int row, int cost) {
        rowsTraversed.add(row);
        totalCost += cost;
    }

    public boolean isComplete() {
        return rowsTraversed.size() == expectedLength;
    }

    public boolean isSuccessful() {
        return isComplete() && !isOverCost();
    }

    public boolean isOverCost() {
        return totalCost > MAXIMUM_COST;
    }
}
