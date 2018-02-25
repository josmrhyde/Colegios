package mx.unam.aragon.fes.diplo.colegios;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG="CambioEstado";
    private Spinner spinner;
    private String colegios[]= new String[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        colegios = new String[]{"Seleccionar una Facultad", "FES Acatlán", "FES Aragón", "FES Cuautitlán", "FES Iztacala", "FES Zaragoza"};
        spinner = (Spinner)findViewById(R.id.spinner);
        addColegio(colegios);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int item, long id) {
                if (item!=0){

                    String facultad = spinner.getItemAtPosition(item).toString();
                    EditText txtUsuario = (EditText) findViewById(R.id.editText);
                    EditText txtPssw = (EditText)findViewById(R.id.editText2);
                    String usuario = txtUsuario.getText().toString();
                    String pssw = txtPssw.getText().toString();

                    if (usuario.isEmpty() || pssw.isEmpty() || facultad.isEmpty() ) {
                        Toast.makeText(MainActivity.this,"Introduce datos: usuario, password y selecciona una facultad",Toast.LENGTH_SHORT).show();
                        Toast.makeText(spinner.getContext(), "Has seleccionado " + spinner.getItemAtPosition(item).toString(), Toast.LENGTH_SHORT).show();
                    }else{
                        if (usuario!=null && pssw!=null && facultad!=null ) {
                            startNewActivity(usuario, facultad);
                        }
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(spinner.getContext(),"ningún elemento seleccionado",Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void startNewActivity(String usuario, String facultad){
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        String alumno = usuario.toString();
        String fes = facultad.toString();

        intent.putExtra("ALUMNO", alumno);
        intent.putExtra("FES", fes);
        startActivity(intent);
    }

    private void addColegio(String[]colegios){
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colegios);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroyd");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG,"onRestoreInstanceState");

        EditText txtUsuario = (EditText)findViewById(R.id.editText);
        CharSequence usuarioText = savedInstanceState.getCharSequence("savedUsuario");
        EditText txtPssw = (EditText)findViewById(R.id.editText2);
        CharSequence psswText = savedInstanceState.getCharSequence("savedPssw");
        txtUsuario.setText(usuarioText);
        txtPssw.setText(psswText);
        if (savedInstanceState!=null){
            colegios = savedInstanceState.getStringArray("spinnerData");
            spinner.setSelection(savedInstanceState.getInt("spinnerItem", 0));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"onSaveInstanceState");

        EditText txtUsuario = (EditText) findViewById(R.id.editText);
        CharSequence usuarioText = txtUsuario.getText();
        EditText txtPssw = (EditText)findViewById(R.id.editText2);
        CharSequence psswText = txtPssw.getText();

        outState.putCharSequence("savedUsuario", usuarioText);
        outState.putCharSequence("savedPssw", psswText);
        outState.putStringArray("spinnerData",colegios);
        outState.putInt("sppinerItem", spinner.getSelectedItemPosition());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
