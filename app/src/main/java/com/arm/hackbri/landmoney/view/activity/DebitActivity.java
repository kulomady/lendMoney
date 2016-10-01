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
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

import com.arm.hackbri.landmoney.R;
import com.arm.hackbri.landmoney.Utils;
import com.arm.hackbri.landmoney.interactor.NetworkInteractor;
import com.arm.hackbri.landmoney.interactor.NetworkInteractorImpl;
import com.arm.hackbri.landmoney.interactor.OnFetchDataListener;
import com.arm.hackbri.landmoney.model.ParamNetwork;
import com.arm.hackbri.landmoney.model.response.Debit;
import com.arm.hackbri.landmoney.model.response.Profile;
import com.arm.hackbri.landmoney.model.response.TBankSaldo;
import com.arm.hackbri.landmoney.network.exception.GeneralErrorException;
import com.arm.hackbri.landmoney.network.exception.HttpErrorException;
import com.arm.hackbri.landmoney.presenter.DebitListPresenter;
import com.arm.hackbri.landmoney.presenter.DebitListPresenterImpl;
import com.arm.hackbri.landmoney.view.DebitListView;
import com.arm.hackbri.landmoney.view.adapter.DebitAdapter;
import com.arm.hackbri.landmoney.view.adapter.DebitItemAnimator;
import com.arm.hackbri.landmoney.view.viewComponent.FeedContextMenuManager;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class DebitActivity extends BaseDrawerActivity implements DebitAdapter.OnDebitClickListener,
        DebitListView {
    public static final String ACTION_SHOW_LOADING_ITEM = "action_show_loading_item";

    private static final int ANIM_DURATION_TOOLBAR = 300;
    private static final int ANIM_DURATION_FAB = 400;

    @Bind(R.id.rvFeed)
    RecyclerView rvFeed;
    @Bind(R.id.btnBuatTagihan)
    FloatingActionButton fabCreate;
    @Bind(R.id.content)
    CoordinatorLayout clContent;
    @Bind(R.id.debit_progressBar)
    ProgressBar debitProgressBar;

    private DebitAdapter debitAdapter;

    private boolean pendingIntroAnimation;
    private DebitListPresenter debitListPresenter;
    private String phoneValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit);

        debitListPresenter = new DebitListPresenterImpl(this);
        debitListPresenter.processFetchDebitList(this);
        if (savedInstanceState == null) {
            pendingIntroAnimation = true;
        } else {
            debitAdapter.updateItems(false);
        }
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

    }


    @OnClick(R.id.btnBuatTagihan)
    public void onTakePhotoClick() {
        showCreateDebitDialog();
    }

    private void navigateToCreateDebit(Profile profileTarget) {
        int[] startingLocation = new int[2];
        fabCreate.getLocationOnScreen(startingLocation);
        startingLocation[0] += fabCreate.getWidth() / 2;
        CreateDebitActivity.openActivity(this, profileTarget);
        overridePendingTransition(0, 0);
    }

    void showPaymentDialog(final String debt_id) {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.payment_dialog);
        RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.rg_payment_method);

        Button btnBayar = (Button) dialog.findViewById(R.id.btnBayar);
        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                processTransfer(debt_id);
            }
        });

        dialog.show();
    }

    private void processTransfer(String debt_id) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Transferring");
        progressDialog.show();
        NetworkInteractor networkInteractor = new NetworkInteractorImpl();
        ParamNetwork.Builder builder = new ParamNetwork.Builder();
        builder.put("debt_id", debt_id);
        networkInteractor.transfer(builder.build(), new OnFetchDataListener<TBankSaldo>() {
            @Override
            public void onSuccessFetchData(TBankSaldo data) {
                progressDialog.dismiss();
                debitListPresenter.processFetchDebitList(DebitActivity.this);
            }

            @Override
            public void onFailedFetchData(Throwable throwable) {
                progressDialog.dismiss();
                if (throwable instanceof SocketTimeoutException) {
                    renderErrorConnection("Server timeout, silahkan coba kembali");
                } else if (throwable instanceof UnknownHostException) {
                    renderErrorConnection("Tidak ada internet, Silahkan coba kembali");
                } else if (throwable instanceof HttpErrorException) {
                    renderErrorUnknown(throwable.getMessage());
                } else if (throwable instanceof GeneralErrorException) {
                    renderErrorUnknown(throwable.getMessage());
                } else {
                    renderErrorUnknown(throwable.getMessage());
                }
            }
        });
    }

    @Override
    public void showProgressFetchDebitList() {
        rvFeed.setVisibility(View.GONE);
        debitProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void dismissProgressFetchDebitList() {
        debitProgressBar.setVisibility(View.GONE);
        rvFeed.setVisibility(View.VISIBLE);
    }

    @Override
    public void renderDebitListDatas(List<Debit> debitList) {
        setDebitList(debitList);
    }

    @Override
    public void renderErrorServerFetchData(String messageError) {
        showMessageSnackBar(messageError);
    }

    @Override
    public void renderErrorResponseFetchData(String messageError) {
        showMessageSnackBar(messageError);
    }

    @Override
    public void renderErrorConnection(String messageError) {
        showMessageSnackBar(messageError);
    }

    @Override
    public void renderErrorUnknown(String messageError) {
        showMessageSnackBar(messageError);
    }

    @Override
    public void renderUserNotLogin() {

    }

    private void setDebitList(List<Debit> debits) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rvFeed.setLayoutManager(linearLayoutManager);

        debitAdapter = new DebitAdapter(this, debits);
        debitAdapter.setOnDebitClickListener(this);
        rvFeed.setAdapter(debitAdapter);
        rvFeed.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
            }
        });
        rvFeed.setItemAnimator(new DebitItemAnimator());
        debitAdapter.updateItems(true);
    }

    void showMessageSnackBar(String message) {
        Snackbar.make(clContent, message, Snackbar.LENGTH_LONG).show();
    }

    void showCreateDebitDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.debit_dialog);

        Button btnBayar = (Button) dialog.findViewById(R.id.btnCari);
        final EditText editText = (EditText) dialog.findViewById(R.id.edtCariNoHp);
        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                phoneValue = editText.getText().toString();
                processCreateDebit();
            }
        });

        dialog.show();
    }

    private void processCreateDebit() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        NetworkInteractor interactor = new NetworkInteractorImpl();
        ParamNetwork.Builder builder = new ParamNetwork.Builder();
        builder.put("user_phone", phoneValue);
        interactor.getDialogNewDebit(builder.build(), new OnFetchDataListener<Profile>() {
            @Override
            public void onSuccessFetchData(Profile data) {
                progressDialog.dismiss();
                navigateToCreateDebit(data);
            }

            @Override
            public void onFailedFetchData(Throwable throwable) {
                progressDialog.dismiss();
                showDialogInvite();
            }
        });
    }

    private void showDialogInvite() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder
                .setMessage("User ini belum terdafar apakah mau mengundangnya ")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Yes-code
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .show();

    }

    @Override
    public void onBayarClick(View v, int position, String debtId) {
        showPaymentDialog(debtId);
    }
}