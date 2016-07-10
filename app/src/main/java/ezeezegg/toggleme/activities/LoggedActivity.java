package ezeezegg.toggleme.activities;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.json.JSONException;
import org.json.JSONObject;

import ezeezegg.toggleme.Interfaces.AsyncVolleyResponse;
import ezeezegg.toggleme.MainLogin;
import ezeezegg.toggleme.R;
import ezeezegg.toggleme.constants.Urls;
import ezeezegg.toggleme.helpers.SharedPreferenceHelper;
import ezeezegg.toggleme.helpers.VolleyHelper;

public class LoggedActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AsyncVolleyResponse{
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
        apiToken =(EditText) findViewById(R.id.api_token);
        apiToken.setText(SharedPreferenceHelper.getSharedPreferenceString(this,"api_token","not found"));
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
