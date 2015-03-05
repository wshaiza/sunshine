package app.com.example.wungmathing.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;



public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
      //  Log.d("tag","main activy menu");
        if(item.getItemId() == R.id.action_show_map) {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            String location = sharedPref.getString(getString(R.string.pref_location_key),
                    getString(R.string.pref_location_default));
            Log.d("tag","the location is: "+location);
            Uri loc = Uri.parse("geo: 0,0?").buildUpon().appendQueryParameter("q",location).build();
            //     Intent intent = new Intent(Intent.ACTION_VIEW,loc);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(loc);

            if(intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
            else {
                Log.d("tag","The specified location "+location+" isn't found");
            }

        }



        return super.onOptionsItemSelected(item);
    }


}
