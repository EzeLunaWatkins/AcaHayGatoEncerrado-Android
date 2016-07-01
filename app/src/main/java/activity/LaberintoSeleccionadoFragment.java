package activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;

import model.MinItem;
import model.MinLaberinto;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import service.LaberintosService;
import service.LaberintosServiceFactory;
import ui.unq.ezelunawatkins.acahaygatoencerrado.R;

public class LaberintoSeleccionadoFragment extends Fragment {

    public static final String ARG_ITEM_ID = "id";

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

        final TextView nombreLaberinto = (TextView) getActivity().findViewById(R.id.laberinto_nombre);
        nombreLaberinto.setText(getArguments().getString("nombre"));

        TextView descripcionLaberinto = (TextView) getActivity().findViewById(R.id.laberinto_descripcion);
        descripcionLaberinto.setText(getArguments().getString("descripcion"));

        new ImagenLaberintoSeleccionado((ImageView) getActivity().findViewById(R.id.laberinto_imagen))
                .execute(LaberintosServiceFactory.API_URL + "/img/"
                        + nombreLaberinto.getText() + ".jpg");

        Button inventario = (Button) getActivity().findViewById(R.id.button_mostrar_inventario);
        inventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getArguments().getBoolean("enJuego")) {
                    getInventario();
                } else {
                    Toast toast1 = Toast.makeText(getActivity().getApplicationContext(),
                                    "Este laberinto no está siendo jugado, por lo cual," +
                                    "no tiene asociado ningún inventario a mostrar. Juegue al laberinto '" +
                                    getArguments().getString("nombre") + "' para poder ver su inventario.",
                                    Toast.LENGTH_SHORT);
                    toast1.show();
                }
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

    private void getInventario() {

        LaberintosService laberintosService = LaberintosServiceFactory.createLaberintosService();
        laberintosService.getInventario(new Callback<List<MinItem>>() {

            @Override
            public void success(List<MinItem> inventario, Response response) {
                triggerFragmentReplace(inventario);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", error.getMessage());
                error.printStackTrace();
            }
        });
    }

    private void triggerFragmentReplace(List<MinItem> inventario) {

        InventarioFragment fragment = new InventarioFragment();
        fragment.setInventario(inventario);

        MainActivity activity = (MainActivity) getActivity();
        activity.handleFragmentChange(fragment, "inventario_list");
    }
}

class ImagenLaberintoSeleccionado extends AsyncTask<String, Void, Bitmap> {

    ImageView bmImage;

    public ImagenLaberintoSeleccionado(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}