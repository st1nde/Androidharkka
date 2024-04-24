package com.example.androidharkka;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;

import com.example.androidharkka.utils.DataStoreSingleton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.Random;

import io.reactivex.rxjava3.core.Single;

public class GameActivity extends AppCompatActivity {


    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private ImageButton imageButton3;
    private ImageButton imageButton4;
    private TextView hScore;
    private int answer;
    public int score = 0;
    public int btnIndex;
    public int highScore = 5;
    public Toolbar toolbar;

    Preferences.Key<Integer> KEY_SCORE;

    RxDataStore<Preferences> dataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        imageButton1 = findViewById(R.id.imageButton1);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton3 = findViewById(R.id.imageButton3);
        imageButton4 = findViewById(R.id.imageButton4);

        hScore = findViewById(R.id.hScore);

        KEY_SCORE = PreferencesKeys.intKey("Score");


        answer = new Random().nextInt(3);

        ImageView khorne = findViewById(R.id.khorne);
        khorne.setVisibility(View.INVISIBLE);

        DataStoreSingleton dataStoreSingleton = DataStoreSingleton.getInstance();
        if (dataStoreSingleton.getDataStore() == null) {
            dataStore = new RxPreferenceDataStoreBuilder(this, "highScore").build();
        } else {
            dataStore = dataStoreSingleton.getDataStore();
        }
        dataStoreSingleton.setDataStore(dataStore);




        for (ImageButton imageButton : Arrays.asList(imageButton1, imageButton2, imageButton3, imageButton4)) {
            imageButton.setOnClickListener(this::handleOnClickEvents);
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(GameActivity.this, GameActivity.class);
            startActivity(intent);
        });
        hScore.setText(getStringValue(KEY_SCORE));
    }




    public void putValue(Preferences.Key<Integer> Key, int value){

        dataStore.updateDataAsync(prefsIn -> {
            MutablePreferences mutablePreferences = prefsIn.toMutablePreferences();
            mutablePreferences.set(Key, value);
            return Single.just(mutablePreferences);
        });
    }

    String getStringValue(Preferences.Key<Integer> Key) {

        Single<Integer> value = dataStore.data().firstOrError().map(prefs -> prefs.get(Key)).onErrorReturnItem(0);
        return value.blockingGet().toString();
    }


    private void handleOnClickEvents(View v) {
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.hyperspace);


        if (v.getId() == R.id.imageButton1) {
            btnIndex = 0;
            score = score + 1;

            imageButton1.startAnimation(hyperspaceJumpAnimation);
            imageButton1.setVisibility(View.INVISIBLE);
            if (btnIndex == answer) {


                imageButton1.setImageResource(R.drawable.khorne);
                imageButton1.setBackgroundColor(Color.BLACK);
                imageButton1.setVisibility(View.VISIBLE);
                highScore = highScore - score;
                putValue(KEY_SCORE, highScore);
            }

        } else if (v.getId() == R.id.imageButton2) {
            btnIndex = 1;
            score = score + 1;
            imageButton2.startAnimation(hyperspaceJumpAnimation);
            imageButton2.setVisibility(View.INVISIBLE);

            if (btnIndex == answer) {
                imageButton2.setImageResource(R.drawable.khorne);
                imageButton2.setBackgroundColor(Color.BLACK);
                imageButton2.setVisibility(View.VISIBLE);
                highScore = highScore - score;
                putValue(KEY_SCORE, highScore);
            }
        } else if (v.getId() == R.id.imageButton3) {
            btnIndex = 2;
            score = score + 1;
            imageButton3.startAnimation(hyperspaceJumpAnimation);
            imageButton3.setVisibility(View.INVISIBLE);

            if (btnIndex == answer) {
                imageButton3.setImageResource(R.drawable.khorne);
                imageButton3.setBackgroundColor(Color.BLACK);
                imageButton3.setVisibility(View.VISIBLE);
                highScore = highScore - score;
                putValue(KEY_SCORE, highScore);
            }
        } else if (v.getId() == R.id.imageButton4) {
            btnIndex = 3;
            score = score + 1;
            imageButton4.startAnimation(hyperspaceJumpAnimation);
            imageButton4.setVisibility(View.INVISIBLE);

            if (btnIndex == answer) {
                imageButton4.setImageResource(R.drawable.khorne);
                imageButton4.setBackgroundColor(Color.BLACK);
                imageButton4.setVisibility(View.VISIBLE);
                highScore = highScore - score;
                putValue(KEY_SCORE, highScore);
            }
        }
    }
}

