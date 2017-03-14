package outerspacemanager.com.beaudouin;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by nico on 14/03/17.
 */

public class ProgressDialogUtil {
    private ProgressDialog progress;

    public ProgressDialogUtil(Context context) {
        progress = new ProgressDialog(context);
        progress.setTitle("Chargement en cours...");
        progress.setMessage("Veuillez patienter pendant le chargement");
        progress.setCancelable(false);
    }

    public void launch() {
        progress.show();
    }

    public void stop() { progress.dismiss(); }


}
