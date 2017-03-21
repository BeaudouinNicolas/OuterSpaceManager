package outerspacemanager.com.beaudouin.space_shuttle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import outerspacemanager.com.beaudouin.R;
import outerspacemanager.com.beaudouin.models.Ship;

public class ShipsListAdapter extends ArrayAdapter<Ship> {

    private Context context;
    private ArrayList<Ship> ships;

    public ShipsListAdapter(@NonNull Context context, @NonNull ArrayList<Ship> objects) {
        super(context, R.layout.ships_list_adapter, objects);
        this.context = context;
        this.ships = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflate = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflate.inflate(R.layout.ships_list_adapter, parent, false);

        TextView shipName = (TextView)rowView.findViewById(R.id.shipName);

        Ship s = ships.get(position);
        shipName.setText(s.getName());

        return rowView;
    }


}
