package com.example.olakease.reciclatuentrada;
import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.olakease.reciclatuentrada.library.Httppostaux;

public class Actividad_Fragments extends Activity implements NavigationDrawerCallbacks {
    // Button btnLoginDialog;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;
    EditText txtUsername;
    EditText txtPassword;
    private ProgressDialog pDialog;
    Httppostaux post;
    String URL_connect = "http://proyectositi.com/recicladora/conexiones/droidlogin/acces.php";//ruta en donde estan nuestros archivos
    public static   String passw,usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         usuario = getIntent().getStringExtra("usuario");
        setContentView(R.layout.activity_main);
        post = new Httppostaux();
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        //setSupportActionBar(mToolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        // populate the navigation drawer
        mNavigationDrawerFragment.setUserData("", "johndoe@doe.com", BitmapFactory.decodeResource(getResources(),
                R.drawable.avatar));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        //Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();
        SelectItem(position);
    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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
public static Intent intent;
    public static int cont=1;

    public void SelectItem(int possition) {

        Fragment fragment = null;
        Bundle args = new Bundle();
        switch (possition) {
            case 0:

                fragment = new FragmentFour();

                break;
            case 1:
                fragment = new FragmentTwo();
                break;
            case 2:
                intent= new Intent(Actividad_Fragments.this, Main_bluetooth.class);
                intent.putExtra("usuario",usuario);
                cont++;
                break;
            case 3:

                fragment = new FragmentThree();

                break;
            case 4:
                fragment = new FragmentTwo();

                break;

            default:
                break;
        }
if(cont==1){
    fragment.setArguments(args);
    FragmentManager frgManager = getFragmentManager();
    frgManager.beginTransaction().replace(R.id.container, fragment)
            .commit();

}else{
    cont--;
    startActivity(intent);

}



        //  mDrawerLayout.closeDrawer(mDrawerList);

    }
}