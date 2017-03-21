package outerspacemanager.com.beaudouin.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by nico on 21/03/17.
 */

public class Searches implements Serializable {
    private ArrayList<Search> searches;
    private Integer size;

    public Searches() {
        this.searches = new ArrayList<>();
        this.size = searches.size();
    }

    public ArrayList<Search> getSearches() { return this.searches; }
    public Integer getSize() { return this.size; }

}
