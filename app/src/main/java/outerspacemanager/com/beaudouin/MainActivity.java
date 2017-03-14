package outerspacemanager.com.beaudouin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import outerspacemanager.com.beaudouin.buildings.BuildingsActivity;
import outerspacemanager.com.beaudouin.buildings.BuildingsAdapter;
import outerspacemanager.com.beaudouin.galaxy.GalaxyActivity;
import outerspacemanager.com.beaudouin.models.Buildings;
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
    private Button galaxy;
    private Button logout;

    OSMService osmService = OSMService.retrofit.create(OSMService.class);
    private static final String PREFS_NAME = "PreferencesFile";
    private ProgressDialogUtil progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentUsername = (TextView)findViewById(R.id.currentUsername);
        currentUserPoints = (TextView)findViewById(R.id.currentUserPoints);
        currentUserMinerals = (TextView)findViewById(R.id.userMinerals);
        currentUserGas = (TextView)findViewById(R.id.userGas);
        buildings = (Button)findViewById(R.id.buildings);
        galaxy = (Button)findViewById(R.id.galaxy);
        logout = (Button)findViewById(R.id.logout);

        buildings.setOnClickListener(this);
        galaxy.setOnClickListener(this);
        logout.setOnClickListener(this);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        Call<User> currentUser = osmService.getCurrentUser(settings.getString("userToken", ""));
        currentUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                currentUsername.setText(response.body().getUsername());
                currentUserPoints.setText("Points : " + response.body().getPoints().toString());
                currentUserMinerals.setText(response.body().getMinerals().toString());
                currentUserGas.setText(response.body().getGas().toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("An error occurred : ", t.getMessage());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        Call<User> currentUser = osmService.getCurrentUser(settings.getString("userToken", ""));
        currentUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                currentUsername.setText(response.body().getUsername());
                currentUserPoints.setText("Points : " + response.body().getPoints().toString());
                currentUserMinerals.setText(response.body().getMinerals().toString());
                currentUserGas.setText(response.body().getGas().toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("An error occurred : ", t.getMessage());
            }
        });

    }

    @Override
    public void onClick(View v) {
        final Context context = this;
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        progressDialog = new ProgressDialogUtil(this);


        switch(v.getId()) {
            case R.id.buildings:

                progressDialog.launch();
                Call<Buildings> buildingsCall = osmService.getBuildings(settings.getString("userToken", ""));
                buildingsCall.enqueue(new Callback<Buildings>() {
                    @Override
                    public void onResponse(Call<Buildings> call, Response<Buildings> response) {
                        // stop the progress bar
                        progressDialog.stop();
                        if(response.code() == 200) {
                            // create the intent and get all the user buildings
                            Intent goToBuilding = new Intent(getApplicationContext(), BuildingsActivity.class);
                            goToBuilding.putExtra("USER_BUILDINGS", response.body().getBuildings());
                            goToBuilding.putExtra("USER_MINERALS", currentUserMinerals.getText());
                            goToBuilding.putExtra("USER_GAS", currentUserGas.getText());

                            // start building activity
                            startActivity(goToBuilding);
                        } else {
                            Toast error = Toast.makeText(context, "Une erreur est survenue...", Toast.LENGTH_LONG);
                            error.show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Buildings> call, Throwable t) {
                        Log.e("An error occurred", t.getMessage());
                    }
                });

                break;
            case R.id.galaxy:

                Intent goToGalaxy = new Intent(getApplicationContext(), GalaxyActivity.class);
                startActivity(goToGalaxy);

                break;
            case R.id.logout:

                SharedPreferences.Editor editor = settings.edit();
                editor.remove("userToken");
                editor.commit();

                Intent goToSignUp = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(goToSignUp);

                break;
        }
    }
}
