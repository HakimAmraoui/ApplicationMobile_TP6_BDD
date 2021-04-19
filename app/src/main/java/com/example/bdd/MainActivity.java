package com.example.bdd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MonRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public PlaneteDao planetDao;
    public List<Planete> planetes;

    private FloatingActionButton floatingActionButton;

    final String PREFS_NAME = "preferences_file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "planetsDB").build();

        planetDao = db.planetDao();

        loadData(planetDao);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddPlanetPopUp addPlanet = new AddPlanetPopUp();
                addPlanet.setPlaneteDao(planetDao);
                addPlanet.show(getSupportFragmentManager(), "AddPlanetPopUp");

            }
        });

    }

    private void loadData(PlaneteDao planeteDao) {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        new Thread(new Runnable() {
            @Override
            public void run() {

                if (settings.getBoolean("is_data_loaded", true)) {
                    initData(planeteDao);
                    settings.edit().putBoolean("is_data_loaded", false).commit();
                }


                planetes = planeteDao.getAll();

                mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(getApplicationContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mAdapter = new MonRecyclerViewAdapter(getApplicationContext(), planetes);
                mRecyclerView.setAdapter(mAdapter);

            }
        }).start();

    }

    private void initData(PlaneteDao planeteDao) {

        ArrayList<Planete> planetes = new ArrayList<>();

        planetes.add(new Planete(1,"Mercure",4900));
        planetes.add(new Planete(2,"Venus",12000));
        planetes.add(new Planete(3,"Terre",12800));
        planetes.add(new Planete(4,"Mars",6800));
        planetes.add(new Planete(5,"Jupiter",144000));
        planetes.add(new Planete(6,"Saturne",120000));
        planetes.add(new Planete(7,"Uranus",52000));
        planetes.add(new Planete(8,"Neptune",50000));
        planetes.add(new Planete(9,"Pluton",2300));

        for (int index = 0; index < planetes.size(); index++) {
            Planete planete = planetes.get(index);
            planeteDao.insert(planete);
        }
    }
}