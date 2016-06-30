package activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
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


    public static final String ARG_ITEM_ID = "id";

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

        obtenerLaberinto(getArguments().getString(ARG_ITEM_ID));

        return inflater.inflate(R.layout.laberinto_detalle, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        TextView nombreLaberinto = (TextView) getActivity().findViewById(R.id.laberinto_nombre);
        nombreLaberinto.setText(getArguments().getString("nombre"));

        TextView descripcionLaberinto = (TextView) getActivity().findViewById(R.id.laberinto_descripcion);
        descripcionLaberinto.setText(getArguments().getString("descripcion"));

        new ImagenLaberinto((ImageView) getActivity().findViewById(R.id.laberinto_imagen))
                .execute(LaberintosServiceFactory.API_URL+"/img/"
                        + nombreLaberinto.getText() + ".jpg");



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