package com.sreekanth.lowestcost;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sreekanth.backend.Matrix;
import com.sreekanth.backend.MatrixLookup;
import com.sreekanth.backend.Path;

import java.util.List;

public class LowestCostExamplesFragment extends Fragment {

    private Matrix loadedMatrix;

    public LowestCostExamplesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_lowest_cost_examples_main, container, false);

        Button gridOneButton = (Button) fragmentView.findViewById(R.id.grid_1_button);
        gridOneButton.setOnClickListener(new GridOnClickListener());
        Button gridTwoButton = (Button) fragmentView.findViewById(R.id.grid_2_button);
        gridTwoButton.setOnClickListener(new GridOnClickListener());
        Button gridThreeButton = (Button) fragmentView.findViewById(R.id.grid_3_button);
        gridThreeButton.setOnClickListener(new GridOnClickListener());
        Button goButton = (Button) fragmentView.findViewById(R.id.go_button);
        goButton.setOnClickListener(new GoOnClickListener());

        return fragmentView;
    }

    private Matrix getGridForButton(View view) {
        switch (view.getId()) {
            case R.id.grid_1_button:
                return MatrixUtils.EXAMPLE_MATRIX_1;
            case R.id.grid_2_button:
                return MatrixUtils.EXAMPLE_MATRIX_2;
            case R.id.grid_3_button:
                return MatrixUtils.EXAMPLE_MATRIX_3;
            default:
                return null;
        }
    }

    private void clearResults() {
        ((TextView) getView().findViewById(R.id.results_success)).setText("");
        ((TextView) getView().findViewById(R.id.results_total_cost)).setText(getResources().getText(R.string.no_results));
        ((TextView) getView().findViewById(R.id.results_path_taken)).setText("");
    }

    private String formatPath(Path path) {
        StringBuilder builder = new StringBuilder();
        List<Integer> rows = path.getRowsTraversed();

        for (int i = 0; i < rows.size(); i++) {
            builder.append(rows.get(i));
            if (i < rows.size() - 1) {
                builder.append("\t");
            }
        }

        return builder.toString();
    }

    class GridOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Matrix selectedMatrix = getGridForButton(view);
            if (!selectedMatrix.equals(loadedMatrix)) {
                clearResults();
            }

            loadedMatrix = selectedMatrix;
            ((TextView) getView().findViewById(R.id.grid_contents)).setText(loadedMatrix.asDelimitedString("\t"));
            getView().findViewById(R.id.go_button).setEnabled(true);
        }
    }

    class GoOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            MatrixLookup visitor = new MatrixLookup(loadedMatrix);
            Path bestPath = visitor.getBestPathForGrid();

            if (bestPath.isSuccessful()) {
                ((TextView) getView().findViewById(R.id.results_success)).setText("Yes");
            } else {
                ((TextView) getView().findViewById(R.id.results_success)).setText("No");
            }
            ((TextView) getView().findViewById(R.id.results_total_cost)).setText(Integer.toString(bestPath.getTotalCost()));
            ((TextView) getView().findViewById(R.id.results_path_taken)).setText(formatPath(bestPath));
        }
    }
}
