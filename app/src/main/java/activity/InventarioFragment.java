package activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import adapters.InventarioAdapter;
import model.MinItem;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import service.LaberintosService;
import service.LaberintosServiceFactory;
import ui.unq.ezelunawatkins.acahaygatoencerrado.R;

public class InventarioFragment extends Fragment{

    List<MinItem> inventario;

    public InventarioFragment (){
    }

    public void setInventario(List<MinItem> minItemList){
        inventario = minItemList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.content_inventario, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        Button deleteItem = (Button) getActivity().findViewById(R.id.button_tirar_item);
        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tirarItem();
            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    private void tirarItem() {

        LaberintosService laberintosService = LaberintosServiceFactory.createLaberintosService();
        laberintosService.tirarItem(R.id.item_seleccionado_id, new Callback<MinItem>() {
            @Override
            public void success(MinItem item, Response response) {
                updateList();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", error.getMessage());
                error.printStackTrace();
            }
        });
    }

    private void updateList() {

        ListView listView = (ListView) getActivity().findViewById(R.id.inventario_items_list);
        InventarioAdapter adapter = new InventarioAdapter(getActivity(), inventario);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(parent, position);
            }
        });
    }

    private void selectItem(AdapterView<?> parent, int position){

        Bundle args = new Bundle();

        MinItem itemSeleccionado = (MinItem) parent.getItemAtPosition(position);
        args.putInt("id", itemSeleccionado.getId());
        args.putString("nombre", itemSeleccionado.getNombre());
        this.setArguments(args);
    }
}