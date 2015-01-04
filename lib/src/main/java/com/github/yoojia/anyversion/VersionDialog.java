package com.github.yoojia.anyversion;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.view.WindowManager;

/**
 * Created by Yoojia.Chen
 * yoojia.chen@gmail.com
 * 2015-01-04
 */
public class VersionDialog {

    private final AlertDialog dialog;

    public VersionDialog(final Application context, final Version version) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.Theme_System_Alert)
                .setTitle(version.name)
                .setMessage(version.note)
                .setCancelable(false)
                .setNegativeButton(R.string.later, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton(R.string.update_now, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Downloads.submit(context, version);
                        dialog.cancel();
                    }
                })
                ;
        this.dialog = builder.create();

    }

    public void show(){
        this.dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        try{
            dialog.show();
        }catch (Exception e){
            throw new IllegalArgumentException("Required " +
                    "'<uses-permission android:name=\"android.permission.SYSTEM_ALERT_WINDOW\" />' !");
        }
    }
}