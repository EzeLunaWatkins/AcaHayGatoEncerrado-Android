package adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import model.MinItem;
import ui.unq.ezelunawatkins.acahaygatoencerrado.R;

public class InventarioAdapter extends AbstractListAdapter<MinItem> {

    public InventarioAdapter(Context context, List<MinItem> items) {
        super(context,items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MinItem item = (MinItem) getItem(position);
        View row = generateRow(R.layout.item_row, parent);

        setColumnTextView(row, R.id.row_item_nombre, item.getNombre());

        if(item.getId()==R.id.item_seleccionado_id) {
            setColumnStatus(row, R.id.check_item_seleccionado, R.drawable.activo);
        } else {
            setColumnStatus(row, R.id.check_item_seleccionado, R.drawable.inactivo);
        }

        return row;
    }
}