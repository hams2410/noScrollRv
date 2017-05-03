package ru.pro2410.recyclerviewnotscroll.item_header;

import android.support.annotation.Nullable;

import ru.pro2410.recyclerviewnotscroll.HeaderItem;

public final class DepartmentHeader implements HeaderItem {
    private Long id;
    private String title;
    private boolean isTitleVisible;
    private boolean isActive;
    private boolean hasShowIcon;

    public DepartmentHeader(Long id, String title, boolean isTitleVisible, boolean isActive,boolean hasShowIcon) {
        this.id = id;
        this.title = title;
        this.isTitleVisible = isTitleVisible;
        this.isActive = isActive;
        this.hasShowIcon = hasShowIcon;
    }

    public boolean hasShowIcon() {
        return hasShowIcon;
    }

    public boolean isTitleVisible() {
        return isTitleVisible;
    }

    public boolean isActive() {
        return isActive;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getItemViewType() {
        return ITEM_DEPARTMENT;
    }

}
