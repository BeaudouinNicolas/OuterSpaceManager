package outerspacemanager.com.beaudouin.reports;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import outerspacemanager.com.beaudouin.R;
import outerspacemanager.com.beaudouin.models.Report;

public class ReportsActivity extends AppCompatActivity implements OnReportsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
    }

    @Override
    public void onReportListener(Report reportSelected) {
        ReportsDetailsFragment reportsDetailsFragment = (ReportsDetailsFragment)getSupportFragmentManager()
                .findFragmentById(R.id.reportDetailsFragment);

        if(reportsDetailsFragment == null || reportsDetailsFragment.isInLayout()) {
            Log.d("intent", "need creation of view");
        } else {
            reportsDetailsFragment.fillReportData(reportSelected);
        }
    }
}
