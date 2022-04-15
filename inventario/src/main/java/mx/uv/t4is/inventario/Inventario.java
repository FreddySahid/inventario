package mx.uv.t4is.inventario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    private String cantidad;

    /**El inventario consta de 3 parametros, el primero es un id de tipo int, con el fin de que sea
     * autoincrementable, el nombre y la cantidad son de tipo String. La razón de hacer la cantidad de 
     * tipo string es que si quieren puner punto decimal no afecte.
     * 
     */

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public void setCantidad(String cantidad){
        this.cantidad = cantidad;
    }

    public String getCantidad(){
        return cantidad;
    }
    
}
