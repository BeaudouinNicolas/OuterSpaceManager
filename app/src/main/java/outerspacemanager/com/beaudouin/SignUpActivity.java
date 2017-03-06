package outerspacemanager.com.beaudouin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    EditText login;
    EditText password;
    Button confirmedSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        login = (EditText)findViewById(R.id.inputTextIdentifiant);
        password = (EditText)findViewById(R.id.inputTextPassword);
        confirmedSignUp = (Button)findViewById(R.id.confirmedSignUp);

    }
}
