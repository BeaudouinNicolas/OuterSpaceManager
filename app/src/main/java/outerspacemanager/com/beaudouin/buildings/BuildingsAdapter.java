package outerspacemanager.com.beaudouin.buildings;

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

import org.w3c.dom.Text;

import java.util.ArrayList;

import outerspacemanager.com.beaudouin.ProgressDialogUtil;
import outerspacemanager.com.beaudouin.R;
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
    private final User currentUser;

    public BuildingsAdapter(@NonNull Context context, @NonNull ArrayList<Building> objects, @NonNull User user) {
        super(context, R.layout.building_adapter, objects);
        this.context = context;
        this.objects = objects;
        this.currentUser = user;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.building_adapter, parent, false);

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
        TextView levelUp = (TextView)rowView.findViewById(R.id.levelUp);


        final Building b = objects.get(position);
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

        Double userGas = currentUser.getGas().doubleValue();
        Double userMinerals = currentUser.getMinerals().doubleValue();

        if((mineralsNeed > userMinerals || gasNeed > userGas) && !b.isBuilding()) {
            levelUp.setBackgroundColor(Color.parseColor("#BA0000"));
            levelUp.setText(context.getString(R.string.notEnough));
        } else if(b.isBuilding()) {
            levelUp.setBackgroundColor(Color.parseColor("#D98E1E"));
            levelUp.setText(context.getString(R.string.buildinIsBuilding));
        } else {
            levelUp.setText(context.getString(R.string.levelUpBuilding));
            levelUp.setBackgroundColor(Color.parseColor("#57BA00"));
            levelUp.setHighlightColor(Color.parseColor("#46D91E"));
        }
    }


}
