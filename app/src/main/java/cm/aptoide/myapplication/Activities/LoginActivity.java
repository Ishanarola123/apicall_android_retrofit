package cm.aptoide.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cm.aptoide.myapplication.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText email,password;
    Button login;
    TextView registerlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.etemailLogin);
        password=findViewById(R.id.etpasswordLogin);
        login=findViewById(R.id.LoginBtn);
        registerlink=findViewById(R.id.RegisterLink);
        //hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        registerlink.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.RegisterLink:
                switchtoRegisterScreen();
                break;
            case R.id.LoginBtn:
                SwitchtoHomeScreen();

        }
    }

    private void SwitchtoHomeScreen() {
        Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    private void switchtoRegisterScreen() {
        Intent intent=new Intent(LoginActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}