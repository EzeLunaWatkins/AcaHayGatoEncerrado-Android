package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import model.MinLaberinto;
import service.ImageDownloader;
import service.LaberintosServiceFactory;
import service.ViewHolder;
import ui.unq.ezelunawatkins.acahaygatoencerrado.R;

public class LaberintosDisponiblesAdapter extends BaseAdapter {

    private final ImageDownloader imageDownloader = new ImageDownloader();
    private List<MinLaberinto> minLaberintoList;
    private LayoutInflater layoutInflater;

    public LaberintosDisponiblesAdapter(Context context, List<MinLaberinto> laberintos) {

        this.minLaberintoList = laberintos;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return minLaberintoList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

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

        MinLaberinto laberinto = minLaberintoList.get(position);
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
    }

    @Override
    public long getItemId (int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return minLaberintoList.get(position);
    }
}