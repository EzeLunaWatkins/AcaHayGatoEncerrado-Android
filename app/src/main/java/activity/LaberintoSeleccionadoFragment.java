package activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import model.MinInventario;
import model.MinItem;
import model.MinLaberinto;
import service.LaberintosService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import service.LaberintosServiceFactory;
import ui.unq.ezelunawatkins.acahaygatoencerrado.R;

/**
 * A fragment representing a single Laberinto detail screen.
 * This fragment is either contained in a {@link LaberintosActivity}
 * in two-pane mode (on tablets).
 */
public class LaberintoSeleccionadoFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LaberintoSeleccionadoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            String itemID = getArguments().getString(ARG_ITEM_ID);
            obtenerLaberinto(itemID);
        }
    }

    private void obtenerLaberinto(String laberintoId) {
        LaberintosService laberintosService = LaberintosServiceFactory.createLaberintosService();
        laberintosService.getLaberinto(laberintoId, new Callback<MinLaberinto>() {
            @Override
            public void success(MinLaberinto laberinto, Response response) {
                mostrarLaberinto(laberinto);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", error.getMessage());
                error.printStackTrace();
            }
        });
    }

    private void mostrarLaberinto(MinLaberinto laberinto) {
        ((TextView) this.getView().findViewById(R.id.laberinto_nombre)).setText(laberinto.getNombre());
        ((TextView) this.getView().findViewById(R.id.laberinto_descripcion)).setText(laberinto.getDescripcion());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_laberintos, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

     /*   Button mostrarInventario = (Button) getActivity().findViewById(R.id.button_mostrar_inventario);
        mostrarInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verInventario();
            }
        });*/

        super.onActivityCreated(savedInstanceState);
    }

    private void verInventario() {

        LaberintosService laberintosService = LaberintosServiceFactory.createLaberintosService();
        final InventarioFragment fragment = new InventarioFragment();

        laberintosService.getInventario(new Callback<List<MinItem>>() {
            @Override
            public void success(List<MinItem> minItems, Response response){
                LaberintosActivity activity = (LaberintosActivity) fragment.getActivity();
                activity.handleFragmentChange(fragment, "laberintos_fragment_container");
            }
            @Override
            public void failure(RetrofitError error) {
                Log.e("", error.getMessage());
                error.printStackTrace();
            }
        });
    }
}