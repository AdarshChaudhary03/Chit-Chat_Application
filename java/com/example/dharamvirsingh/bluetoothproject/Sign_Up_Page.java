package com.example.dharamvirsingh.bluetoothproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Sign_Up_Page extends AppCompatActivity {

    Button register;
    EditText username,password,confirmpassword;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up__page);

        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirmpassword = (EditText) findViewById(R.id.confirmpassword);
        register = (Button) findViewById(R.id.register);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("")
                        & password.getText().toString().equals("")
                        & confirmpassword.getText().toString().equals(""))
                {
                    Toast.makeText(Sign_Up_Page.this, "Please fill all fields..!!", Toast.LENGTH_SHORT).show();
                    return;

                }
                else if(!password.getText().toString().equals(confirmpassword.getText().toString()))
                {
                    Toast.makeText(Sign_Up_Page.this, "Password doesn't match..!!", Toast.LENGTH_SHORT).show();
                    return;

                }
                else
                {

                        String userName=username.getText().toString();
                        String pwd=password.getText().toString();
                        String confirmPassword=confirmpassword.getText().toString();



                        // Save the Data in Database
                        loginDataBaseAdapter.insertEntry(userName, pwd);
                        Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                        Intent i=new Intent(Sign_Up_Page.this,Login_Page.class);
                        i.putExtra("uname",username.getText().toString());
                        i.putExtra("pwd",password.getText().toString());
                        startActivity(i);

                    }

                }

});
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDataBaseAdapter.close();

    }
}

