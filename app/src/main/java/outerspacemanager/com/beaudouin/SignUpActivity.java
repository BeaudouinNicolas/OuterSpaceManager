package outerspacemanager.com.beaudouin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText username;
    EditText password;
    Button confirmedSignIn;
    Button confirmedSignUp;

    public static final String PREFS_NAME = "PreferencesFile";
    OSMService osmService = OSMService.retrofit.create(OSMService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText)findViewById(R.id.inputTextIdentifiant);
        password = (EditText)findViewById(R.id.inputTextPassword);
        confirmedSignIn = (Button)findViewById(R.id.confirmedSignIn);
        confirmedSignUp = (Button) findViewById(R.id.confirmedSignUp);
        confirmedSignIn.setOnClickListener(this);
        confirmedSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.confirmedSignIn ) {
            Call<User> callUser = osmService.loginUser(
                    new User(username.getText().toString(), password.getText().toString(), ""));

            callUser.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.code() == 401) {
                        Toast.makeText(getApplicationContext(),
                                "Une erreur est survenu lors de la connexion",
                                Toast.LENGTH_LONG).show();
                    } else {
                        // SharedPreferences content the user token
                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("userToken", response.body().getToken());
                        editor.commit();

                        Intent goToMenu = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(goToMenu);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("user login error", t.toString());
                }
            });
        } else if(v.getId() == R.id.confirmedSignUp) {
            Call<User> createUser = osmService.creatUser(
                    new User(username.getText().toString(), password.getText().toString(), ""));

            createUser.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.code() == 400) {
                        Toast.makeText(getApplicationContext(),
                                "Le compte existe déjà appuyé sur Connexion",
                                Toast.LENGTH_LONG).show();
                    } else if(response.code() == 401) {
                        Toast.makeText(getApplicationContext(),
                                "Une erreur est survenu lors de la création du compte",
                                Toast.LENGTH_LONG).show();
                    } else {
                        // SharedPreferences content the user token
                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("userToken", response.body().getToken());
                        editor.commit();

                        Intent goToMenu = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(goToMenu);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("create user error", t.toString());
                }
            });


        }

    }
}
