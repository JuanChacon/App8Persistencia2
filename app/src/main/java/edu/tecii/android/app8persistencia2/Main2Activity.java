package edu.tecii.android.app8persistencia2;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Iterator;

public class Main2Activity extends AppCompatActivity {

    EditText nomb;
    EditText ape;
    EditText tel;
    EditText correo;
    String temp1 = "";
    String temp = "";
    String te = "";
    String cor = "";


    Button btn1;
    Button btn2;
    Button Act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nomb = (EditText)findViewById(R.id.editTextNa);
        ape = (EditText)findViewById(R.id.editTextLa);
        tel = (EditText)findViewById(R.id.editTextTel);
        correo = (EditText)findViewById(R.id.editTextEmail);


        btn1 = (Button)findViewById(R.id.buttonOne);
        btn2 = (Button)findViewById(R.id.buttonTwo);
        Act = (Button)findViewById(R.id.buttonAct);

            recibeDatos();
        //if para la visibilidad de los botones
            if(MainActivity.ocul){
                Act.setVisibility(View.VISIBLE);
            btn1.setVisibility(View.INVISIBLE);
            }
            else{Act.setVisibility(View.INVISIBLE);
            btn2.setVisibility(View.INVISIBLE);}



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Contactos cont = new Contactos(nomb.getText().toString(), ape.getText().toString(), tel.getText().toString(), correo.getText().toString());
                    MainActivity.Lista.add(cont);
                    guardarArchivo();
                    Intent main = new Intent(Main2Activity.this, MainActivity.class);
                    startActivity(main);
                    finish();
                }catch(Error e){
                    System.out.println(e.toString());
                }


            }
        });
        Act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Contactos cont = new Contactos(nomb.getText().toString(), ape.getText().toString(), tel.getText().toString(), correo.getText().toString());
                    Iterator<Contactos> iter = MainActivity.Lista.iterator();
                    while (iter.hasNext()) {
                        Contactos user = iter.next();
                        if (user.nombre.equals(MainActivity.nombre)) {
                            int x = MainActivity.Lista.indexOf(user);
                            //MainActivity.Lista.get(0);
                            //System.out.println("posicion " + x);
                            MainActivity.Lista.set(x, cont);
                            //Use iterator to remove this User object.
                            iter.remove();
                        }
                    }

                    MainActivity.Lista.add(cont);
                    guardarArchivo();
                    Intent mains = new Intent(Main2Activity.this, MainActivity.class);
                    startActivity(mains);
                    finish();
                }catch(Error e){
                    System.out.println(e.toString());}
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarContac(MainActivity.nombre);

            }
        });

    }


    public boolean HasExternal(){
        String estado = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(estado)){
            return  true;
            }else{
            return  false;
        }
    }



    public void guardarArchivo(){
        try {
            if (HasExternal()){
             File arc = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"archivo.txt");
                ObjectOutputStream str = new ObjectOutputStream(new FileOutputStream(arc));
                str.writeObject(MainActivity.Lista);
                str.close();
            }
        }catch(IOException e){

        }
    }



    public void recibeDatos(){
       // Intent i1 = getIntent();
       // Intent i = getIntent();
       // Intent in = getIntent();
       // Intent intt = getIntent();

        String temp1 = MainActivity.nombre; //i1.getStringExtra("nom");
        String temp = MainActivity.apellido;//i.getStringExtra("ape");
        String te = MainActivity.tele;//in.getStringExtra("tel");
        String cor = MainActivity.correo; //intt.getStringExtra("corre");
        nomb.setText(temp1);
        ape.setText(temp);
        tel.setText(te);
        correo.setText(cor);
    }

    public void eliminarContac( String nombrr){
        try{
        for(Contactos con:MainActivity.Lista) {
            //while(iter.hasNext()){
            int pos = MainActivity.Lista.indexOf(con);
            //Cuando solo hay un objeto en la lista
            if (pos == 0 && MainActivity.Lista.size() == 1) {
                //MainActivity.Lista.set(0,MainActivity.Lista.get(1));
                MainActivity.Lista.remove(con);
                Toast.makeText(getBaseContext(), "Se elimino el objeto con exito "  , Toast.LENGTH_SHORT).show();
                System.out.println("primer if" + pos);
//                MainActivity.Lista.remove(pos);
                nomb.setText("");
                ape.setText("");
                tel.setText("");
                correo.setText("");
                //System.out.println("PAAA" + MainActivity.Lista.size());
            }
            if (con.nombre.equals(nombrr)) {
            //Cuando se elimina el primer objeto de la lista pero hay mas objetos en la lista
                if (pos == 0 && MainActivity.Lista.size() > 1) {
                    MainActivity.Lista.set(0, MainActivity.Lista.get(1));
                    MainActivity.Lista.remove(con);
                    MainActivity.Lista.remove(pos);
                    Toast.makeText(getBaseContext(), "Se elimino el objeto con exito " , Toast.LENGTH_SHORT).show();
                    //System.out.println("cuando hay mas de 1 if" + pos);
                    nomb.setText("");
                    ape.setText("");
                    tel.setText("");
                    correo.setText("");
                    //System.out.println("PAAA" + MainActivity.Lista.size());

                }
                MainActivity.Lista.remove(con);
                //Toast.makeText(getBaseContext(), "Se elimino el objeto de la posicion: " + (pos+1) , Toast.LENGTH_SHORT).show();
            //System.out.println("ulitmo if" + pos);
                nomb.setText("");
                ape.setText("");
                tel.setText("");
                correo.setText("");
            }
        }
        }catch(Exception e){
            System.out.println(e.toString());
        }

        Intent ba = new Intent(Main2Activity.this,MainActivity.class);
        startActivity(ba);
        finish();
    }



}
