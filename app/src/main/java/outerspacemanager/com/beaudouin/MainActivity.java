package outerspacemanager.com.beaudouin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import outerspacemanager.com.beaudouin.models.User;
import outerspacemanager.com.beaudouin.services.OSMService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView currentUsername;
    private TextView currentUserPoints;
    private TextView currentUserMinerals;
    private TextView currentUserGas;
    private Button buildings;
    private Button logout;

    private OSMService osmService = OSMService.retrofit.create(OSMService.class);
    private static final String PREFS_NAME = "PreferencesFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentUsername = (TextView)findViewById(R.id.currentUsername);
        currentUserPoints = (TextView)findViewById(R.id.currentUserPoints);
        currentUserMinerals = (TextView)findViewById(R.id.userMinerals);
        currentUserGas = (TextView)findViewById(R.id.userGas);
        buildings = (Button)findViewById(R.id.buildings);
        logout = (Button)findViewById(R.id.logout);

        buildings.setOnClickListener(this);
        logout.setOnClickListener(this);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        Call<User> currentUser = osmService.getCurrentUser(settings.getString("userToken", ""));
        currentUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                currentUsername.setText(response.body().getUsername());
                currentUserPoints.setText("Points : " + response.body().getPoints().toString());
                currentUserMinerals.setText(response.body().getMinerals().toString() + " : ");
                currentUserGas.setText(" : " + response.body().getGas().toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("An error occurred : ", t.getMessage());
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.buildings:
                Intent goToBuilding = new Intent(getApplicationContext(), BuildingsActivity.class);
                startActivity(goToBuilding);
                break;
            case R.id.logout:
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.remove("userToken");
                editor.commit();

                Intent goToSignUp = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(goToSignUp);
                break;
        }
    }
}
