package outerspacemanager.com.beaudouin.space_shuttle;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

import outerspacemanager.com.beaudouin.R;
import outerspacemanager.com.beaudouin.models.Building;
import outerspacemanager.com.beaudouin.models.Ship;
import outerspacemanager.com.beaudouin.models.Ships;
import outerspacemanager.com.beaudouin.services.OSMService;

public class SpaceShuttleActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    OSMService osmService = OSMService.retrofit.create(OSMService.class);
    private static final String PREFS_NAME = "PreferencesFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_shuttle);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ShipListFragment shipListFrag = (ShipListFragment)getSupportFragmentManager()
                .findFragmentById(R.id.shipListFragment);
        ShipDetailsFragment shipDetailsFrag = (ShipDetailsFragment)getSupportFragmentManager()
                .findFragmentById(R.id.shipDetailsFragment);

        if(shipDetailsFrag == null || !shipDetailsFrag.isInLayout()){
            Intent i = new Intent(getApplicationContext(), ShipDetailActivity.class);
            i.putExtra("SHIP_SELECTED", shipListFrag.getShipAt(position));
            startActivity(i);
        } else {
            shipDetailsFrag.fillShipDetail(shipListFrag.getShipAt(position));
        }


    }
}
