package outerspacemanager.com.beaudouin.space_shuttle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import outerspacemanager.com.beaudouin.R;
import outerspacemanager.com.beaudouin.models.Ship;

public class ShipDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_detail);
        Ship ship = (Ship)getIntent().getSerializableExtra("SHIP_SELECTED");

        ShipDetailsFragment shipDetailsFrag = (ShipDetailsFragment)getSupportFragmentManager()
                .findFragmentById(R.id.shipDetailsFrag);
        shipDetailsFrag.fillShipDetail(ship);
    }
}
