package com.example.appcertamenv;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//Link que github
//https://github.com/davidCamposM/AppCertamen12-11-2024.git
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

        items.add("Seleccione: ");
        items.add("Femenino");
        items.add("Masculino");
        //Guardamos los cambios en el Spinner
        adapter.notifyDataSetChanged();





    }



    @SuppressLint("SetTextI18n")
    public void obtenerImc(View view) {
        String txtPeso = peso.getText().toString();
        String txtEstatura = estatura.getText().toString();

        if (!txtPeso.isEmpty() && !txtEstatura.isEmpty() && sp.equals("Seleccione:")) {
            double pesoInt = Double.parseDouble(txtPeso);
            double estaturaInt = Double.parseDouble(txtEstatura);

            double resultado = (pesoInt / (estaturaInt * estaturaInt));

            String resultado2 = String.valueOf(resultado);
            String subResultado = resultado2.substring(0, 5);

            //Parseo del subResultado para comparar
            double subResultadoDouble = Double.parseDouble(subResultado);

            if (subResultadoDouble < 18.05) {

                Toast.makeText(this, "Bajo peso", Toast.LENGTH_SHORT).show();
                limpiarCampos();
                resultados.setText("Su IMC es: " + subResultado);
                caja.setText("1) Come con más frecuencia. Empieza poco a poco a hacer 5 a 6 comidas mas pequeñas durante el día\n"
                        + "2) Elije alimentos con muchos nutrientes\n"
                        + "3) Agrega aderezos\n"
                        + "4) Prueba licuados y batidos de fruta\n"
                        + "5) Controla qué bebes y cuando\n"
                        + "6) Haz ejercicio.");

            } else if (subResultadoDouble > 18.05 && subResultadoDouble < 24.09) {
                resultados.setText("Su IMC es: " + subResultado);
                Toast.makeText(this, "Peso adecuado", Toast.LENGTH_SHORT).show();
                limpiarCampos();
                caja.setText("Felicitaciones, ud mantiene una excelente alimentación");
            } else if (subResultadoDouble > 24.9 && subResultadoDouble < 29.9) {
                resultados.setText("Su IMC es: " + subResultado);
                Toast.makeText(this, "Sobrepeso", Toast.LENGTH_SHORT).show();
                limpiarCampos();
                caja.setText("Como reducir el MC\n"
                        + "1) Haga 30 minutos de ejercicio aeróbico 5 veces por semana\n"
                        + "2) Logre un equilibrio entre el consumo de calorias y la actividad física\n"
                        + "3) Límite de grasas saturadas a no más de 7% de las calorias totales\n"
                        + "4) Tenga una alimentación baja en colesterol con carnes magras, frutas, verduras y cereales integrales\n");
            } else if (subResultadoDouble > 29.09 && subResultadoDouble < 34.09) {
                resultados.setText("Su IMC es: " + subResultado);
                Toast.makeText(this, "Obesidad grado 1", Toast.LENGTH_SHORT).show();
                limpiarCampos();
                caja.setText("Como reducir el MC\n"
                        + "1) haga 30 minutos de ejercicio aeróbico 5 veces por semana\n"
                        + "2) Logre un equilibrio entre el consumo de calorias y la actividad física\n"
                        + "3) Límite de grasas saturadas a no más de 7% de las calorias totales\n"
                        + "4) Tenga una alimentación baja en colesterol con carnes magras, frutas, verduras y cereales integrales\n");
            }

        } else {
            Toast.makeText(this, "Favor, no deje campos vacíos ni sin selección!", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        }
    }

    public void limpiarCampos() {
        peso.setText("");
        estatura.setText("");
    }
}