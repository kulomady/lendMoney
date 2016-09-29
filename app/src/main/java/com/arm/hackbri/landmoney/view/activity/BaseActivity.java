package com.arm.hackbri.landmoney.view.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.Bind;

import com.arm.hackbri.landmoney.R;

public class BaseActivity extends AppCompatActivity {

    @Nullable
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @Bind(R.id.ivLogo)
    ImageView ivLogo;


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        bindViews();
    }

    protected void bindViews() {
        ButterKnife.bind(this);
        setupToolbar();
    }

    public void setContentViewWithoutInject(int layoutResId) {
        super.setContentView(layoutResId);
    }

    protected void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_menu_white);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public ImageView getIvLogo() {
        return ivLogo;
    }
}
