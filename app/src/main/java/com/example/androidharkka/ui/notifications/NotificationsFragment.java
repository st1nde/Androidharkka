package com.example.androidharkka.ui.notifications;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.NumberPicker;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidharkka.R;
import com.example.androidharkka.databinding.FragmentNotificationsBinding;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class NotificationsFragment extends Fragment {

    private TextClock Clock;
    private NumberPicker nPicker;
    private FragmentNotificationsBinding binding;
    MaterialButtonToggleGroup toggleButton;
    private TextView timerEnd;
    private String[] pickerVals;
    private CountDownTimer timer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Ringtone defaultRingtone = RingtoneManager.getRingtone(getActivity(),
                Settings.System.DEFAULT_RINGTONE_URI);

        toggleButton = root.findViewById(R.id.toggleButton);


        timerEnd = root.findViewById(R.id.timerEndText);
        nPicker = binding.nPicker1;
        pickerVals = new String[61];
        nPicker.setMaxValue(pickerVals.length - 1);
        nPicker.setMinValue(0);
        for (int i = 0; i < pickerVals.length; i++) {
            pickerVals[i] = i + " s";
        }

        nPicker.setDisplayedValues(pickerVals);


        nPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

            }
        });

        toggleButton.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {

            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked) {
                    if (checkedId == R.id.start) {
                        startTiming();
                    } else if (checkedId == R.id.pause) {
                        pauseTiming();
                    } else if (checkedId == R.id.stop) {
                        stopTiming();
                    }
                }

            }
        });
        timerEnd.setVisibility(View.INVISIBLE);


        return root;
    }


    private void startTiming() {
        Ringtone defaultRingtone = RingtoneManager.getRingtone(getActivity(),
                Settings.System.DEFAULT_RINGTONE_URI);

        Uri currentRintoneUri = RingtoneManager.getActualDefaultRingtoneUri(getActivity()
                .getApplicationContext(), RingtoneManager.TYPE_RINGTONE);
        Ringtone currentRingtone = RingtoneManager.getRingtone(getActivity(), currentRintoneUri);

        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(timerEnd.getContext(), R.anim.hyperspace);
       timer = new CountDownTimer((long)nPicker.getValue()*1000, 1000) {
           @Override
           public void onTick(long millisUntilFinished) {
                nPicker.setValue((int)millisUntilFinished/1000);
            }
            @Override
            public void onFinish() {
                timerEnd.setVisibility(View.VISIBLE);
                timerEnd.setAlpha(0);
               timerEnd.startAnimation(hyperspaceJumpAnimation);
               timerEnd.setAlpha(1);
               timerEnd.setVisibility(View.INVISIBLE);
               currentRingtone.play();
            }
        }.start();
    }
    private void pauseTiming() {
        timer.cancel();
    }

    private void stopTiming() {
        Uri currentRintoneUri = RingtoneManager.getActualDefaultRingtoneUri(getActivity()
                .getApplicationContext(), RingtoneManager.TYPE_RINGTONE);
        Ringtone currentRingtone = RingtoneManager.getRingtone(getActivity(), currentRintoneUri);

        timer.cancel();
        nPicker.setValue(0);
        currentRingtone.stop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }
}