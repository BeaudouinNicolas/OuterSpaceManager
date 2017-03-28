package outerspacemanager.com.beaudouin.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by nico on 24/03/17.
 */

public class Reports implements Serializable {
    private ArrayList<Report> reports;
    private Integer size;

    public Reports() { }

    public ArrayList<Report> getReports() { return this.reports; }
    public Integer getSize() { return this.size; }


}
