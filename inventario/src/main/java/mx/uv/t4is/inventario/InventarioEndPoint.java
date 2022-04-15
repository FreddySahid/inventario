package mx.uv.t4is.inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


import https.t4is_uv_mx.inventario.BorrarInventarioRequest;
import https.t4is_uv_mx.inventario.BorrarInventarioResponse;
import https.t4is_uv_mx.inventario.BuscarInventarioResponse;
import https.t4is_uv_mx.inventario.InventarioRequest;
import https.t4is_uv_mx.inventario.InventarioResponse;
import https.t4is_uv_mx.inventario.ModificarInventarioRequest;
import https.t4is_uv_mx.inventario.ModificarInventarioResponse;

@Endpoint
public class InventarioEndPoint {

    @Autowired
    Iinventario iinventario;

    /**El funcionamiento es el mismo que al de saludos, con la diferencia que recibe 2 parametros */

    @PayloadRoot(localPart = "InventarioRequest", namespace = "https://t4is.uv.mx/inventario")
    @ResponsePayload
    public InventarioResponse AddInventario(@RequestPayload InventarioRequest peticion){

        /*Aunque recibe dos parametros no es necesario pedirlo, con uno solo se pueden agregar, 
        ya que en el archivo inventario.xsd  se agrego el elemento de cantidad*/
        InventarioResponse respuesta = new InventarioResponse();
        respuesta.setRespuesta("El producto es"+ peticion.getNombre());
        Inventario inventario = new Inventario();
        inventario.setNombre(peticion.getNombre());
        inventario.setCantidad(peticion.getCantidad()); 

        iinventario.save(inventario);
        return respuesta;
    }

    @PayloadRoot(localPart = "BuscarInventarioRequest",namespace = "https://t4is.uv.mx/inventario")
    @ResponsePayload
    public BuscarInventarioResponse BuscarInventario(){
        BuscarInventarioResponse respuesta = new BuscarInventarioResponse();
        Iterable<Inventario> lista = iinventario.findAll();
        for (Inventario o : lista) {
            BuscarInventarioResponse.Inventario inventarioBuscar = new BuscarInventarioResponse.Inventario();
            inventarioBuscar.setId(o.getId());
            inventarioBuscar.setNombre(o.getNombre());
            inventarioBuscar.setCantidad(o.getCantidad());
            respuesta.getInventario().add(inventarioBuscar);
        }
        return respuesta;
    }

    @PayloadRoot(localPart = "ModificarInventarioRequest",namespace = "https://t4is.uv.mx/inventario")
    @ResponsePayload
    public ModificarInventarioResponse modificarInventario(@RequestPayload ModificarInventarioRequest peticion){
        ModificarInventarioResponse respuesta = new ModificarInventarioResponse();
        Inventario e = new Inventario();
        e.setId(peticion.getId());
        e.setNombre(peticion.getNombre());
        e.setCantidad(peticion.getCantidad());
        
        iinventario.save(e);
        respuesta.setRespuesta(true);
        return respuesta;
    }

    @PayloadRoot(localPart = "BorrarInventarioRequest",namespace = "https://t4is.uv.mx/inventario")
    @ResponsePayload
    public BorrarInventarioResponse borrarInventario(@RequestPayload BorrarInventarioRequest peticion){
        BorrarInventarioResponse respuesta = new BorrarInventarioResponse();

        iinventario.deleteById(peticion.getId());
        respuesta.setRespuesta(true);
        return respuesta;
    }


    
}
