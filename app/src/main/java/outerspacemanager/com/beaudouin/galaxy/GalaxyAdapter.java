package outerspacemanager.com.beaudouin.galaxy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import outerspacemanager.com.beaudouin.R;
import outerspacemanager.com.beaudouin.models.User;
import outerspacemanager.com.beaudouin.models.Users;

public class GalaxyAdapter extends ArrayAdapter<User> {

    private final Context context;
    private final ArrayList<User> usersList;

    private TextView rank;
    private TextView userName;
    private TextView userPoints;

    public GalaxyAdapter(@NonNull Context context, @NonNull ArrayList<User> objects) {
        super(context, R.layout.galaxy_adapter, objects);
        this.context = context;
        this.usersList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.galaxy_adapter, parent, false);

        rank = (TextView)rowView.findViewById(R.id.rank);
        userName = (TextView)rowView.findViewById(R.id.userName);
        userPoints = (TextView)rowView.findViewById(R.id.userPoints);

        final User user = usersList.get(position);
        Integer userRank = position + 1;
        rank.setText(userRank.toString());
        userName.setText(user.getUsername());
        userPoints.setText(user.getPoints().toString());

        return rowView;
    }
}
