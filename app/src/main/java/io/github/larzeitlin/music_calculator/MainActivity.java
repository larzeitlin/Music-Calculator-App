package io.github.larzeitlin.music_calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Note mainNote = new Note();

    private double handleEmptyInputDouble(CharSequence s){
        if (String.valueOf(s).equals("")) {
            return 0.0;
        }
        else {
            return Double.parseDouble(s.toString());
        }
    }

    private int handleEmptyInputInt(CharSequence s){
        if (String.valueOf(s).equals("")) {
            return 4;
        }
        else {
            return Integer.parseInt(String.valueOf(s));
        }
    }

    private String handleInputNote(CharSequence s){
        int counter = 0;
        String inputString = s.toString();
        String[] validNoteNames = Note.getNotesInOctave();
        while (counter < 12) {
            if (inputString.equals(validNoteNames[counter])) {
                return inputString;
            }
            else {
                counter += 1;
            }
        }
        return validNoteNames[9];
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText HzEditText = findViewById(R.id.HzEditText);
        HzEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (getCurrentFocus() == HzEditText) {
                    EditText nearestNoteView = findViewById(R.id.nearestNoteView);
                    EditText octaveView = findViewById(R.id.octaveView);
                    EditText centOffsetView = findViewById(R.id.centOffsetView);
                    double hz = handleEmptyInputDouble(s);
                    mainNote.setHz(hz);
                    nearestNoteView.setText(mainNote.getNoteName());
                    octaveView.setText(String.valueOf(mainNote.getOctave()));
                    centOffsetView.setText(String.valueOf(mainNote.getCents()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        final EditText nearestNoteView = findViewById(R.id.nearestNoteView);
        nearestNoteView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (getCurrentFocus() == nearestNoteView) {
                    EditText HzEditText = findViewById(R.id.HzEditText);
                    EditText octaveView = findViewById(R.id.octaveView);
                    String noteName = handleInputNote(s);
                    int octave = handleEmptyInputInt(octaveView.getText());
                    mainNote.setNote(noteName, octave);
                    HzEditText.setText(String.valueOf(mainNote.getHz()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });




        final EditText octaveView = findViewById(R.id.octaveView);
        octaveView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (getCurrentFocus() == octaveView) {
                    EditText HzEditText = findViewById(R.id.HzEditText);
                    EditText nearestNoteView = findViewById(R.id.nearestNoteView);
                    String noteName = handleInputNote(nearestNoteView.getText());
                    int octave = handleEmptyInputInt(s);
                    mainNote.setNote(noteName, octave);
                    HzEditText.setText(String.valueOf(mainNote.getHz()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        EditText centOffsetView = findViewById(R.id.centOffsetView);
        centOffsetView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
