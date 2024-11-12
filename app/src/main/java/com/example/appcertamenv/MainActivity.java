package com.example.appcertamenv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText peso, estatura;
    TextView caja, resultados;
    Spinner sp;
    ArrayList<String> items;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peso = findViewById(R.id.txtPeso);
        estatura = findViewById(R.id.txtEstatura);
        sp = findViewById(R.id.spinnerrr);
        caja = findViewById(R.id.txtCaja);
        resultados = findViewById(R.id.txtImc);

        items = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        sp.setAdapter(adapter);
        agregarItemsSpinner();


    }

    public void agregarItemsSpinner(){
        items.add("Seleccione: ");
        items.add("Femenino");
        items.add("Masculino");
        //Guardamos los cambios en el Spinner
        adapter.notifyDataSetChanged();
    }
    public void obtenerImc(View view){
        String txtPeso = peso.getText().toString();
        String txtEstatura = estatura.getText().toString();
        
        if(!txtPeso.isEmpty() && !txtEstatura.isEmpty() && !items.isEmpty()){ 
            double pesoInt = Double.parseDouble(txtPeso);
            double estaturaInt = Double.parseDouble(txtEstatura);
            
            double resultado = (pesoInt / (estaturaInt * estaturaInt));

            String resultado2 = String.valueOf(resultado);
            String subResultado = resultado2.substring(0,5);

            //Parseo del subResultado para comparar
            int subResultadoInt = Integer.parseInt(subResultado);

            if(subResultadoInt < 18.5){

                Toast.makeText(this, "Bajo peso", Toast.LENGTH_SHORT).show();
                resultados.setText(String.valueOf(resultado));
                caja.setText("1) Come con más frecuencia. Empieza poco a poco a hacer 5 a 6 comidas mas pequeñas durante el día\n"
                            + "2) Elije alimentos con muchos nutrientes\n"
                            + "3) Agrega aderezos\n"
                            + "4) Prueba licuados y batidos de fruta\n"
                            + "5) Controla que bebes y cuando\n"
                            + "6) Haz ejercicio.");

            } else if (subResultadoInt > 18.5 && subResultadoInt < 24.9) {
                Toast.makeText(this, "Peso adecuado", Toast.LENGTH_SHORT).show();
            }
            
        }
    }
}