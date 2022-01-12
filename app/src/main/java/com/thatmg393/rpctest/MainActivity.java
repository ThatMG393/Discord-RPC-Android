package com.thatmg393.rpctest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.thatmg393.discordrpc.DiscordRPC;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DiscordRPC d = new DiscordRPC(MainActivity.this);

        d.init();
    }
}
