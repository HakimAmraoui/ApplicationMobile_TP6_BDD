package com.example.bdd;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddPlanetPopUp extends DialogFragment {

    EditText et_planetName, et_planetSize;
    Button buttonAddPlanet;
    PlaneteDao planeteDao;

    private static final String TAG = "MyActivity";


    public void setPlaneteDao(PlaneteDao planeteDao) {
        this.planeteDao = planeteDao;
    }

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_planet, container, false);

        et_planetName = view.findViewById(R.id.editTextNamePlanet);
        et_planetSize = view.findViewById(R.id.editTextSizePlanet);

        buttonAddPlanet = view.findViewById(R.id.button_add_planet);
        buttonAddPlanet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isValid = true;

                String name = et_planetName.getText().toString();
                if (!name.matches("[a-z A-Z]+")) {
                    Toast.makeText(getContext(), getString(R.string.name_invalid), Toast.LENGTH_SHORT).show();
                    isValid = false;
                }

                String size = et_planetSize.getText().toString();
                if (!size.matches("[0-9]+")) {
                    Toast.makeText(getContext(), getString(R.string.size_invalid), Toast.LENGTH_SHORT).show();
                    isValid = false;
                }

                if (isValid) {
//                    Log.v(TAG, "oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int uid = planeteDao.getAll().size() + 1;

                            Planete planete = new Planete(uid, et_planetName.getText().toString(), Integer.parseInt(et_planetSize.getText().toString()));
                            planeteDao.insert(planete);
//                            Log.v(TAG, "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                        }
                    }).start();

                }
            }
        });

        return view;
    }
}
