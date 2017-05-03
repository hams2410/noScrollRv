package ru.pro2410.recyclerviewnotscroll;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.pro2410.recyclerviewnotscroll.item_header.DepartmentHeader;
import ru.pro2410.recyclerviewnotscroll.item_header.DepartmentNavUp;

/**
 * Created by USER on 03.05.2017.
 */

public class DepartmentHeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @NonNull
    private final List<HeaderItem> mItems;
    @NonNull
    private final OnDepartmentClickListener mDepartmentClickListener;
    @NonNull
    private final OnNavigationUpClickListener mUpClickListener;
    public DepartmentHeaderAdapter(@NonNull OnDepartmentClickListener departmentClickListener,
                                   @NonNull OnNavigationUpClickListener upClickListener) {
        mDepartmentClickListener = departmentClickListener;
        mUpClickListener = upClickListener;
        mItems = new ArrayList<>();
    }

    public void update(List<HeaderItem> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView;
        switch (viewType) {
            case HeaderItem.ITEM_DEPARTMENT:
                itemView = inflater.inflate(R.layout.item_department_header, parent, false);
                return new DepartmentHeaderHolder(itemView);
            case HeaderItem.ITEM_DIVIDER:
                itemView = inflater.inflate(R.layout.item_department_divider, parent, false);
                return new DepartmentDividerHolder(itemView);
            case HeaderItem.ITEM_NAVIGATION_UP:
                itemView = inflater.inflate(R.layout.item_department_nav_up, parent, false);
                return new DepartmentNavigationUpHolder(itemView);
        }
        throw new IllegalArgumentException("Wrong view type!");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == HeaderItem.ITEM_DEPARTMENT) {
            DepartmentHeader departmentHeader = (DepartmentHeader) mItems.get(position);
            DepartmentHeaderHolder departmentHolder = (DepartmentHeaderHolder) holder;
            departmentHolder.bind(departmentHeader, mDepartmentClickListener);
        } else if (viewType == HeaderItem.ITEM_NAVIGATION_UP) {
            DepartmentNavUp departmentNavUp = (DepartmentNavUp) mItems.get(position);
            DepartmentNavigationUpHolder navigationUpHolder = (DepartmentNavigationUpHolder) holder;
            navigationUpHolder.bind(departmentNavUp, mUpClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getItemViewType();
    }

    public interface OnDepartmentClickListener {
        void onDepartmentClick(Long departmentId);
    }

    public interface OnNavigationUpClickListener {
        void onNavigationUpClick(Long childDepartmentId);
    }

    static class DepartmentHeaderHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_dep)
        TextView mTitle;
        @BindView(R.id.root_dep)
        ImageView mBtnRoot;

        DepartmentHeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final DepartmentHeader departmentHeader, final OnDepartmentClickListener onDepartmentClickListener) {
            mTitle.setText(departmentHeader.getTitle());
            mTitle.setVisibility(departmentHeader.isTitleVisible() ? View.VISIBLE : View.GONE);

            if (getAdapterPosition()!=0) mTitle.setEms(10);

            if (departmentHeader.hasShowIcon()) mBtnRoot.setImageResource(R.drawable.ic_shoplight);
            else mBtnRoot.setImageResource(R.drawable.ic_header);

            mBtnRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (departmentHeader.isActive()) {
                        mBtnRoot.setEnabled(true);
                        onDepartmentClickListener.onDepartmentClick(departmentHeader.getId());
                    }else mBtnRoot.setEnabled(false);
                }
            });
        }

    }

    static class DepartmentDividerHolder extends RecyclerView.ViewHolder {

        DepartmentDividerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class DepartmentNavigationUpHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nav_up_dep)
        RelativeLayout mNavUpBtn;

        DepartmentNavigationUpHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final DepartmentNavUp departmentNavUp, final OnNavigationUpClickListener navigationUpClickListener) {
            mNavUpBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navigationUpClickListener.onNavigationUpClick(departmentNavUp.getId());
                }
            });
        }
    }


}
