package com.sreekanth.backend;

import java.util.List;

public class RowLookup {

    private int row;
    private Matrix matrix;
    private PathCollector pathCollector;

    public RowLookup(int startRow, Matrix matrix, PathCollector collector) {
        if (matrix == null) {
            throw new IllegalArgumentException("A visitor requires a matrix");
        } else if (collector == null) {
            throw new IllegalArgumentException("A visitor requires a collector");
        } else if (startRow <= 0 || startRow > matrix.getRowCount()) {
            throw new IllegalArgumentException("Cannot visit a row outside of matrix boundaries");
        }

        this.row = startRow;
        this.matrix = matrix;
        this.pathCollector = collector;
    }

    public Path getBestPathForRow() {
        Path initialPath = new Path(matrix.getColumnCount());

        visitPathsForRow(row, initialPath);

        return pathCollector.getBestPath();
    }

    private void visitPathsForRow(int row, Path path) {
        if (canVisitRowOnPath(row, path)) {
            visitRowOnPath(row, path);
        }

        List<Integer> adjacentRows = matrix.getRowsAdjacentTo(row);
        boolean currentPathAdded = false;

        for (int adjacentRow : adjacentRows) {
            if (canVisitRowOnPath(adjacentRow, path)) {
                Path pathCopy = new Path(path);
                visitPathsForRow(adjacentRow, pathCopy);
            } else if (!currentPathAdded) {
                pathCollector.addPath(path);
                currentPathAdded = true;
            }
        }
    }

    private boolean canVisitRowOnPath(int row, Path path) {
        return !path.isComplete() && !nextVisitOnPathWouldExceedMaximumCost(row, path);
    }

    private void visitRowOnPath(int row, Path path) {
        int columnToVisit = path.getPathLength() + 1;
        path.addRowWithCost(row, matrix.getValueForRowAndColumn(row, columnToVisit));
    }

    private boolean nextVisitOnPathWouldExceedMaximumCost(int row, Path path) {
        int nextColumn = path.getPathLength() + 1;
        return (path.getTotalCost() + matrix.getValueForRowAndColumn(row, nextColumn)) > Path.MAXIMUM_COST;
    }
}
