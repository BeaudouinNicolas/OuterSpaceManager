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

import outerspacemanager.com.beaudouin.models.User;
import outerspacemanager.com.beaudouin.services.OSMService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText username;
    EditText password;
    EditText email;
    Button confirmedSignIn;
    Button confirmedSignUp;

    public static final String PREFS_NAME = "PreferencesFile";
    OSMService osmService = OSMService.retrofit.create(OSMService.class);
    private ProgressDialogUtil progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText)findViewById(R.id.inputTextIdentifiant);
        password = (EditText)findViewById(R.id.inputTextPassword);
        email = (EditText)findViewById(R.id.inputTextEmail);

        confirmedSignIn = (Button)findViewById(R.id.confirmedSignIn);
        confirmedSignUp = (Button) findViewById(R.id.confirmedSignUp);
        confirmedSignIn.setOnClickListener(this);
        confirmedSignUp.setOnClickListener(this);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if(settings.contains("userToken")) {
            Intent goToMenu = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(goToMenu);
        }

    }

    @Override
    public void onClick(View v) {
        progressDialog = new ProgressDialogUtil(this);
        progressDialog.launch();

        if(v.getId() == R.id.confirmedSignIn ) {
            Call<User> callUser = osmService.loginUser(
                    new User(username.getText().toString(),
                             password.getText().toString(),
                             "",
                             ""));

            callUser.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    progressDialog.stop();
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
                    progressDialog.stop();
                    Log.e("user login error", t.toString());
                }
            });
        } else if(v.getId() == R.id.confirmedSignUp) {
            Call<User> createUser = osmService.creatUser(
                    new User(username.getText().toString(),
                            password.getText().toString(),
                            "",
                            email.getText().toString()));

            createUser.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    progressDialog.stop();
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
                    progressDialog.stop();
                    Log.e("create user error", t.toString());
                }
            });


        }

    }
}
