package com.duyuqian.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class LifeCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        Log.i("Activity生命周期","调用onCreate()方法");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Activity生命周期","调用onStart()方法");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Activity生命周期","调用onResume()方法");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Activity生命周期","调用onPause()方法");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Activity生命周期","调用onStop()方法");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Activity生命周期","调用onRestart()方法");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Activity生命周期","调用onDestroy()方法");
    }
}