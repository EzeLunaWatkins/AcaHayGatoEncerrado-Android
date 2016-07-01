package activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

public class ErrorDialogFragment extends DialogFragment {

    public interface OnErrorDialogListener {
        void onNeutralButtonClick();
    }

    OnErrorDialogListener listener;
    String message;

    public ErrorDialogFragment() {
    }

    public void setMessage(String message){
        this.message = message;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createErrorDialog();
    }

    private AlertDialog createErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Error")
                .setMessage(message)
                .setNeutralButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onNeutralButtonClick();
                            }
                        });
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnErrorDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    activity.toString() +
                            "No implementó OnErrorDialogListener");
        }
    }
}