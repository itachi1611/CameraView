package com.foxy.cameraview.view;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;

import static com.foxy.cameraview.ultis.Constants.ARG_MESSAGE;
import static com.foxy.cameraview.ultis.Constants.ARG_NOT_GRANTED_MESSAGE;
import static com.foxy.cameraview.ultis.Constants.ARG_PERMISSIONS;
import static com.foxy.cameraview.ultis.Constants.ARG_REQUEST_CODE;


public class ConfirmationDialogFragment extends DialogFragment {

    public static ConfirmationDialogFragment newInstance(@StringRes int message, String[] permissions, int requestCode, @StringRes int notGrantedMessage) {
        ConfirmationDialogFragment fragment = new ConfirmationDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_MESSAGE, message);
        args.putStringArray(ARG_PERMISSIONS, permissions);
        args.putInt(ARG_REQUEST_CODE, requestCode);
        args.putInt(ARG_NOT_GRANTED_MESSAGE, notGrantedMessage);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Bundle args = getArguments();
        return new AlertDialog.Builder(getActivity())
                .setMessage(args.getInt(ARG_MESSAGE))
                .setPositiveButton(android.R.string.ok,
                        (dialog, which) -> {
                            String[] permissions = args.getStringArray(ARG_PERMISSIONS);
                            if (permissions == null) {
                                throw new IllegalArgumentException();
                            }
                            ActivityCompat.requestPermissions(getActivity(),
                                    permissions, args.getInt(ARG_REQUEST_CODE));
                        })
                .setNegativeButton(android.R.string.cancel,
                        (dialog, which) -> Toast.makeText(getActivity(),
                                args.getInt(ARG_NOT_GRANTED_MESSAGE),
                                Toast.LENGTH_SHORT).show())
                .create();
    }

}

