package com.desarrollosPatito.taproom.Utilidades;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.desarrollosPatito.taproom.R;

public class Generales {

    public static Dialog mostrarDialogoCargaConImagen(Context context) {
        Dialog dialogoCarga = new Dialog(context);
        dialogoCarga.setContentView(R.layout.preloader);
        dialogoCarga.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogoCarga.setCancelable(false);

        ImageView imageView = dialogoCarga.findViewById(R.id.imageViewCarga);
        Animation rotateAnimation = AnimationUtils.loadAnimation(context, R.anim.rotar);
        imageView.startAnimation(rotateAnimation);

        dialogoCarga.show();

        return dialogoCarga;
    }

    public static void ocultarDialogoCargaConImagen(Dialog dialogoCarga) {
        if (dialogoCarga != null && dialogoCarga.isShowing()) {
            dialogoCarga.dismiss();
        }
    }

    // Método para verificar la conexión a Internet
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnected();
        }
        return false;
    }
}

