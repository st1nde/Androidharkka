package com.example.androidharkka.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidharkka.GameActivity;
import com.example.androidharkka.R;
import com.example.androidharkka.dataActivity;
import com.example.androidharkka.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private static final String TAG = "MainActivity";
    private Button startButton;
    private Button gameButton;
    private View helloTextView;
    private Button search;
    private EditText searchTxt;
    private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        helloTextView = root.findViewById(R.id.hello);
        helloTextView.setVisibility(View.INVISIBLE);

        searchTxt = root.findViewById(R.id.searchText);


        startButton = root.findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleOnClickEvents(v);

            }
        });
        gameButton = root.findViewById(R.id.gameButton);
        gameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleOnClickEvents(v);
            }
        });

        search = root.findViewById(R.id.searchButton);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOnClickEvents(v);
            }
        });
        return root;
    }

    private void handleOnClickEvents(View v) {
        if (v.getId() == R.id.startButton) {
            Log.d(TAG, "User clicked the Start-button");
            if(helloTextView.getVisibility() == View.INVISIBLE){
                helloTextView.setVisibility(View.VISIBLE);
            } else if (helloTextView.getVisibility() == View.VISIBLE) {
                helloTextView.setVisibility(View.INVISIBLE);
            }

        } else if (v.getId() == R.id.gameButton) {
            Intent i = new Intent(getActivity(), GameActivity.class);
            startActivity(i);
            Log.d(TAG, "User clicked the Game-button");
        }
        else if (v.getId() == R.id.searchButton) {
            Log.d(TAG, "User clicked the search-button");
            Intent i = new Intent(getActivity(), dataActivity.class);
            i.putExtra("searchValue", searchTxt.getText().toString());
            startActivity(i);
        }

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}