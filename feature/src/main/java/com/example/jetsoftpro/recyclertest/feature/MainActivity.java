package com.example.jetsoftpro.recyclertest.feature;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jetsoftpro.recyclertest.feature.Models.Person;
import com.example.jetsoftpro.recyclertest.feature.Utils.NetworksUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.ListItemClickListener {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        LinearLayoutManager manager = new LinearLayoutManager(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        recyclerAdapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(recyclerAdapter);

        new getPersonsTask().execute();
    }

    public class getPersonsTask extends AsyncTask<Void, Void, Person[]>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Person[] doInBackground(Void... strings) {

            URL personRequestURL = NetworksUtils.buildPersonUrl();

            try {
                String jsonPersonsResponse = NetworksUtils
                        .getPersonsRequest(personRequestURL);

                if(jsonPersonsResponse != null){
                    ObjectMapper mapper = new ObjectMapper();

                    Person[] persons = mapper.readValue(jsonPersonsResponse, Person[].class);

                    return persons;
                }
                else{
                    return null;
                }

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Person[] personData){
            progressBar.setVisibility(View.INVISIBLE);
            if (personData != null) {
                recyclerAdapter.setPersonData(personData);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnClick(Person selectedPerson) {
        Toast.makeText(this, new StringBuilder().append(selectedPerson.getFirstName()).append(" ").append(selectedPerson.getLastName()).toString(), Toast.LENGTH_SHORT).show();
    }
}
