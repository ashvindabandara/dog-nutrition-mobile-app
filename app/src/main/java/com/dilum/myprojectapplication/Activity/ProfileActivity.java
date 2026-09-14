package com.dilum.myprojectapplication.Activity;

import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dilum.myprojectapplication.R;

public class ProfileActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText Name,Type,Feedback,editUpdate;
    Button btnAddFeed,btnViewFeed,btnUpdateFeed,btnDeleteFeed;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile2);
        myDb = new DatabaseHelper(this);
        Name = findViewById(R.id.editName);
        Type = findViewById(R.id.editType);
        editUpdate = findViewById(R.id.editUpdate);
        Feedback = findViewById(R.id.editFeedback);
        btnAddFeed = findViewById(R.id.btnAddFeed);
        btnViewFeed = findViewById(R.id.btnViewFeed);
        btnUpdateFeed = findViewById(R.id.btnUpdateFeed);
        btnDeleteFeed = findViewById(R.id.btnDeleteFeed);

        addData();
        viewAll();
        updateData();
        deleteData();

        mediaPlayer = MediaPlayer.create(this,R.raw.speech);

        Feedback.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String[] words = s.toString().trim().split("\\s+");
                if (words.length > 20){
                    mediaPlayer.start();
                }

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void deleteData() {
        btnDeleteFeed.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editUpdate.getText().toString());
                        if (deletedRows>0)
                            Toast.makeText(ProfileActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ProfileActivity.this, "Data Not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    private void updateData() {
        btnUpdateFeed.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = myDb.updateData(editUpdate.getText().toString(),Name.getText().toString(),Type.getText().toString(),Feedback.getText().toString());
                        if (isUpdated == true)
                            Toast.makeText(ProfileActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ProfileActivity.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void viewAll() {
        btnViewFeed.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor results = myDb.getAllData();
                        if (results.getCount()==0){
                            showMessage("Error Message :" , "No Data Found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while(results.moveToNext()){
                            buffer.append("Id :" + results.getString(0) + "\n");
                            buffer.append("Name:" + results.getString(1) + "\n");
                            buffer.append("Type :" + results.getString(2) + "\n");
                            buffer.append("Feedback: " + results.getString(3) + "\n");

                            showMessage("List Of Data: ",buffer.toString());
                        }

                    }
                }
        );
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    private void addData() {
        btnAddFeed.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(Name.getText().toString(),Type.getText().toString(),Feedback.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(ProfileActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ProfileActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

}