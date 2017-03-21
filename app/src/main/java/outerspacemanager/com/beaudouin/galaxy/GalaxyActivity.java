package outerspacemanager.com.beaudouin.galaxy;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import outerspacemanager.com.beaudouin.ProgressDialogUtil;
import outerspacemanager.com.beaudouin.R;
import outerspacemanager.com.beaudouin.models.User;
import outerspacemanager.com.beaudouin.models.Users;
import outerspacemanager.com.beaudouin.services.OSMService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalaxyActivity extends AppCompatActivity {
    private ListView lvUsers;

    private OSMService osmService = OSMService.retrofit.create(OSMService.class);
    private static final String PREFS_NAME = "PreferencesFile";
    private ProgressDialogUtil progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galaxy);

        lvUsers = (ListView)findViewById(R.id.lvUsers);

        progressDialog = new ProgressDialogUtil(this);
        progressDialog.launch();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        Call<Users> usersCall = osmService.getUsers(settings.getString("userToken", ""));
        usersCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                progressDialog.stop();
                lvUsers.setAdapter(new GalaxyAdapter(getApplicationContext(), response.body().getUsers()));
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Log.e("An error occurred", t.getMessage());
            }
        });

    }
}
