package activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import adapters.LaberintosDisponiblesAdapter;
import model.MinLaberinto;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import service.LaberintosService;
import service.LaberintosServiceFactory;
import ui.unq.ezelunawatkins.acahaygatoencerrado.R;

public class LaberintosDisponiblesFragment extends Fragment{

    public LaberintosDisponiblesFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.content_laberintos_disponibles, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        getLaberintosDisponiblesParaJugador();

        Button refresh = (Button) getActivity().findViewById(R.id.button_actualizar_laberintos_disponibles);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLaberintosDisponiblesParaJugador();
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

    private void getLaberintosDisponiblesParaJugador() {

        int idJugador = getActivity().getIntent().getExtras().getInt("id");
        getLaberintosDisponiblesParaJugador(idJugador);
    }

    private void getLaberintosDisponiblesParaJugador(int id) {

        LaberintosService laberintosService = LaberintosServiceFactory.createLaberintosService();
        laberintosService.getLaberintos(new Callback<List<MinLaberinto>>() {

           @Override
           public void success(List<MinLaberinto> laberintosDisponibles, Response response) {
               convertToList(laberintosDisponibles);
           }

           @Override
           public void failure(RetrofitError error) {
               Log.e("", error.getMessage());
               error.printStackTrace();
           }
        });
    }

    private void convertToList(List<MinLaberinto> laberintos) {

        ListView listView = (ListView) getActivity().findViewById(R.id.laberintos_disponibles_list);
        LaberintosDisponiblesAdapter adapter = new LaberintosDisponiblesAdapter(getActivity(), laberintos);
        listView.setAdapter(adapter);

    }
}