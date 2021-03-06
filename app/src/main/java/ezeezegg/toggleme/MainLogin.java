package ezeezegg.toggleme;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ezeezegg.toggleme.Interfaces.AsyncVolleyResponse;
import ezeezegg.toggleme.activities.LoggedActivity;
import ezeezegg.toggleme.constants.Urls;
import ezeezegg.toggleme.helpers.SharedPreferenceHelper;
import ezeezegg.toggleme.helpers.VolleyHelper;

public class MainLogin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AsyncVolleyResponse {
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button buttonLogin;
    private ViewFlipper mainViewFlipper;

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
        if (SharedPreferenceHelper.getSharedPreferenceBoolean(this, "login", true)) {
            Intent myIntent = new Intent(MainLogin.this, LoggedActivity.class);
            MainLogin.this.startActivity(myIntent);
        } else {
            mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
            mPasswordView = (EditText) findViewById(R.id.password);

            buttonLogin = (Button) findViewById(R.id.email_sign_in_button);
            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    login();
                }
            });
            mainViewFlipper.setDisplayedChild(0);
        }
    }

    private void login() {
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        VolleyHelper volleyHelper = new VolleyHelper(this, Urls.urlLoginToggl, this);
        volleyHelper.makeLoginVolley(email, password);
    }

    @Override
    public void AsyncVolleyFinish(String response) {
        JSONObject parse = null;
        JSONObject data = null;
        try {
            parse = new JSONObject(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            String dataMain = parse.getString("data");
            data = new JSONObject(dataMain);
            SharedPreferenceHelper.setSharedPreferenceBoolean(this, "login", true);
            SharedPreferenceHelper.setSharedPreferenceString(this, "api_token", data.getString("api_token"));
            Intent myIntent = new Intent(MainLogin.this, LoggedActivity.class);
            MainLogin.this.startActivity(myIntent);
            Toast.makeText(this, "response: " + parse.getString("since"), Toast.LENGTH_SHORT ).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void AsyncVolleyError(String error) {
        Toast.makeText(this, "ERROR: " + error, Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
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
}
