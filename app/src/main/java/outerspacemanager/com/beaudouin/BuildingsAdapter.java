package outerspacemanager.com.beaudouin;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import outerspacemanager.com.beaudouin.models.Building;
import outerspacemanager.com.beaudouin.models.Buildings;

/**
 * Created by nico on 07/03/17.
 */

public class BuildingsAdapter extends ArrayAdapter<Building> {
    public BuildingsAdapter(@NonNull Context context, @NonNull List<Building> objects) {
        super(context, R.layout.building_adapter, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
