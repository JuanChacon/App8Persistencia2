package edu.tecii.android.app8persistencia2;

/**
 * Created by Juan Chacon Holguin on 16/10/2017.
 */

public class Contactos {
    String nombre ;
    String apellido;
    String telefono;
    String correo;


   //...Toma mas valores, es de dos parametros, pero como tiene ... este permitira mas parametros
  public Contactos(String Nombre, String... Datos){
        this.nombre =  Nombre;
        this.apellido = Datos[0]; //el 0 es la posicion del arreglo, si se deja 0 para todos, se pondra lo mismo que tenga el primer elemento de esa posicion
        this.telefono =  Datos[1];
        this.correo = Datos[2];


  }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }
    public String getTelefono() {
        return this.telefono;
    }
    public String getCorreo() {
        return this.correo;
    }

}
