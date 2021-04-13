package com.unsplash.pickerandroid.example.info;

import android.content.Context;

/**
 * Created by juan on 24/02/17.
 */

public final class infoContext {
    public static Context _context;

    public Context get_context() {
        return _context;
    }

    public void set_context(Context _context) {
        this._context = _context;
    }
}
