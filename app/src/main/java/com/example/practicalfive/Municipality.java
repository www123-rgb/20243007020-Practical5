package com.example.practicalfive;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

/**
 * Second screen: displays the selected municipality's name, image, summary,
 * colour scheme and a "Visit site" button that opens the municipality's
 * official website with an implicit Intent.
 */
public class Municipality extends AppCompatActivity {

    // Indexes into municipalities_array (order: Beijing, Shanghai, Chongqing, Tianjin).
    private static final int INDEX_BEIJING = 0;
    private static final int INDEX_SHANGHAI = 1;
    private static final int INDEX_CHONGQING = 2;
    private static final int INDEX_TIANJIN = 3;

    private String websiteUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        // Retrieve and re-validate the name passed from MainActivity.
        String received = getIntent().getStringExtra(MainActivity.EXTRA_MUNICIPALITY);
        String municipality = TextProcessor.processMunicipality(received);

        if (municipality == null) {
            Toast.makeText(this, R.string.error_no_municipality, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // Choose the named colour, image, summary index and website for this municipality.
        int colourRes;
        int imageRes;
        int summaryIndex;
        switch (municipality) {
            case "Shanghai":
                colourRes = R.color.municipality_shanghai;
                imageRes = R.drawable.shanghai;
                summaryIndex = INDEX_SHANGHAI;
                websiteUrl = "https://english.shanghai.gov.cn/";
                break;
            case "Tianjin":
                colourRes = R.color.municipality_tianjin;
                imageRes = R.drawable.tianjin;
                summaryIndex = INDEX_TIANJIN;
                websiteUrl = "https://en.tj.gov.cn/";
                break;
            case "Chongqing":
                colourRes = R.color.municipality_chongqing;
                imageRes = R.drawable.chongqing;
                summaryIndex = INDEX_CHONGQING;
                websiteUrl = "https://english.cq.gov.cn/";
                break;
            case "Beijing":
            default:
                colourRes = R.color.municipality_beijing;
                imageRes = R.drawable.beijing;
                summaryIndex = INDEX_BEIJING;
                websiteUrl = "https://english.beijing.gov.cn/";
                break;
        }

        int colour = ContextCompat.getColor(this, colourRes);

        // Municipality name: coloured background with light text.
        TextView textViewName = findViewById(R.id.textViewMunicipalityName);
        textViewName.setText(municipality);
        textViewName.setBackgroundColor(colour);
        textViewName.setTextColor(ContextCompat.getColor(this, R.color.color_text_light));

        // Municipality image.
        ImageView imageView = findViewById(R.id.imageViewMunicipality);
        imageView.setImageResource(imageRes);

        // Municipality summary, coloured to match the theme.
        TextView textViewInformation = findViewById(R.id.textViewInformation);
        String[] summaries = getResources().getStringArray(R.array.municipalities_array);
        textViewInformation.setText(summaries[summaryIndex]);
        textViewInformation.setTextColor(colour);

        // Visit site button: coloured background with light text.
        Button buttonVisitSite = findViewById(R.id.buttonVisitSite);
        buttonVisitSite.setBackgroundColor(colour);
        buttonVisitSite.setTextColor(ContextCompat.getColor(this, R.color.color_text_light));
        buttonVisitSite.setOnClickListener(view -> openWebsite());
    }

    /** Opens the official website of the displayed municipality via an implicit Intent. */
    private void openWebsite() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.no_browser_app, Toast.LENGTH_LONG).show();
        }
    }
}
