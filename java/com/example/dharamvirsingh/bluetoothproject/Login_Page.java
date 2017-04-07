package com.example.dharamvirsingh.bluetoothproject;

import android.app.ActivityOptions;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Login_Page extends AppCompatActivity implements View.OnClickListener {

    LoginDataBaseAdapter loginDataBaseAdapter;
    Button b1, b2;
    EditText t1, t2;
    ProgressBar pb;
    Thread t;
    NotificationCompat.Builder nc;
    private static final int uniqueID = 45612;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        b1 = (Button) findViewById(R.id.login);
        b2 = (Button) findViewById(R.id.signup);
        t1 = (EditText) findViewById(R.id.username);
        t2 = (EditText) findViewById(R.id.password);
        pb = (ProgressBar)findViewById(R.id.pb);
        assert pb != null;
        pb.setVisibility(View.INVISIBLE);
        nc = new NotificationCompat.Builder(Login_Page.this);
        nc.setAutoCancel(true);


        b2.setOnClickListener(this);
        b1.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.signup:
                Intent i = new Intent(Login_Page.this, Sign_Up_Page.class);
                final ActivityOptions options = ActivityOptions.makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight());
                startActivity(i, options.toBundle());
                break;
            case R.id.login:
                String userName = t1.getText().toString();
                String password = t2.getText().toString();
                String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);
                if (password.equals(storedPassword)) {
                    Toast.makeText(Login_Page.this, "Please wait...", Toast.LENGTH_SHORT).show();
                    pb.setVisibility(View.VISIBLE);
                    t = new Thread() {
                        @Override
                        public void run() {
                            try {
                                for (int i = 0; i < 25; i++) {
                                    pb.setProgress(i * 4);
                                    sleep(100);
                                }

                                Intent i1 = new Intent(Login_Page.this, MainActivity.class);
                                String username = t1.getText().toString();
                                i1.putExtra("UserName",username);
                                startActivity(i1);
                                nc.setSmallIcon(R.drawable.app_icon);
                                nc.setTicker("Chit-Chat");
                                nc.setWhen(System.currentTimeMillis());
                                nc.setContentTitle("Welcome To Chit-Chat");
                                nc.setContentText("Enjoy offline chatting with your Buddies..!!");

                                PendingIntent pi = PendingIntent.getActivity(Login_Page.this, 0, i1, PendingIntent.FLAG_UPDATE_CURRENT);
                                nc.setContentIntent(pi);

                                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                nm.notify(uniqueID, nc.build());


                            } catch (Exception e) {
                                Toast.makeText(Login_Page.this, "Error Occured", Toast.LENGTH_SHORT).show();

                            }
                        }
                    };
                    t.start();
                }
                else
                {
                    Toast.makeText(Login_Page.this, "Username or password doesn't match..!!", Toast.LENGTH_SHORT).show();
                }

                break;


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDataBaseAdapter.close();
    }
}