package outerspacemanager.com.beaudouin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText username;
    EditText password;
    Button confirmedSignUp;

    OSMService osmService = OSMService.retrofit.create(OSMService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText)findViewById(R.id.inputTextIdentifiant);
        password = (EditText)findViewById(R.id.inputTextPassword);
        confirmedSignUp = (Button)findViewById(R.id.confirmedSignUp);
        confirmedSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.confirmedSignUp ) {

            Call<User> callUser = osmService.creatUser(
                    new User(username.getText().toString(), password.getText().toString()));

            callUser.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.i("user create", response.toString());
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("user creation error", t.toString());
                }
            });
        }

    }
}
