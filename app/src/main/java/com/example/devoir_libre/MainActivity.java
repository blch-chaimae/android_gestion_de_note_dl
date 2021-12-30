package com.example.devoir_libre;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.devoir_libre.affichage.affichage;
import com.example.devoir_libre.R;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listItems;
    Spinner sp;
    TextView note , moyenne;
    List<affichage> lr = new ArrayList<affichage>();
    Integer nbr_module = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listItems = findViewById(R.id.listID);
        sp = findViewById(R.id.spinner2);
        note = findViewById(R.id.noteid);
        moyenne = findViewById(R.id.moyenneid);

    }
    public void getSpinnerValue(View v){
        Boolean itemAlreadyExists;
        String module = sp.getSelectedItem().toString();
        String n = note.getText().toString();
        Float note_float = Float.parseFloat(n);
        affichage r = new affichage(module , note_float);

        if( lr.size() != 0) {
            //on verifie que le module deja exist
            itemAlreadyExists = checkIfItemAlreadyExists(lr , module);
            if( !itemAlreadyExists){
            //ajoute au liste
                lr.add(r);
                updateListView(lr);
            }else{
            //une alert
                Toast toast = Toast.makeText(MainActivity.this , "choisir d'autre module" ,Toast.LENGTH_SHORT );
                toast.show();
            }
        }else{
            lr.add(r);
            updateListView(lr);
        }
    }
    //verifie si un module exist dans la list des resultat
    private Boolean checkIfItemAlreadyExists(List<affichage> lr, String module) {
        for (int i = 0; i < lr.size() ; i++){
            if( lr.get(i).toString().contains(module) ){
                return true;
            }
        }
        return false;
    }

    private void updateListView(List<affichage> lr) {
        ArrayList arrayMemory = new ArrayList();
        float somme = 0;

        for (int i = 0; i < lr.size() ; i++){
            arrayMemory.add(
                    lr.get(i).getModule() + " " + lr.get(i).getNote().toString()
            );
            somme += lr.get(i).getNote();
        }
        ArrayAdapter ad = new ArrayAdapter(this , android.R.layout.simple_list_item_1 ,arrayMemory );
        listItems.setAdapter(ad);

        //calcule la moyenne
        if (lr.size() == nbr_module){
            float moy = somme/nbr_module;
            moyenne.setText(Float.toString(moy));
        }
    }
}