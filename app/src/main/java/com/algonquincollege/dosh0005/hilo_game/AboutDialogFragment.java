package com.algonquincollege.dosh0005.hilo_game;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Created by Harsh Doshi - dosh0005
 * Date: 2017-10-05
 * Purpose : HiLo Game
 */

// about Dialog box fragment.

public class AboutDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        EditText plName = (EditText) ((Activity) getContext()).findViewById(R.id.getPlayerName);
        String DialogMsg = "" + getText(R.string.dialog_about_msg) +" "+ plName.getText() + " ";

        // Decorate our About dialog
        builder.setTitle(R.string.menuAbout)
                .setMessage(DialogMsg)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();

    }

}
