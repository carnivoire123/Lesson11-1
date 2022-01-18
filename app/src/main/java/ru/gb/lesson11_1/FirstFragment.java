package ru.gb.lesson11_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FirstFragment  extends Fragment implements View.OnClickListener {

    private Button startSecond;

    public interface FirstFragmentController {
        public void startSecond();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        startSecond = view.findViewById(R.id.first_start_second);
        startSecond.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ((FirstFragmentController) requireActivity()).startSecond();
    }
}
