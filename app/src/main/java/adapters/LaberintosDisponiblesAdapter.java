package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
            setColumnImageView(row, R.id.check_laberinto_en_juego, R.drawable.en_juego);
        } else {
            setColumnImageView(row, R.id.check_laberinto_en_juego, R.drawable.disponible);
        }

        return row;

/*        ViewHolder holder;

        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.laberinto_row,parent, false);
            holder = new ViewHolder();
            holder.nombre = (TextView) convertView.findViewById(R.id.row_laberinto_nombre);
            holder.imagen = (ImageView) convertView.findViewById(R.id.row_laberinto_imagen);
            holder.check = (ImageView) convertView.findViewById(R.id.check_laberinto_en_juego);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MinLaberinto laberinto = objects.get(position);
        String imagePath = LaberintosServiceFactory.API_URL +
                "/img/" + laberinto.getNombre() + ".jpg";

        holder.nombre.setText(laberinto.getNombre());
        if(laberinto.estaEnJuego()) {
            holder.check.setImageResource(R.drawable.en_juego);
        } else {
            holder.check.setImageResource(R.drawable.disponible);
        }

        imageDownloader.download(imagePath, holder.imagen);

        return convertView;
*/    }
}