package outerspacemanager.com.beaudouin.reports;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import outerspacemanager.com.beaudouin.R;
import outerspacemanager.com.beaudouin.models.Report;

public class ReportsAdapter extends RecyclerView.Adapter<ReportsAdapter.ReportsViewHolder> {

    private final ArrayList<Report> reports;
    private final Context context;

    private OnReportsListener onReportsListener;

    public ReportsAdapter(ArrayList<Report> reports, Context context, OnReportsListener listener) {
        this.reports = reports;
        this.context = context;
        this.onReportsListener = listener;
    }

    @Override
    public ReportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.reports_list_adapter, parent, false);
        ReportsViewHolder viewHolder = new ReportsViewHolder(rowView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReportsAdapter.ReportsViewHolder holder, int position) {

        final Report report = reports.get(position);

        if(report.getType().equals("attacked")) {
            holder.reportName.setText(context.getString(R.string.reportsNameAttacked, report.getFrom()));
            holder.reportName.setTextColor(Color.parseColor("#E11D1D"));
            holder.reportName.setBackgroundColor(Color.parseColor("#6D1717"));
        } else {
            holder.reportName.setText(context.getString(R.string.reportsNameAttacker, report.getFrom()));
            holder.reportName.setTextColor(Color.parseColor("#7FE11D"));
            holder.reportName.setBackgroundColor(Color.parseColor("#447612"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onReportsListener != null) {
                    onReportsListener.onReportListener(report);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    public class ReportsViewHolder extends RecyclerView.ViewHolder {
        private TextView reportName;

        public ReportsViewHolder(View itemView) {
            super(itemView);

            this.reportName = (TextView)itemView.findViewById(R.id.reportName);
        }
    }
}
