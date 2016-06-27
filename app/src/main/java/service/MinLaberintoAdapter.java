package service;

import java.util.List;
import model.MinLaberinto;

public class MinLaberintoAdapter extends AbstractListAdapter<MinLaberinto> {

        public MinLaberintoAdapter(Context context, List<Libro> libros) {
            super(context, libros);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Libro libro = (Libro) getItem(position);
            View row = generateRow(R.layout.libro_row, parent);
            setColumnTextView(row, R.id.txtTitulo, libro.getTitulo());
            setColumnTextView(row, R.id.txtAutor, libro.getAutor());
            return row;
        }
}
