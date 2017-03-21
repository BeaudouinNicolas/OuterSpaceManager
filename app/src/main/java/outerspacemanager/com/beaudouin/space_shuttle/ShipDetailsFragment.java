package outerspacemanager.com.beaudouin.space_shuttle;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import outerspacemanager.com.beaudouin.ProgressDialogUtil;
import outerspacemanager.com.beaudouin.R;
import outerspacemanager.com.beaudouin.models.Ship;
import outerspacemanager.com.beaudouin.services.OSMService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShipDetailsFragment extends Fragment {

    OSMService osmService = OSMService.retrofit.create(OSMService.class);
    private static final String PREFS_NAME = "PreferencesFile";
    private ProgressDialogUtil progressDialog;

    private Integer shipId;
    private TextView shipName;
    private TextView shipGas;
    private TextView shipMinerals;
    private TextView shipMinAttack;
    private TextView shipMaxAttack;
    private TextView shipShield;
    private TextView shipLife;
    private TextView shipSpeed;
    private TextView shipTime;

    private SeekBar seekBarShip;
    private Button createShip;

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

        seekBarShip = (SeekBar)v.findViewById(R.id.seekBarShip);
        createShip = (Button)v.findViewById(R.id.createShip);

        details_container = (ConstraintLayout)v.findViewById(R.id.details_container);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        seekBarShip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                createShip.setText(getString(R.string.createShip, progress, shipName.getText()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method
            }
        });

        createShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialogUtil(getActivity());
                progressDialog.launch();

                Ship newShips = new Ship();
                newShips.setAmount(seekBarShip.getProgress());

                SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
                Call<Ship> createShipCall = osmService.postShip(
                        settings.getString("userToken", ""), shipId,
                        newShips);
                createShipCall.enqueue(new Callback<Ship>() {
                    @Override
                    public void onResponse(Call<Ship> call, Response<Ship> response) {
                        progressDialog.stop();
                        if(response.code() == 200) {
                            Toast success = Toast.makeText(getActivity(), "Vaisseaux en construction...", Toast.LENGTH_LONG);
                            success.show();
                            getActivity().finish();
                            startActivity(getActivity().getIntent());
                        } else {
                            Toast error = Toast.makeText(getActivity(), "Une erreur est survenue...", Toast.LENGTH_LONG);
                            error.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Ship> call, Throwable t) {
                        progressDialog.stop();
                        Toast error = Toast.makeText(getActivity(), "Une erreur est survenue...", Toast.LENGTH_LONG);
                        error.show();
                    }
                });
            }
        });


    }

    public void fillShipDetail(Ship ship, Float userMinerals, Float userGas) {
        if(details_container.getVisibility() != View.VISIBLE) {
            details_container.setVisibility(View.VISIBLE);
        }

        shipId = ship.getShipId();
        shipName.setText(ship.getName());
        shipGas.setText(ship.getGasCost().toString());
        shipMinerals.setText(ship.getMineralCost().toString());
        shipMinAttack.setText(ship.getMinAttack().toString());
        shipMaxAttack.setText(ship.getMaxAttack().toString());
        shipShield.setText(ship.getShield().toString());
        shipLife.setText(ship.getLife().toString());
        shipSpeed.setText(ship.getSpeed().toString());
        shipTime.setText(ship.getTimeToBuild().toString());

        Integer nbShipWithMinerals = Math.round(userMinerals / ship.getMineralCost()) - 1;
        Integer nbShipWithGas = Math.round(userGas / ship.getGasCost()) - 1;
        Integer nbShip = 0;
        if(ship.getMineralCost() == 0) {
            nbShip = nbShipWithGas;
        } else if(ship.getGasCost() == 0) {
            nbShip = nbShipWithMinerals;
        } else {
            if(nbShipWithGas <= nbShipWithMinerals){
                nbShip = nbShipWithGas;
            } else {
                nbShip = nbShipWithMinerals;
            }
        }
        seekBarShip.setMax(nbShip);
        seekBarShip.setProgress(nbShip);
        createShip.setText(getString(R.string.createShip, nbShip, shipName.getText()));

    }
}
