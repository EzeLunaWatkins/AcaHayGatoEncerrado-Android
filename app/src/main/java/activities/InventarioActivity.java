package activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import model.MinInventario;
import ui.unq.ezelunawatkins.acahaygatoencerrado.R;

public class InventarioActivity extends AppCompatActivity {

    private MinInventario inventario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);
        this.inventario = new MinInventario();
    }
}