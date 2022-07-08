package cm.aptoide.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cm.aptoide.myapplication.ModelResponse.RegisterResponse;
import cm.aptoide.myapplication.R;
import cm.aptoide.myapplication.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
 TextView loginlink;
 EditText name,email,password;
 Button  register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginlink=findViewById(R.id.LoginLink);
        name=findViewById(R.id.etname);
        email=findViewById(R.id.etemail);
        password=findViewById(R.id.etpassword);
        register=findViewById(R.id.RegisterBtn);
        //hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
       loginlink.setOnClickListener(this);
       register.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.RegisterBtn:
               // Toast.makeText(MainActivity.this, "registerd!", Toast.LENGTH_SHORT).show();
                registerUser();
                break;
            case R.id.LoginLink:
                switchtologinscreen();
        }
    }

    private void registerUser() {
        //validation
        String username=name.getText().toString();
        String userPassword=password.getText().toString();
        String userEmail=email.getText().toString();

        if (username.isEmpty()){
            name.requestFocus();
            name.setError("please enter your username");
            return;
        }
        if (userEmail.isEmpty()){
            email.requestFocus();
           email.setError("please enter your username");
           return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            email.requestFocus();
            email.setError("please enter valid email");
            return;
        }
        if (userPassword.isEmpty()){
            password.requestFocus();
            password.setError("please enter your password");
            return;
        }
        if (userPassword.length()<6){
            password.requestFocus();
            password.setError("password length should be minimum six charcters");
            return;
        }
        Call<RegisterResponse> call= RetrofitClient.getInstance().getApi().register(username,userEmail,userPassword);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                //response is come
                RegisterResponse registerResponse=response.body();
                if (response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "register done" + registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,registerResponse.getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void switchtologinscreen() {
        Intent intent=new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}