package com.foxy.cameraview.ultis;

import com.foxy.cameraview.R;
import com.google.android.cameraview.CameraView;

public class Constants {

    public static final String TAG = "NamNT";

    public static final int REQUEST_CAMERA_PERMISSION = 1;

    public static final String FRAGMENT_DIALOG = "dialog";

    public static final int[] FLASH_OPTIONS = {
            CameraView.FLASH_AUTO,
            CameraView.FLASH_OFF,
            CameraView.FLASH_ON,
    };

    public static final int[] FLASH_ICONS = {
            R.drawable.ic_flash_auto,
            R.drawable.ic_flash_off,
            R.drawable.ic_flash_on,
    };

    public static final int[] FLASH_TITLES = {
            R.string.flash_auto,
            R.string.flash_off,
            R.string.flash_on,
    };

    public static final String ARG_MESSAGE = "message";
    public static final String ARG_PERMISSIONS = "permissions";
    public static final String ARG_REQUEST_CODE = "request_code";
    public static final String ARG_NOT_GRANTED_MESSAGE = "not_granted_message";
    public static final String ARG_ASPECT_RATIOS = "aspect_ratios";
    public static final String ARG_CURRENT_ASPECT_RATIO = "current_aspect_ratio";

}
