package adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.MinLaberinto;
import ui.unq.ezelunawatkins.acahaygatoencerrado.R;

public class LaberintosDisponiblesAdapter extends AbstractListAdapter<MinLaberinto>{


    public LaberintosDisponiblesAdapter(Context context, List<MinLaberinto> laberintos) {
        super(context, laberintos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        MinLaberinto laberinto = (MinLaberinto) getItem(position);

        row = generateRow(R.layout.laberinto_row, parent);
        setColumnTextView(row, R.id.row_laberinto_nombre, laberinto.getNombre());

        if (laberinto.estaEnJuego()) {
            setColumnStatus(row, R.id.check_laberinto_en_juego, R.drawable.activo);
        } else {
            setColumnStatus(row, R.id.check_laberinto_en_juego, R.drawable.inactivo);
        }
        return row;
    }
}