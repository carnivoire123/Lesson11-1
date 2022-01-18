package ru.gb.lesson11_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment implements View.OnClickListener {

    private Button endSecond;


    public interface SecondFragmentController {
        public void endSecond();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        endSecond = view.findViewById(R.id.second_end_fragment);
        endSecond.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ((SecondFragmentController ) requireActivity()).endSecond();
    }
}
