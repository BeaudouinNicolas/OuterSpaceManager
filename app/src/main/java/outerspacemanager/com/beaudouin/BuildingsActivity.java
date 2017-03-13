package outerspacemanager.com.beaudouin;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import outerspacemanager.com.beaudouin.models.Building;
import outerspacemanager.com.beaudouin.models.Buildings;
import outerspacemanager.com.beaudouin.services.OSMService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuildingsActivity extends AppCompatActivity {

    private ListView lvBuildings;

    OSMService osmService = OSMService.retrofit.create(OSMService.class);
    private static final String PREFS_NAME = "PreferencesFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);

        lvBuildings = (ListView)findViewById(R.id.buildingsListView);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        Call<Buildings> buildingsCall = osmService.getBuildings(settings.getString("userToken", ""));
        buildingsCall.enqueue(new Callback<Buildings>() {
            @Override
            public void onResponse(Call<Buildings> call, Response<Buildings> response) {
                lvBuildings.setAdapter(new BuildingsAdapter(getApplicationContext(), response.body().getBuildings()));
            }

            @Override
            public void onFailure(Call<Buildings> call, Throwable t) {
                Log.e("An error occurred", t.getMessage());
            }
        });




    }
}
