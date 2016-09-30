/*
 * Created By Kulomady on 9/29/16 10:22 PM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 10:21 PM
 */

package com.arm.hackbri.landmoney.view.activity;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.RadioGroup;

import com.arm.hackbri.landmoney.R;
import com.arm.hackbri.landmoney.Utils;
import com.arm.hackbri.landmoney.view.adapter.DebitAdapter;
import com.arm.hackbri.landmoney.view.adapter.FeedItemAnimator;
import com.arm.hackbri.landmoney.view.viewComponent.FeedContextMenuManager;

import butterknife.Bind;
import butterknife.OnClick;

public class DebitActivity extends BaseDrawerActivity implements DebitAdapter.OnDebitClickListener {
    public static final String ACTION_SHOW_LOADING_ITEM = "action_show_loading_item";

    private static final int ANIM_DURATION_TOOLBAR = 300;
    private static final int ANIM_DURATION_FAB = 400;

    @Bind(R.id.rvFeed)
    RecyclerView rvFeed;
    @Bind(R.id.btnBuatTagihan)
    FloatingActionButton fabCreate;
    @Bind(R.id.content)
    CoordinatorLayout clContent;

    private DebitAdapter debitAdapter;

    private boolean pendingIntroAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit);
        setupFeed();

        if (savedInstanceState == null) {
            pendingIntroAnimation = true;
        } else {
            debitAdapter.updateItems(false);
        }
    }

    private void setupFeed() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rvFeed.setLayoutManager(linearLayoutManager);

        debitAdapter = new DebitAdapter(this);
        debitAdapter.setOnDebitClickListener(this);
        rvFeed.setAdapter(debitAdapter);
        rvFeed.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
            }
        });
        rvFeed.setItemAnimator(new FeedItemAnimator());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (ACTION_SHOW_LOADING_ITEM.equals(intent.getAction())) {
            showFeedLoadingItemDelayed();
        }
    }

    private void showFeedLoadingItemDelayed() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rvFeed.smoothScrollToPosition(0);
                debitAdapter.showLoadingView();
            }
        }, 500);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        if (pendingIntroAnimation) {
            pendingIntroAnimation = false;
            startIntroAnimation();
        }
        return true;
    }

    private void startIntroAnimation() {
        fabCreate.setTranslationY(2 * getResources().getDimensionPixelOffset(R.dimen.btn_fab_size));

        int actionbarSize = Utils.dpToPx(56);
        getToolbar().setTranslationY(-actionbarSize);
        getTitleText().setTranslationY(-actionbarSize);

        getToolbar().animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300);
        getTitleText().animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(400)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startContentAnimation();
                    }
                })
                .start();

    }

    private void startContentAnimation() {
        fabCreate.animate()
                .translationY(0)
                .setInterpolator(new OvershootInterpolator(1.f))
                .setStartDelay(300)
                .setDuration(ANIM_DURATION_FAB)
                .start();
        debitAdapter.updateItems(true);
    }


    @Override
    public void onBayarClick(View v, int itemPosition) {
        showPaymentDialog();
    }

    @OnClick(R.id.btnBuatTagihan)
    public void onTakePhotoClick() {
        int[] startingLocation = new int[2];
        fabCreate.getLocationOnScreen(startingLocation);
        startingLocation[0] += fabCreate.getWidth() / 2;
        CreateDebitActivity.openWithPhotoUri(this, null);
        overridePendingTransition(0, 0);
    }

    void showPaymentDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.payment_dialog);
        RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.rg_payment_method);

        Button btnBayar = (Button) dialog.findViewById(R.id.btnBayar);
        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}