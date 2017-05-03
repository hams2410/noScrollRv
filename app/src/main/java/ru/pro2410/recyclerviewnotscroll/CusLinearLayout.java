package ru.pro2410.recyclerviewnotscroll;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by USER on 03.05.2017.
 */

public class CusLinearLayout extends LinearLayoutManager {
    public CusLinearLayout(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);

    }

    @Override
    public boolean canScrollHorizontally() {
        return false;
    }

}