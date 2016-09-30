package com.arm.hackbri.landmoney.view.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arm.hackbri.landmoney.R;
import com.arm.hackbri.landmoney.model.response.Credit;
import com.arm.hackbri.landmoney.view.viewComponent.LoadingFeedItemView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreditAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_DEFAULT = 1;
    public static final int VIEW_TYPE_LOADER = 2;

    private final List<Credit> credits;

    private Context context;
    private OnItemClickListener onItemClickListener;

    private boolean showLoadingView = false;

    public CreditAdapter(List<Credit> credits, Context context) {
        this.credits = credits;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_DEFAULT) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_credit, parent, false);
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
                onItemClickListener.onBayarClick(v, cellHutangViewHolder.getAdapterPosition());
            }
        });


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((CellHutangViewHolder) viewHolder).bindView(credits.get(position));

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
        return credits.size();
    }

    public void updateItems(boolean animated) {
        credits.clear();

        if (animated) {
            notifyItemRangeInserted(0, credits.size());
        } else {
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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

        @Bind(R.id.description)
        TextView description;

        @Bind(R.id.amount)
        TextView amount;


        Credit credit;

        public CellHutangViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindView(Credit credit) {
            this.credit = credit;
        }

        public Credit getCredit() {
            return credit;
        }
    }

    public static class LoadingCellHutangViewHolder extends CellHutangViewHolder {

        LoadingFeedItemView loadingFeedItemView;

        public LoadingCellHutangViewHolder(LoadingFeedItemView view) {
            super(view);
            this.loadingFeedItemView = view;
        }

        @Override
        public void bindView(Credit credit) {
            super.bindView(credit);
        }
    }


    public interface OnItemClickListener {

        void onBayarClick(View v, int position);

    }
}
