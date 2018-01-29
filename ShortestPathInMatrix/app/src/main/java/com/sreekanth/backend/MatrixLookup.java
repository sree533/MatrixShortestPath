package com.sreekanth.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatrixLookup {

    private Matrix matrix;
    private PathComparator pathComparator;

    public MatrixLookup(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("A visitor requires a matrix");
        }

        this.matrix = matrix;
        pathComparator = new PathComparator();
    }

    public Path getBestPathForGrid() {
        List<Path> allPaths = new ArrayList<Path>();
        for (int row = 1; row <= matrix.getRowCount(); row++) {
            RowLookup visitor = new RowLookup(row, matrix, new PathCollector());
            allPaths.add(visitor.getBestPathForRow());
        }

        Collections.sort(allPaths, pathComparator);

        return allPaths.get(0);
    }

}
