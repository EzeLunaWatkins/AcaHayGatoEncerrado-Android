package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by estefaniamiguel on 17/6/15. Enjoy!
 */
public abstract class AbstractListAdapter<T> extends BaseAdapter {
    private Context context;
    private List<T> objects;

    public AbstractListAdapter(Context pContext, List<T> severalObjects) {
        context = pContext;
        objects = severalObjects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View generateRow(int rowLayoutId, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(rowLayoutId, parent, false);
    }

    public void setColumnTextView(View row, int widgetId, String value) {
        TextView widget = (TextView) row.findViewById(widgetId);
        widget.setText(value);
    }

    public void setColumnStatus(View row, int widgetId, int value) {
        ImageView widget = (ImageView) row.findViewById(widgetId);
        widget.setImageResource(value);
    }
}