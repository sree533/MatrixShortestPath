package com.sreekanth.backend;

public class PathCollector {

    private Path bestPath;
    private PathComparator comparator;

    public PathCollector() {
        comparator = new PathComparator();
    }

    public Path getBestPath() {
        return bestPath;
    }

    public void addPath(Path newPath) {
        if (comparator.compare(newPath, bestPath) < 0) {
            bestPath = newPath;
        }
    }
}
