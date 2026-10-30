package com.example.practicalfive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Main screen: lets the user type the name of one of the four Chinese
 * municipalities, validates the input with {@link TextProcessor} and, when
 * valid, navigates to {@link Municipality} using an explicit Intent.
 */
public class MainActivity extends AppCompatActivity {

    /** Intent extra key shared with Municipality to pass the normalised name. */
    public static final String EXTRA_MUNICIPALITY = "municipality_name";

    private EditText editTextMunicipality;
    private TextView textViewError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMunicipality = findViewById(R.id.editTextMunicipality);
        textViewError = findViewById(R.id.textViewError);

        // Submit when the user presses the keyboard's Go (or Done) action.
        editTextMunicipality.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE) {
                submitMunicipality();
                return true;
            }
            return false;
        });
    }

    /**
     * Reads the input, validates it with TextProcessor and either shows an
     * error (staying on this screen) or opens the Municipality screen.
     */
    private void submitMunicipality() {
        String input = editTextMunicipality.getText().toString();
        String municipality = TextProcessor.processMunicipality(input);

        if (municipality == null) {
            boolean empty = input == null || input.trim().isEmpty();
            textViewError.setText(empty ? R.string.error_empty : R.string.error_invalid);
            textViewError.setVisibility(View.VISIBLE);
            return;
        }

        textViewError.setVisibility(View.GONE);

        // Explicit Intent to the second Activity, passing the normalised name.
        Intent intent = new Intent(MainActivity.this, Municipality.class);
        intent.putExtra(EXTRA_MUNICIPALITY, municipality);
        startActivity(intent);
    }
}
