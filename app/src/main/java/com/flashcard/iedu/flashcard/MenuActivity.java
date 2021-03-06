package com.flashcard.iedu.flashcard;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.iedu.flashcard.dao.domain.WordBook;
import edu.iedu.flashcard.service.WordBookService;


/**
 * VIEW_4
 */

public class MenuActivity extends AppCompatActivity {
    String actionBarTitle = "WordBook List";

    Context context;
    ListView lv;
    List<WordBook> wordbookList;
    MenuAdapter adapter;
    Integer userId;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(actionBarTitle);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.ic_logo);
        getSupportActionBar().setIcon(R.drawable.logo_h2); //shoudlbe 24 or 32 dp


        getSupportActionBar().setDisplayUseLogoEnabled(false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        context = this;

        wordbookList = new ArrayList<>();
        //WordBookService.getWordBookList(1);



        SharedPreferences pref = getApplicationContext().getSharedPreferences("IEDUPref", 0); // 0 - for private mode
        userId = pref.getInt("USER_ID", -1);
        System.out.println("MENUACTIVITY_USERID:"+userId);

        adapter = new MenuAdapter(this, wordbookList, userId);
        lv = (ListView) findViewById(R.id.ListView);
        lv.setAdapter(adapter);

        getWordBooks(userId);

    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWordBooks(userId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actionbar_options, menu);

//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
//        // Assumes current activity is the searchable activity
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
//
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * On selecting action bar icons
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_search:
                // search action
                Intent searchActivity = new Intent(context, SearchActivity.class);
                context.startActivity(searchActivity);
                return true;
            case R.id.action_add:
                Intent wordbookEditActivity = new Intent(context, WordBookEditActivity.class);
                context.startActivity(wordbookEditActivity);
                return true;
            case R.id.action_setting:
                // location found

                Intent settingActivity = new Intent(context, SettingActivity.class);
                context.startActivity(settingActivity);
                return true;
            case R.id.action_logout:

                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Log out")
                        .setMessage("Is that okay to logout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences pref = getApplicationContext().getSharedPreferences("IEDUPref", 0); // 0 - for private mode
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putInt("USER_ID", -1);
                                editor.commit();

                                Intent loginActivity = new Intent(context, LoginActivity.class);
                                context.startActivity(loginActivity);
                                //Stop the activity
                                //YourClass.this.finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();



                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void sendMessage (View view) {
        TextView viewMain =(TextView) view.findViewById(R.id.textViewMain);
        Toast.makeText(context, "You Clicked latest ", Toast.LENGTH_LONG).show();
    }

    public void sendMessage1 (View view) {
        TextView viewMain =(TextView) view.findViewById(R.id.textViewMain);
        Toast.makeText(context, "You clicked set", Toast.LENGTH_LONG).show();
    }
    public void sendMessage2 (View view) {
        TextView viewMain = (TextView) view.findViewById(R.id.textViewMain);
        Toast.makeText( context, "You clicked class", Toast.LENGTH_LONG).show();
    }
    public void getWordBooks (int wordBookId) {
        Connection conn = new Connection ();
        conn.doInBackground(wordBookId);
    }
    private class Connection extends AsyncTask {

        protected Object doInBackground(Object... arg0) {
            int userId = (Integer)arg0[0];
            List<WordBook> test = WordBookService.getWordBooks(userId);

            wordbookList.clear();
            wordbookList.addAll(test);
            adapter.notifyDataSetChanged();
            return null;
        }
    }

}
