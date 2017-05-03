package ru.pro2410.recyclerviewnotscroll;

import java.util.ArrayList;
import java.util.List;

import ru.pro2410.recyclerviewnotscroll.item_header.DepartmentDivider;
import ru.pro2410.recyclerviewnotscroll.item_header.DepartmentHeader;
import ru.pro2410.recyclerviewnotscroll.item_header.DepartmentNavUp;

public class HeaderInfo {

    private Long mId;

    private Long mParentId;

    private String mName;

    private List<HeaderItem> mHeaderItems;

    private boolean hasShopIcon;

    public HeaderInfo(Long id, Long parentId, String name, boolean hasShopIcon) {
        this.mId = id;
        this.mParentId = parentId;
        this.mName = name;
        this.hasShopIcon = hasShopIcon;

        mHeaderItems = createNavigationStateHeader();
    }

    private List<HeaderItem> createNavigationStateHeader() {

        List<HeaderItem> headerItems = new ArrayList<>();

        boolean hasDepartmentId = mId != null;
        boolean hasDepartmentParentId = mParentId != null;

        headerItems.add(new DepartmentHeader(null, mName, !hasDepartmentId, hasDepartmentId,false));

        if (hasDepartmentId&&!hasDepartmentParentId) {
            headerItems.add(new DepartmentDivider());
            headerItems.add(new DepartmentHeader(mId, mName,true , false,hasShopIcon));
        }
        if (hasDepartmentId&&hasDepartmentParentId) {
            headerItems.add(new DepartmentDivider());
            headerItems.add(new DepartmentNavUp(mParentId));
            headerItems.add(new DepartmentDivider());
            headerItems.add(new DepartmentHeader(mId, mName, true, false, hasShopIcon));
        }
        return headerItems;
    }

    public Long getId() {
        return mId;
    }

    public Long getParentId() {
        return mParentId;
    }

    public String getName() {
        return mName;
    }

    public List<HeaderItem> getHeaderItems() {
        return mHeaderItems;
    }
}