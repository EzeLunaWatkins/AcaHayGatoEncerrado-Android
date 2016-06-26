package activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import model.MinLaberinto;
import service.LaberintosService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ui.unq.ezelunawatkins.acahaygatoencerrado.R;

/**
 * A fragment representing a single Laberinto detail screen.
 * This fragment is either contained in a {@link LaberintosDisponiblesActivity}
 * in two-pane mode (on tablets) or a {@link LaberintoSeleccionadoActivity}
 * on handsets.
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
        LaberintosService laberintosService = createLaberintosService();
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

    private LaberintosService createLaberintosService() {
        //MMM código repetido, habría que modificar esto no?
        String SERVER_IP = "10.0.2.2"; //esta ip se usa para comunicarse con mi localhost en el emulador de Android Studio
        String SERVER_IP_GENY = "192.168.56.1";//esta ip se usa para comunicarse con mi localhost en el emulador de Genymotion
        String API_URL = "http://"+ SERVER_IP_GENY +":9000";

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
        LaberintosService laberintosService = restAdapter.create(LaberintosService.class);
        return laberintosService;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_laberinto_seleccionado, container, false);
        return rootView;
    }
}