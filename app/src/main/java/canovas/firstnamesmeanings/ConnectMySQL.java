package canovas.firstnamesmeanings;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectMySQL extends AsyncTask<Void, Void, String> {


    private WeakReference<MainActivity> activityReference;

    // only retain a weak reference to the activity
    ConnectMySQL(MainActivity context) {
        activityReference = new WeakReference<>(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        // get a reference to the activity if it is still there
        MainActivity activity = activityReference.get();
        if (activity == null || activity.isFinishing()) return;

       /*  try {
            //loadIntoListView(s);
        } catch (JSONException e) {
            e.printStackTrace();
        } */
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL("INSERT_URL_HERE");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String json;
            while ((json = bufferedReader.readLine()) != null) {
                sb.append(json).append("\n");
            }
            return sb.toString().trim();
        } catch (Exception e) {
            return null;
        }
    }

}
