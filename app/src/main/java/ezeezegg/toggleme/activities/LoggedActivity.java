package ezeezegg.toggleme.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ezeezegg.toggleme.Interfaces.AsyncVolleyResponse;
import ezeezegg.toggleme.MainLogin;
import ezeezegg.toggleme.R;
import ezeezegg.toggleme.applications.VolleyApplication;
import ezeezegg.toggleme.constants.Urls;
import ezeezegg.toggleme.helpers.SharedPreferenceHelper;
import ezeezegg.toggleme.helpers.VolleyHelper;

public class LoggedActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AsyncVolleyResponse {
    private ViewFlipper mainViewFlipper;
    private EditText apiToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mainViewFlipper = (ViewFlipper) findViewById(R.id.mainViewFlipper);
        mainViewFlipper.setDisplayedChild(1);
        apiToken = (EditText) findViewById(R.id.api_token);
        apiToken.setText(SharedPreferenceHelper.getSharedPreferenceString(this, "api_token", "not found"));

        getEntriesToggle();
    }

    private void getEntriesToggle() {
        String start_date = "2016-03-10T15:42:46+02:00";
        String end_date = "2016-03-12T15:42:46+02:00";
        String urlFinal = Urls.urlEntriesToggl + "?start_date=" + start_date + "&end_date=" + end_date;
        urlFinal = "https://www.toggl.com/api/v8/time_entries?start_date=2016-03-10T15%3A42%3A46%2B02%3A00&end_date=2016-03-12T15%3A42%3A46%2B02%3A00";
        VolleyHelper volleyHelper = new VolleyHelper(this, urlFinal, this);
        volleyHelper.getEntriesToggle(SharedPreferenceHelper.getSharedPreferenceString(this, "api_token", "without_token"));
        //getEntriesToggle("7b132cea5922120fa67840d6fd576d89", urlFinal, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_settings:

                break;
            case R.id.action_logout:
                VolleyHelper volleyHelper = new VolleyHelper(this, Urls.urlLogoutToggl, this);
                volleyHelper.makeLogutVolley();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void AsyncVolleyFinish(String response) {
        SharedPreferenceHelper.setSharedPreferenceBoolean(this, "login", false);
        Intent myIntent = new Intent(LoggedActivity.this, MainLogin.class);
        LoggedActivity.this.startActivity(myIntent);

    }

    @Override
    public void AsyncVolleyError(String error) {

    }
}
