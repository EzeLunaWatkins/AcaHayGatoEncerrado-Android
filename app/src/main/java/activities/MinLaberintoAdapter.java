package activities;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import model.MinLaberinto;
import ui.unq.ezelunawatkins.acahaygatoencerrado.R;

public class MinLaberintoAdapter extends AbstractListAdapter<MinLaberinto> {

        public MinLaberintoAdapter(Context context, List<MinLaberinto> laberintos) {
            super(context, laberintos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MinLaberinto laberinto = (MinLaberinto) getItem(position);
            View row = generateRow(R.layout.laberinto_row, parent);
            setColumnTextView(row, R.id.laberinto_nombre, laberinto.getNombre());
            return row;
        }
}