package ru.pro2410.recyclerviewnotscroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        DepartmentHeaderAdapter.OnDepartmentClickListener,
        DepartmentHeaderAdapter.OnNavigationUpClickListener{
    @BindView(R.id.navigation_header)
    RecyclerView mNavigation;
    private DepartmentHeaderAdapter mNavigationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        CusLinearLayout layoutManager =
                new CusLinearLayout(this, LinearLayoutManager.HORIZONTAL, false);
        mNavigation.setLayoutManager(layoutManager);
        mNavigationAdapter = new DepartmentHeaderAdapter(this, this);
        mNavigation.setAdapter(mNavigationAdapter);

        mNavigation.setVisibility(View.VISIBLE);

        mNavigationAdapter.update(new HeaderInfo(123L,56L,"Привет чики еу как оно курите иль чо епта", true).getHeaderItems());
    }

    @Override
    public void onDepartmentClick(Long departmentId) {
        Toast.makeText(this, "onDepartmentClick", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNavigationUpClick(Long childDepartmentId) {
        Toast.makeText(this, "onNavigationUpClick", Toast.LENGTH_SHORT).show();
    }
}
