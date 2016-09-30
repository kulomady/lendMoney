/*
 * Created By Kulomady on 9/30/16 1:41 PM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/30/16 1:41 PM
 */

package com.arm.hackbri.landmoney.view.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.arm.hackbri.landmoney.R;
import com.arm.hackbri.landmoney.model.response.Debit;
import com.arm.hackbri.landmoney.view.viewComponent.LoadingFeedItemView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DebitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_DEFAULT = 1;
    public static final int VIEW_TYPE_LOADER = 2;

    private final List<Debit> debits;

    private Context context;
    private OnDebitClickListener onDebitClickListener;

    private boolean showLoadingView = false;


    public DebitAdapter( Context context,List<Debit> debits) {

        this.debits = debits;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_DEFAULT) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_debit, parent, false);
            CellHutangViewHolder cellHutangViewHolder = new CellHutangViewHolder(view);
            setupClickableViews(view, cellHutangViewHolder);
            return cellHutangViewHolder;
        } else if (viewType == VIEW_TYPE_LOADER) {
            LoadingFeedItemView view = new LoadingFeedItemView(context);
            view.setLayoutParams(new LinearLayoutCompat.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            );
            return new LoadingCellHutangViewHolder(view);
        }

        return null;
    }

    private void setupClickableViews(final View view, final CellHutangViewHolder cellHutangViewHolder) {
        cellHutangViewHolder.btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDebitClickListener.onBayarClick(v, cellHutangViewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((CellHutangViewHolder) viewHolder).bindView(debits.get(position));

        if (getItemViewType(position) == VIEW_TYPE_LOADER) {
            bindLoadingFeedItem((LoadingCellHutangViewHolder) viewHolder);
        }
    }

    private void bindLoadingFeedItem(final LoadingCellHutangViewHolder holder) {
        holder.loadingFeedItemView.setOnLoadingFinishedListener(new LoadingFeedItemView.OnLoadingFinishedListener() {
            @Override
            public void onLoadingFinished() {
                showLoadingView = false;
                notifyItemChanged(0);
            }
        });
        holder.loadingFeedItemView.startLoading();
    }

    @Override
    public int getItemViewType(int position) {
        if (showLoadingView && position == 0) {
            return VIEW_TYPE_LOADER;
        } else {
            return VIEW_TYPE_DEFAULT;
        }
    }

    @Override
    public int getItemCount() {
        return debits.size();
    }

    public void updateItems(boolean animated) {
        debits.clear();
//        debits.addAll(Arrays.asList(
//                new FeedItem(33, false),
//                new FeedItem(1, false),
//                new FeedItem(223, false),
//                new FeedItem(2, false),
//                new FeedItem(6, false),
//                new FeedItem(8, false),
//                new FeedItem(99, false)
//        ));
        if (animated) {
            notifyItemRangeInserted(0, debits.size());
        } else {
            notifyDataSetChanged();
        }
    }

    public void setOnDebitClickListener(OnDebitClickListener onDebitClickListener) {
        this.onDebitClickListener = onDebitClickListener;
    }

    public void showLoadingView() {
        showLoadingView = true;
        notifyItemChanged(0);
    }

    public static class CellHutangViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.ivUserProfile)
        ImageView ivUserProfile;

        @Bind(R.id.btnBayar)
        Button btnBayar;


        Debit debit;

        public CellHutangViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindView(Debit debit) {
            this.debit = debit;
        }

        public Debit getDebit() {
            return debit;
        }
    }

    public static class LoadingCellHutangViewHolder extends CellHutangViewHolder {

        LoadingFeedItemView loadingFeedItemView;

        public LoadingCellHutangViewHolder(LoadingFeedItemView view) {
            super(view);
            this.loadingFeedItemView = view;
        }

        @Override
        public void bindView(Debit debit) {
            super.bindView(debit);
        }
    }

    public interface OnDebitClickListener {
        void onBayarClick(View v, int position);

    }
}
