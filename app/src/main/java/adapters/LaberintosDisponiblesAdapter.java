package adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import model.MinLaberinto;
import ui.unq.ezelunawatkins.acahaygatoencerrado.R;

public class LaberintosDisponiblesAdapter extends AbstractListAdapter<MinLaberinto> {

    public LaberintosDisponiblesAdapter(Context context, List<MinLaberinto> laberintos) {
        super(context,laberintos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MinLaberinto laberinto = (MinLaberinto) getItem(position);
        View row = generateRow(R.layout.laberinto_row, parent);

        setColumnTextView(row, R.id.row_laberinto_nombre, laberinto.getNombre());

        if(laberinto.estaEnJuego()) {
            setColumnImageView(row, R.id.check_laberinto_en_juego, R.drawable.activo);
        } else {
            setColumnImageView(row, R.id.check_laberinto_en_juego, R.drawable.inactivo);
        }

        return row;
    }
}