package edu.tecii.android.app8persistencia2;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//AGREGAR NUM.TEL Y CORREO, editar informacion cuando le preciones al elemento en la lista y que se pueda eliminar
public class MainActivity extends AppCompatActivity {

    ListView list;
    Button bt;
    static String nombre = "";
    static String apellido = "";
    static String tele = "";
    static String correo = "";
    static Boolean ocul = false;
    int x = 0;
    int pos = 0;
    // utiliza fifo
    static ArrayList<Contactos> Lista =  new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView)findViewById(R.id.lista);
        bt = (Button)findViewById(R.id.buttonUn);

        leerLista();
        actualizarLista();
        //total = Lista.size();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(inte);
                nombre = "";
                apellido = "";
                tele = "";
                correo = "";
                ocul = false;

            }
        });



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           if (position != -1){
            pos = position;
               //System.out.println("posicion de la ta: " + pos);
            datosLista();

           }
               /* switch(position){
                    case 0:{datosLista();
                    break;}
                    case 1:{
                        datosLista();
                        break;}
                    case 2:{
                        datosLista();
                        break;}
                    case 3:{
                        datosLista();
                        break;}
                    case 4:{
                        datosLista();
                        break;}
                    case 5:{
                        datosLista();
                        break;}
                    case 6:{
                        datosLista();
                        break;}
                    case 7:{
                        datosLista();
                        break;}
                }
*/

            }
        });
    }

    public void onResume(){
        super.onResume();
        leerLista();
        actualizarLista();
    }
    public void actualizarLista(){
        ArrayList<String> aux = new ArrayList<>();
        for (Contactos c: Lista){
            aux.add(c.nombre);
            aux.add(c.apellido);
            aux.add(c.telefono);
            aux.add(c.correo);

        }
        //pasar la lista aux a listView                                                                     //Layout
        ArrayAdapter adaptador = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,aux);
        list.setAdapter(adaptador);
    }

    public void datosLista(){
        //actualizarLista();
        Iterator<Contactos> iter = Lista.iterator();
        Intent go = new Intent(MainActivity.this,Main2Activity.class);
        //for (Contactos c: Lista){
        while (iter.hasNext()) {
            Contactos c = iter.next();
            // if para comprobar las posiciones de la tabla
           if ( pos<= 3 && c.getNombre() == Lista.get(0).getNombre()){

               //go.putExtra("nom",c.getNombre());
               //go.putExtra("ape",c.getApellido());
               //go.putExtra("tel", c.getTelefono());
               //go.putExtra("corre", c.getCorreo());
               //startActivity(go);
               //finish();

               x = Lista.indexOf(c);
               //System.out.println("posicionX " + x + "tamano lista" + total);
               nombre = Lista.get(0).getNombre(); //c.getNombre();
               apellido = Lista.get(0).apellido; //c.getApellido();
               tele = Lista.get(0).telefono; //c.getTelefono();
               correo = Lista.get(0).getCorreo(); //c.getCorreo();
               startActivity(go);
               ocul = true;

            // if para comprobar las posiciones de la tabla
           }if ( 4 <= pos  && pos <= 7 && c.getNombre() == Lista.get(1).getNombre()){

                //go.putExtra("nom",c.getNombre());
                //go.putExtra("ape",c.getApellido());
                //go.putExtra("tel", c.getTelefono());
                //go.putExtra("corre", c.getCorreo());
                //startActivity(go);
                //finish();

                x = Lista.indexOf(1);
                //System.out.println("posicionX " + x + c.nombre);
                //System.out.println("posicionX " + x + "tamano lista" + total);
                nombre = Lista.get(1).getNombre(); //c.getNombre();
                apellido = Lista.get(1).apellido; //c.getApellido();
                tele = Lista.get(1).telefono; //c.getTelefono();
                correo = Lista.get(1).getCorreo(); //c.getCorreo();
                startActivity(go);
                ocul = true;


            }

        }

    }


        //memoria Externa
    public boolean HasExternal(){
        String estado = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(estado)){
            return  true;
        }else{
            return  false;
        }
    }
    //Leer lista de memoria
    public void leerLista(){
        try {
            File archivo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"archivo.txt");
            if (HasExternal() && archivo.exists()){
                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(archivo));
                Lista = (ArrayList<Contactos>) stream.readObject();
                stream.close();
            }
        }catch(Exception e){
        e.getStackTrace();
        }
    }


}
