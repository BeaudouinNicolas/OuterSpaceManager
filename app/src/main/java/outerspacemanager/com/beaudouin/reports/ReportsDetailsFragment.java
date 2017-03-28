package outerspacemanager.com.beaudouin.reports;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

import outerspacemanager.com.beaudouin.ProgressDialogUtil;
import outerspacemanager.com.beaudouin.R;
import outerspacemanager.com.beaudouin.models.Report;


public class ReportsDetailsFragment extends Fragment {

    private static final String PREFS_NAME = "PreferencesFile";
    private ProgressDialogUtil progressDialog;

    private TextView reportDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reports_details, container);

        this.reportDate = (TextView)v.findViewById(R.id.dateAttack);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void fillReportData(Report report) {

        String yolo = "yolo";

        DateTimeFormatter formatter = DateTimeFormat.forPattern("H:m:s - d/M/y");
        this.reportDate.setText(formatter.print(report.getDate()));

    }
}
