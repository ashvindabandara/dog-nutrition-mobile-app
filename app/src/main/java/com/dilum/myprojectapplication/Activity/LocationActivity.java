package com.dilum.myprojectapplication.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dilum.myprojectapplication.R;

public class LocationActivity extends AppCompatActivity {

    private EditText editTextStart,editTextEnd;
    private Button btnGetPath,btnMoreInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_location);

        editTextStart = findViewById(R.id.editTextStart);
        editTextEnd = findViewById(R.id.editTextEnd);
        btnGetPath = findViewById(R.id.btnGetPath);
        btnMoreInfo = findViewById(R.id.btnMoreInfo);

        btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocationActivity.this,VideoActivity.class));
            }
        });

        btnGetPath.setOnClickListener(v -> {
            String startingPoint = editTextStart.getText().toString();
            String endPoint = editTextEnd.getText().toString();

            if(startingPoint.equals("")|| endPoint.equals("")){
                Toast.makeText(this, "Please enter starting destination and location", Toast.LENGTH_SHORT).show();
            }else {
                getPath(startingPoint,endPoint);
            }
                });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void getPath(String startingPoint, String endPoint) {
        try {
            startingPoint = startingPoint.replace(" ","20%");
            endPoint = endPoint.replace(" ","20%");
            Uri uri = Uri.parse("https://www.google.com/maps/dir/"+startingPoint + "/" + endPoint);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }catch (ActivityNotFoundException exception){
            Uri uri = Uri.parse("https://www.google.com/store/apps/details?id=com.google.android.apps.maps&hl=en&gl=US");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}