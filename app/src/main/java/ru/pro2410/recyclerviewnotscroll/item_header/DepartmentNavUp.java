package ru.pro2410.recyclerviewnotscroll.item_header;


import ru.pro2410.recyclerviewnotscroll.HeaderItem;

public final class DepartmentNavUp implements HeaderItem {
    private final Long mId;

    public DepartmentNavUp(Long id) {
        mId = id;
    }

    @Override
    public int getItemViewType() {
        return ITEM_NAVIGATION_UP;
    }

    public Long getId() {
        return mId;
    }
}
