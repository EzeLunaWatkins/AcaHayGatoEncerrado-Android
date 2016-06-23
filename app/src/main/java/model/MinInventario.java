package model;

import java.util.ArrayList;
import java.util.List;

public class MinInventario {

    List<MinItem> items = new  ArrayList<MinItem>();

    public void agregar(MinItem item){
        if(this.hayEspacioDisponible()){
            items.add(item);
        }
    }

    public boolean hayEspacioDisponible(){
        return items.size() < 15;
    }

    public void quitar(Integer idItem){
        MinItem itemAEliminar = null;
        for(MinItem item : this.items){
            if(item.getId() == idItem){
                itemAEliminar = item;
            }
        }
        items.remove(itemAEliminar);
    }
}