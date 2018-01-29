package com.sreekanth.backend;

import java.util.Comparator;

public class PathComparator implements Comparator<Path> {

    private static int SORT_LEFT_FIRST = -1;
    private static int SORT_RIGHT_FIRST = 1;
    private static int SORT_EQUAL = 0;

    @Override
    public int compare(Path leftPath, Path rightPath) {
        int comparedLength = compareLengths(leftPath, rightPath);
        return (comparedLength != 0) ? comparedLength : compareCosts(leftPath, rightPath);
    }

    private int compareLengths(Path leftPath, Path rightPath) {
        int leftLength = getLengthFromPath(leftPath);
        int rightLength = getLengthFromPath(rightPath);

        return (leftLength > rightLength) ? SORT_LEFT_FIRST : (leftLength == rightLength) ? SORT_EQUAL : SORT_RIGHT_FIRST;
    }

    private int compareCosts(Path leftPath, Path rightPath) {
        int leftCost = getCostFromPath(leftPath);
        int rightCost = getCostFromPath(rightPath);

        return (leftCost < rightCost) ? SORT_LEFT_FIRST : (leftCost == rightCost) ? SORT_EQUAL : SORT_RIGHT_FIRST;
    }

    private int getLengthFromPath(Path path) {
        if (path != null) {
            return path.getPathLength();
        } else {
            return Integer.MIN_VALUE;
        }
    }

    private int getCostFromPath(Path path) {
        if (path != null) {
            return path.getTotalCost();
        } else {
            return Integer.MAX_VALUE;
        }
    }
}
