package com.desarrollosPatito.taproom.Utilidades;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
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
}

