package outerspacemanager.com.beaudouin.space_shuttle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import outerspacemanager.com.beaudouin.R;
import outerspacemanager.com.beaudouin.models.Ship;

public class ShipDetailsFragment extends Fragment {

    private TextView shipName;
    private TextView shipGas;
    private TextView shipMinerals;
    private TextView shipMinAttack;
    private TextView shipMaxAttack;
    private TextView shipShield;
    private TextView shipLife;
    private TextView shipSpeed;
    private TextView shipTime;

    private ConstraintLayout details_container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ship_details, container);

        shipName = (TextView)v.findViewById(R.id.shipDetailName);
        shipGas = (TextView)v.findViewById(R.id.shipGas);
        shipMinerals = (TextView)v.findViewById(R.id.shipMinerals);
        shipMinAttack = (TextView)v.findViewById(R.id.minAttackDetailShip);
        shipMaxAttack = (TextView)v.findViewById(R.id.maxAttackDetailShip);
        shipShield = (TextView)v.findViewById(R.id.shipDetailShield);
        shipLife = (TextView)v.findViewById(R.id.shipDetailLife);
        shipSpeed = (TextView)v.findViewById(R.id.shipDetailSpeed);
        shipTime = (TextView)v.findViewById(R.id.shipDetailTime);

        details_container = (ConstraintLayout)v.findViewById(R.id.details_container);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void fillShipDetail(Ship ship) {
        if(details_container.getVisibility() != View.VISIBLE) {
            details_container.setVisibility(View.VISIBLE);
        }

        shipName.setText(ship.getName());
        shipGas.setText(ship.getGasCost().toString());
        shipMinerals.setText(ship.getMineralCost().toString());
        shipMinAttack.setText(ship.getMinAttack().toString());
        shipMaxAttack.setText(ship.getMaxAttack().toString());
        shipShield.setText(ship.getShield().toString());
        shipLife.setText(ship.getLife().toString());
        shipSpeed.setText(ship.getSpeed().toString());
        shipTime.setText(ship.getTimeToBuild().toString());
    }
}
