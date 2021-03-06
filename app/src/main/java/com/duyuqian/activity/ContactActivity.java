package com.duyuqian.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    static final int REQUEST_SELECT_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Button chooseContact = findViewById(R.id.choose_contact);
        chooseContact.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectContact();
                    }
                }
        );

        Button lifeCycle = findViewById(R.id.life_cycle);
        lifeCycle.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ContactActivity.this, LifeCycleActivity.class);
                        startActivity(intent);
                    }
                });
    }


    public void selectContact() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_SELECT_CONTACT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == REQUEST_SELECT_CONTACT && resultCode == RESULT_OK) {
            Cursor phoneCursor = null;
            try {
                Uri contractUri = data.getData();
                if (contractUri != null) {
                    String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
                    phoneCursor = getContentResolver().query(contractUri, projection, null, null, null);
                    if (phoneCursor != null) {
                        if (phoneCursor.moveToFirst()) {
                            String contactName = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                            String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            TextView contactInfo = findViewById(R.id.contact_info);
                            contactInfo.setText(contactName.concat(phoneNumber));
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                phoneCursor.close();
            }
        }
    }
}