package outerspacemanager.com.beaudouin.reports;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import outerspacemanager.com.beaudouin.R;
import outerspacemanager.com.beaudouin.models.Report;

public class ReportsListFragment extends Fragment {
    private RecyclerView rvReports;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_reports_list, container, false);
        this.rvReports = (RecyclerView)v.findViewById(R.id.rvReports);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<Report> reports = (ArrayList<Report>)getActivity().getIntent()
                .getSerializableExtra("USER_REPORTS");

        this.rvReports.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.rvReports.setAdapter(new ReportsAdapter(reports, getActivity(), (ReportsActivity)getActivity()));
    }
}
