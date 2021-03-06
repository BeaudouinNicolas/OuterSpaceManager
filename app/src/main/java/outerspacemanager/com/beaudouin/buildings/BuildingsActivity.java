package outerspacemanager.com.beaudouin.buildings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import outerspacemanager.com.beaudouin.ProgressDialogUtil;
import outerspacemanager.com.beaudouin.R;
import outerspacemanager.com.beaudouin.models.Building;
import outerspacemanager.com.beaudouin.models.Buildings;
import outerspacemanager.com.beaudouin.models.User;
import outerspacemanager.com.beaudouin.services.OSMService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuildingsActivity extends Activity {

    private ListView lvBuildings;

    OSMService osmService = OSMService.retrofit.create(OSMService.class);
    private static final String PREFS_NAME = "PreferencesFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);
        final ArrayList<Building> buildingList = (ArrayList<Building>)getIntent().getSerializableExtra("USER_BUILDINGS");
        final ProgressDialogUtil progressDialog = new ProgressDialogUtil(context);

        lvBuildings = (ListView)findViewById(R.id.buildingsListView);
        lvBuildings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

                progressDialog.launch();
                final Call<Building> createBuilding = osmService.postBuilding(position, settings.getString("userToken", ""));
                createBuilding.enqueue(new Callback<Building>() {
                    @Override
                    public void onResponse(Call<Building> call, Response<Building> response) {
                        progressDialog.stop();
                        if(response.code() == 401) {
                            Toast message = Toast.makeText(context, "Impossible pour le moment...", Toast.LENGTH_SHORT);
                            message.show();
                        } else if(response.code() == 200) {
                            TextView levelUp = (TextView)findViewById(R.id.levelUp);
                            levelUp.setBackgroundColor(Color.parseColor("#D98E1E"));
                            levelUp.setText(context.getString(R.string.buildinIsBuilding));
                        }
                    }
                    @Override
                    public void onFailure(Call<Building> call, Throwable t) {
                        progressDialog.stop();
                        Log.e("An error occurred : ", t.getMessage());
                    }
                });
            }
        });

        User currentUser = new User();
        currentUser.setMinerals(Float.parseFloat(getIntent().getStringExtra("USER_MINERALS")));
        currentUser.setGas(Float.parseFloat(getIntent().getStringExtra("USER_GAS")));
        lvBuildings.setAdapter(new BuildingsAdapter(context, buildingList, currentUser));

    }
}
