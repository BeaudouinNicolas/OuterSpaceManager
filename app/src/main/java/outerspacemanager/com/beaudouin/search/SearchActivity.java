package outerspacemanager.com.beaudouin.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import outerspacemanager.com.beaudouin.ProgressDialogUtil;
import outerspacemanager.com.beaudouin.R;
import outerspacemanager.com.beaudouin.models.Search;
import outerspacemanager.com.beaudouin.models.User;
import outerspacemanager.com.beaudouin.services.OSMService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    OSMService osmService = OSMService.retrofit.create(OSMService.class);
    private static final String PREFS_NAME = "PreferencesFile";
    private ProgressDialogUtil progressDialog;

    private RecyclerView rvSearches;
    private ArrayList<Search> searches;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final Context context = this;
        progressDialog = new ProgressDialogUtil(this);

        this.searches = (ArrayList<Search>)getIntent().getSerializableExtra("SEARCHES_LIST");
        this.currentUser = new User();
        this.currentUser.setMinerals(Float.parseFloat(getIntent().getStringExtra("USER_MINERALS")));
        this.currentUser.setGas(Float.parseFloat(getIntent().getStringExtra("USER_GAS")));

        final SearchAdapter searchAdapter = new SearchAdapter(searches, SearchActivity.this, currentUser);
        // make listener on the item click
        searchAdapter.setOnSearchListner(new OnSearchListener() {
            @Override
            public void onSearchListener(Search newSearch) {
                // Do Retrofit stuff for making research
                progressDialog.launch();

                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                Call<Search> createSearchCall =
                        osmService.postSearch(newSearch.getSearchId(), settings.getString("userToken", ""));
                createSearchCall.enqueue(new Callback<Search>() {
                    @Override
                    public void onResponse(Call<Search> call, Response<Search> response) {
                        progressDialog.stop();
                        if(response.code() == 200) {
                            Toast message = Toast.makeText(context, "Recherche lancé !", Toast.LENGTH_LONG);
                            message.show();
                            rvSearches.invalidate();
                        } else {
                            Toast message = Toast.makeText(context, "Une erreur est survenue...", Toast.LENGTH_LONG);
                            message.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Search> call, Throwable t) {
                        progressDialog.stop();
                        Toast error = Toast.makeText(context, "Une erreur est survenue...", Toast.LENGTH_LONG);
                        error.show();
                    }
                });
            }
        });

        this.rvSearches = (RecyclerView)findViewById(R.id.lvSearches);
        this.rvSearches.setLayoutManager(new LinearLayoutManager(this));

        this.rvSearches.setAdapter(searchAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
