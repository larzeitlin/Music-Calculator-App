package io.github.larzeitlin.music_calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Note mainNote = new Note();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                EditText HzEditText = findViewById(R.id.HzEditText);
                TextView nearestNoteView = findViewById(R.id.nearestNoteView);
                TextView centOffsetView = findViewById(R.id.centOffsetView);
                double hz = Double.parseDouble(HzEditText.getText().toString());
                mainNote.setHz(hz);
                String noteString = mainNote.getNoteName() + " " + String.valueOf(mainNote.getOctave());
                nearestNoteView.setText(noteString);
                centOffsetView.setText(String.valueOf(mainNote.getCents()));
            }
        });
    }
}
