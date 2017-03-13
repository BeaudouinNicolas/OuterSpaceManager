package outerspacemanager.com.beaudouin;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import outerspacemanager.com.beaudouin.models.Building;
import outerspacemanager.com.beaudouin.models.Buildings;

/**
 * Created by nico on 07/03/17.
 */

public class BuildingsAdapter extends ArrayAdapter<Building> {

    private final Context context;
    private final ArrayList<Building> objects;

    public BuildingsAdapter(@NonNull Context context, @NonNull ArrayList<Building> objects) {
        super(context, R.layout.building_adapter, objects);
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.building_adapter, parent, false);

        TextView buildingName = (TextView)rowView.findViewById(R.id.buildingName);
        TextView mineralsCost = (TextView)rowView.findViewById(R.id.mineralCost);
        TextView gasCost = (TextView)rowView.findViewById(R.id.gasCost);
        TextView buildingEffect = (TextView)rowView.findViewById(R.id.buildingEffect);
        TextView timeToBuild = (TextView)rowView.findViewById(R.id.timeToBuild);

        Building b = objects.get(position);
        buildingName.setText(b.getName());
        buildingEffect.setText("Effet : " + b.getEffect());
        if(b.getLevel() > 0) {
            Integer mineralsNeed = b.getMineralCostByLevel() * b.getLevel();
            Integer gasNeed = b.getGasCostByLevel() * b.getLevel();
            Integer timeNeed = b.getTimeToBuildByLevel() * b.getLevel();

            mineralsCost.setText(mineralsNeed.toString());
            gasCost.setText(gasNeed.toString());
            timeToBuild.setText("Durée : " + timeNeed.toString());
        } else {
            mineralsCost.setText(b.getGasCostLevel0().toString());
            gasCost.setText(b.getGasCostLevel0().toString());
            timeToBuild.setText("Durée : " + b.getTimeToBuildLevel0().toString());
        }

        return rowView;
    }
}
