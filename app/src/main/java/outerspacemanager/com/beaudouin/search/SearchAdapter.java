package outerspacemanager.com.beaudouin.search;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import outerspacemanager.com.beaudouin.R;
import outerspacemanager.com.beaudouin.models.Search;
import outerspacemanager.com.beaudouin.models.User;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private final ArrayList<Search> searches;
    private final Context context;
    private final User currentUser;

    private OnSearchListener onSearchListener;

    public SearchAdapter(ArrayList<Search> searches, Context context, User currentUser) {
        this.searches = searches;
        this.context = context;
        this.currentUser = currentUser;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.search_adapter, parent, false);
        SearchViewHolder viewHolder = new SearchViewHolder(rowView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchAdapter.SearchViewHolder holder, int position) {

        // load data inside different item in the layout
        Float timeToBuild;
        Float gasNeeded;
        Float mineralsNeeded;

        final Search search = searches.get(position);
        holder.searchName.setText(search.getName());
        holder.searchEffect.setText(context.getString(R.string.searchEffect, search.getEffect()));
        if(search.getLevel() != null) {
            holder.searchLevel.setText(search.getLevel().toString());
        }

        if(search.getLevel() == 0) {
            timeToBuild = search.getTimeToBuildLevel0();
            gasNeeded = search.getGasCostLevel0();
            mineralsNeeded = search.getMineralCostLevel0();
        } else {
            timeToBuild = search.getTimeToBuildLevel0() + (search.getAmountOfEffectByLevel() * search.getLevel());
            gasNeeded = search.getGasCostLevel0() + (search.getGasCostByLevel() * search.getLevel());
            mineralsNeeded = search.getGasCostLevel0() + (search.getMineralCostByLevel() * search.getLevel());
        }
        holder.searchTimeToBuild.setText(context.getString(R.string.searchTimeTo, timeToBuild.toString()));

        if((currentUser.getMinerals() < mineralsNeeded || currentUser.getGas() < gasNeeded )
                && !search.isBuilding()) {
            holder.searchIsBuild.setText("Recherche impossible pour le moment...");
            holder.searchIsBuild.setBackgroundColor(Color.parseColor("#E11D1D"));
        } else if(search.isBuilding()) {
            holder.searchIsBuild.setText("La recherche est en cours...");
            holder.searchIsBuild.setBackgroundColor(Color.parseColor("#E1A01D"));
        } else {
            holder.searchIsBuild.setText("Lancer la recherche !");
            holder.searchIsBuild.setBackgroundColor(Color.parseColor("#7FE11D"));
        }

        // add search listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onSearchListener != null) {
                    onSearchListener.onSearchListener(search);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return searches.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        private TextView searchName;
        private TextView searchEffect;
        private TextView searchLevel;
        private TextView searchTimeToBuild;
        private TextView searchIsBuild;

        public SearchViewHolder(View itemView) {
            super(itemView);

            this.searchName = (TextView)itemView.findViewById(R.id.searcheName);
            this.searchEffect = (TextView)itemView.findViewById(R.id.searchEffect);
            this.searchLevel = (TextView)itemView.findViewById(R.id.searchLevel);
            this.searchTimeToBuild = (TextView)itemView.findViewById(R.id.timeToBuildSearch);
            this.searchIsBuild = (TextView)itemView.findViewById(R.id.searchIsBuilding);

        }
    }

    public void setOnSearchListner(OnSearchListener listener) {
        this.onSearchListener = listener;
    }
}
