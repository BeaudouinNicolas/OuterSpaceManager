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
    private Button buildings;

    private OSMService osmService = OSMService.retrofit.create(OSMService.class);
    private static final String PREFS_NAME = "PreferencesFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentUsername = (TextView)findViewById(R.id.currentUsername);
        currentUserPoints = (TextView)findViewById(R.id.currentUserPoints);
        buildings = (Button)findViewById(R.id.buildings);

        buildings.setOnClickListener(this);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        Call<User> currentUser = osmService.getCurrentUser(settings.getString("userToken", ""));
        currentUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                currentUsername.setText(response.body().getUsername());
                currentUserPoints.setText("Points : " + response.body().getPoints().toString());
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
                Intent goToBuilding = new Intent(getApplicationContext(), BuildingActivity.class);
                startActivity(goToBuilding);
                break;
        }
    }
}
