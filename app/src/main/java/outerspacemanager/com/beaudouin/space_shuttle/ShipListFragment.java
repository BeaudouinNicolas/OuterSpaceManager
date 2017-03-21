package outerspacemanager.com.beaudouin.space_shuttle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import outerspacemanager.com.beaudouin.MainActivity;
import outerspacemanager.com.beaudouin.R;
import outerspacemanager.com.beaudouin.models.Ship;

public class ShipListFragment extends Fragment {
    private ListView lvShips;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ships_list, container, false);
        lvShips = (ListView)v.findViewById(R.id.shipsListView);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<Ship> shipList = (ArrayList<Ship>)getActivity().getIntent().getSerializableExtra("SHIP_LIST");

        lvShips.setAdapter(new ShipsListAdapter(getActivity(), shipList));
        lvShips.setOnItemClickListener((SpaceShuttleActivity)getActivity());

    }

    public Ship getShipAt(Integer position) {
        return (Ship)lvShips.getAdapter().getItem(position);
    }
}
