package com.arm.hackbri.landmoney.view.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.animation.DecelerateInterpolator;

import com.arm.hackbri.landmoney.Utils;

import java.util.List;

public class CreditItemAnimator extends DefaultItemAnimator {

    private int lastAddAnimatedItem = -2;

    @Override
    public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewHolder) {
        return true;
    }

    @NonNull
    @Override
    public ItemHolderInfo recordPreLayoutInformation(@NonNull RecyclerView.State state,
                                                     @NonNull RecyclerView.ViewHolder viewHolder,
                                                     int changeFlags, @NonNull List<Object> payloads) {

        return super.recordPreLayoutInformation(state, viewHolder, changeFlags, payloads);
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.getItemViewType() == CreditAdapter.VIEW_TYPE_DEFAULT) {
            if (viewHolder.getLayoutPosition() > lastAddAnimatedItem) {
                lastAddAnimatedItem++;
                runEnterAnimation((CreditAdapter.CellHutangViewHolder) viewHolder);
                return false;
            }
        }

        dispatchAddFinished(viewHolder);
        return false;
    }

    private void runEnterAnimation(final CreditAdapter.CellHutangViewHolder holder) {
        final int screenHeight = Utils.getScreenHeight(holder.itemView.getContext());
        holder.itemView.setTranslationY(screenHeight);
        holder.itemView.animate()
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(700)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        dispatchAddFinished(holder);
                    }
                })
                .start();
    }

    @Override
    public boolean animateChange(@NonNull RecyclerView.ViewHolder oldHolder,
                                 @NonNull RecyclerView.ViewHolder newHolder,
                                 @NonNull ItemHolderInfo preInfo,
                                 @NonNull ItemHolderInfo postInfo) {

        return false;
    }



    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {
        super.endAnimation(item);

    }

    @Override
    public void endAnimations() {
        super.endAnimations();

    }

}
