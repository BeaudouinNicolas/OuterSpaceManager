package outerspacemanager.com.beaudouin;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.LayoutRes;
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
    private Float userGas;
    private Float userMinerals;

    private OSMService osmService = OSMService.retrofit.create(OSMService.class);
    private static final String PREFS_NAME = "PreferencesFile";

    public BuildingsAdapter(@NonNull Context context, @NonNull ArrayList<Building> objects) {
        super(context, R.layout.building_adapter, objects);
        this.context = context;
        this.objects = objects;

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        final Call<User> currentUser = osmService.getCurrentUser(settings.getString("userToken", ""));
        currentUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                userGas = response.body().getGas();
                userMinerals = response.body().getMinerals();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("An error occurred : ", t.getMessage());
            }
        });
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.building_adapter, parent, false);

        setRowAdapter(position, rowView);

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
        Button levelUp = (Button)rowView.findViewById(R.id.levelUp);

        Building b = objects.get(position);
        buildingName.setText(b.getName());
        buildingEffect.setText(context.getString(R.string.buildingEffect, b.getEffect()));
        buildingLevel.setText(b.getLevel().toString());
        Glide
                .with(context)
                .load(b.getImageUrl())
                .into(imageBuilding);

        Float mineralsNeed;
        Float gasNeed;
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
        mineralsCost.setText(context.getString(R.string.mineralsCost, mineralsNeed));
        gasCost.setText(context.getString(R.string.gasCost, gasNeed));
        timeToBuild.setText(context.getString(R.string.timeToBuild, timeNeed));

        /*if(mineralsNeed > userMinerals && gasNeed > userGas) {
            levelUp.setCursorVisible(false);
        } else {
            levelUp.setCursorVisible(true);
        }*/
    }
}
