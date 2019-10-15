package com.osiris.food.view.dialog;

import android.app.Dialog;

/**
 * Created by zhukaifeng on 2017/8/18.
 */

public interface DialogClickListener {

    void onClick(Dialog dialog, String msg);
    void onClick(Dialog dialog);
}
