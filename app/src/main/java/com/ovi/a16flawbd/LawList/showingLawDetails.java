package com.ovi.a16flawbd.LawList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ovi.a16flawbd.R;

public class showingLawDetails extends AppCompatActivity {

    TextView lawTitles, lawDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_law_details);


        lawTitles = findViewById(R.id.lawTitle);
        lawDetails = findViewById(R.id.lawDetails);
        lawDetails.setText(details[0]);
    }


    final String[] details = new String[]{
            " Judicial precedent is enshrined under Article 111 of the Constitution of Bangladesh.[4] \n" +
                    "Bangladeshi courts have provided vital judicial precedent in areas like constitutional law, such as in Bangladesh Italian Marble Works Ltd." +
                    " v. Government of Bangladesh, which declared martial law illegal. The judgement of Secretary, Ministry of Finance v Masdar Hossain asserted the separation of powers and judicial independence. \n" +
                    "In Aruna Sen " +
                    "v. Government of Bangladesh, the Supreme Court set a precedent against unlawful detention and torture. " +
                    "The court affirmed the principle of natural justice in the judgement of Abdul Latif Mirza v. Government of Bangladesh. The two verdicts were precedents for invalidating most detentions under the Special Powers Act, 1974. \n" +
                    "The doctrine of legitimate expectation in Bangladeshi law has developed through judicial precedent. "
    };

}