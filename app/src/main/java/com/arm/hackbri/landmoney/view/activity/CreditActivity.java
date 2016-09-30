package com.arm.hackbri.landmoney.view.activity;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
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
import com.arm.hackbri.landmoney.interactor.PreferencesInteractor;
import com.arm.hackbri.landmoney.interactor.PreferencesInteractorImpl;
import com.arm.hackbri.landmoney.model.ParamNetwork;
import com.arm.hackbri.landmoney.model.response.Credit;
import com.arm.hackbri.landmoney.model.response.Profile;
import com.arm.hackbri.landmoney.presenter.CreditListPresenter;
import com.arm.hackbri.landmoney.presenter.CreditListPresenterImpl;
import com.arm.hackbri.landmoney.view.CreditListView;
import com.arm.hackbri.landmoney.view.adapter.CreditAdapter;
import com.arm.hackbri.landmoney.view.adapter.CreditItemAnimator;
import com.arm.hackbri.landmoney.view.viewComponent.FeedContextMenu;
import com.arm.hackbri.landmoney.view.viewComponent.FeedContextMenuManager;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class CreditActivity extends BaseDrawerActivity implements CreditAdapter.OnItemClickListener,
        FeedContextMenu.OnFeedContextMenuItemClickListener, CreditListView {
    public static final String ACTION_SHOW_LOADING_ITEM = "action_show_loading_item";

    private static final int ANIM_DURATION_TOOLBAR = 300;
    private static final int ANIM_DURATION_FAB = 400;

    @Bind(R.id.rvFeed)
    RecyclerView rvFeed;
    @Bind(R.id.btnCreate)
    FloatingActionButton fabCreate;
    @Bind(R.id.content)
    CoordinatorLayout clContent;

    private String phoneValue;

    @Bind(R.id.credit_progressbar)
    ProgressBar creditProgressBar;

    private CreditAdapter creditAdapter;
    private CreditListPresenter creditListPresenter;

    private Profile profile;
    private PreferencesInteractor preferencesInteractor;

    private boolean pendingIntroAnimation;
    public static void openActivity(Activity openingActivity) {
        Intent intent = new Intent(openingActivity, CreditActivity.class);

        openingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);


        creditListPresenter = new CreditListPresenterImpl(this);
        creditListPresenter.processFetchCreditList(this);
        preferencesInteractor = new PreferencesInteractorImpl();
        profile = preferencesInteractor.getUserData(this);
        if (profile != null) {
            setSaldo("Rp." + String.valueOf(profile.gettBankSaldo().getSaldo()));
            setUsernameText(profile.getUserName());
        }
        if (savedInstanceState == null) {
            pendingIntroAnimation = true;
        } else {
            if(creditAdapter!=null)creditAdapter.updateItems(false);
        }
    }

    private void setupFeed(List<Credit> credits) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rvFeed.setLayoutManager(linearLayoutManager);

        creditAdapter = new CreditAdapter(credits, this);
        creditAdapter.setOnItemClickListener(this);
        rvFeed.setAdapter(creditAdapter);
        rvFeed.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
            }
        });

        rvFeed.setItemAnimator(new CreditItemAnimator());
        creditAdapter.updateItems(true);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (ACTION_SHOW_LOADING_ITEM.equals(intent.getAction())) {
            if(creditAdapter!=null)showFeedLoadingItemDelayed();
        }
    }

    private void showFeedLoadingItemDelayed() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rvFeed.smoothScrollToPosition(0);
                creditAdapter.showLoadingView();
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

    @Override
    public void onReportClick(int feedItem) {
        FeedContextMenuManager.getInstance().hideContextMenu();
    }

    @Override
    public void onSharePhotoClick(int feedItem) {
        FeedContextMenuManager.getInstance().hideContextMenu();
    }

    @Override
    public void onCopyShareUrlClick(int feedItem) {
        FeedContextMenuManager.getInstance().hideContextMenu();
    }

    @Override
    public void onCancelClick(int feedItem) {
        FeedContextMenuManager.getInstance().hideContextMenu();
    }

    @OnClick(R.id.btnCreate)
    public void onTakePhotoClick() {
//        navigateToCreateCredit();
        showCreateCreditDialog();
    }

    private void navigateToCreateCredit(Profile profile) {
        int[] startingLocation = new int[2];
        fabCreate.getLocationOnScreen(startingLocation);
        startingLocation[0] += fabCreate.getWidth() / 2;
        CreateCreditActivity.openWithPhotoUri(this,profile);
        overridePendingTransition(0, 0);
    }


    @Override
    public void onBayarClick(View v, int position) {
       showPaymentDialog();
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

    @Override
    public void showProgressFetchCreditList() {
        rvFeed.setVisibility(View.GONE);
        creditProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgressFetchCreditList() {
        creditProgressBar.setVisibility(View.GONE);
        rvFeed.setVisibility(View.VISIBLE);
    }

    @Override
    public void renderCreditListDatas(List<Credit> creditList) {
        setupFeed(creditList);
    }

    @Override
    public void renderErrorServerFetchData(String messageError) {
        showSnackBarError(messageError);
    }

    @Override
    public void renderErrorResponseFetchData(String messageError) {
        showSnackBarError(messageError);
    }

    @Override
    public void renderErrorConnection(String messageError) {
        showSnackBarError(messageError);
    }

    @Override
    public void renderErrorUnknown(String messageError) {
        showSnackBarError(messageError);
    }

    @Override
    public void renderUserNotLogin() {

    }

    void showSnackBarError(String message) {
        Snackbar.make(clContent, message, Snackbar.LENGTH_LONG).show();
    }

    void showCreateCreditDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.credit_dialog);

        Button btnBayar = (Button) dialog.findViewById(R.id.btnCari);
        final EditText editText = (EditText) dialog.findViewById(R.id.edtCariNoHp);
        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                phoneValue = editText.getText().toString();
                processCreateCreditDialog();
            }
        });

        dialog.show();
    }

    private void processCreateCreditDialog() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        NetworkInteractor interactor = new NetworkInteractorImpl();
        ParamNetwork.Builder builder = new ParamNetwork.Builder();
        builder.put("user_phone", phoneValue);
        interactor.getDialogNewCredit(builder.build(), new OnFetchDataListener<Profile>() {
            @Override
            public void onSuccessFetchData(Profile data) {
                progressDialog.dismiss();
                navigateToCreateCredit(data);
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
                .setPositiveButton("Yes",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Yes-code
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                })
                .show();

    }
}