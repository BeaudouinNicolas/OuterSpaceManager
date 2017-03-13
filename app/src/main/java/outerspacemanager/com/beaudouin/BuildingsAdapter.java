package outerspacemanager.com.beaudouin;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import outerspacemanager.com.beaudouin.models.Building;
import outerspacemanager.com.beaudouin.models.User;
import outerspacemanager.com.beaudouin.services.OSMService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nico on 07/03/17.
 */

public class BuildingsAdapter extends ArrayAdapter<Building> {

    private final Context context;
    private final ArrayList<Building> objects;

    private OSMService osmService = OSMService.retrofit.create(OSMService.class);
    private static final String PREFS_NAME = "PreferencesFile";

    public BuildingsAdapter(@NonNull Context context, @NonNull ArrayList<Building> objects) {
        super(context, R.layout.building_adapter, objects);
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.building_adapter, parent, false);

        setRowAdapter(position, rowView);

        Button levelUp = (Button)rowView.findViewById(R.id.levelUp);
        levelUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.levelUp) {
                    SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
                    final Call<String> createBuilding = osmService.postBuilding(position + 1, settings.getString("userToken", ""));

                    createBuilding.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast toast = Toast.makeText(context, "En cours de construction", Toast.LENGTH_LONG);
                            toast.show();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.e("An error occurred : ", t.getMessage());
                        }
                    });
                }
            }
        });


        return rowView;
    }

    public void setRowAdapter(Integer position, View rowView) {
        TextView buildingName = (TextView)rowView.findViewById(R.id.buildingName);
        TextView mineralsCost = (TextView)rowView.findViewById(R.id.mineralCost);
        TextView gasCost = (TextView)rowView.findViewById(R.id.gasCost);
        TextView buildingEffect = (TextView)rowView.findViewById(R.id.buildingEffect);
        TextView timeToBuild = (TextView)rowView.findViewById(R.id.timeToBuild);
        TextView buildingLevel = (TextView)rowView.findViewById(R.id.buldingLevel);
        ImageView imageBuilding = (ImageView)rowView.findViewById(R.id.buildingImage);
        final Button levelUp = (Button)rowView.findViewById(R.id.levelUp);


        Building b = objects.get(position);
        buildingName.setText(b.getName());
        buildingEffect.setText(context.getString(R.string.buildingEffect, b.getEffect()));
        buildingLevel.setText(b.getLevel().toString());

        Glide
                .with(context)
                .load(b.getImageUrl())
                .into(imageBuilding);


        final Double mineralsNeed;
        final Double gasNeed;
        Integer timeNeed;
        if(b.getLevel() > 0) {
            mineralsNeed = b.getMineralCostByLevel() * b.getLevel();
            gasNeed = b.getGasCostByLevel() * b.getLevel();
            timeNeed = b.getTimeToBuildByLevel() * b.getLevel();
        } else {
            mineralsNeed = b.getGasCostLevel0();
            gasNeed = b.getGasCostLevel0();
            timeNeed = b.getTimeToBuildLevel0();
        }
        mineralsCost.setText(context.getString(R.string.mineralsCost, mineralsNeed.intValue()));
        gasCost.setText(context.getString(R.string.gasCost, gasNeed.intValue()));
        timeToBuild.setText(context.getString(R.string.timeToBuild, timeNeed));


        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        final Call<User> currentUser = osmService.getCurrentUser(settings.getString("userToken", ""));
        currentUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Double userGas = response.body().getGas().doubleValue();
                Double userMinerals = response.body().getMinerals().doubleValue();

                if(mineralsNeed > userMinerals && gasNeed > userGas) {
                    levelUp.setCursorVisible(false);
                    levelUp.setBackgroundColor(Color.parseColor("#BA0000"));
                } else {
                    levelUp.setCursorVisible(true);
                    levelUp.setBackgroundColor(Color.parseColor("#57BA00"));
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("An error occurred : ", t.getMessage());
            }
        });
    }


}
