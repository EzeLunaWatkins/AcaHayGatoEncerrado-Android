package activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import ui.unq.ezelunawatkins.acahaygatoencerrado.R;

public class MainActivity extends AppCompatActivity implements ErrorDialogFragment.OnErrorDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laberintos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Acá Hay Gato Encerrado...");

        if (savedInstanceState == null) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            LaberintosDisponiblesFragment fragment = new LaberintosDisponiblesFragment();
            fragmentTransaction.add(R.id.main_fragment_container, fragment, "laberintos_disponibles_list");
            fragmentTransaction.commit();
        }
    }

    public void handleFragmentChange(Fragment fragment, String name){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_container, fragment, name);
        fragmentTransaction.addToBackStack(name);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {

        if(getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
        else super.onBackPressed();
    }

    @Override
    public void onNeutralButtonClick() {
        //cerrar diálogo
    }
}