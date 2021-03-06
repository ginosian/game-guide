package com.example.martha.gameguide.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;

import com.example.martha.gameguide.listener.FragmentActionListener;

/**
 * Created by Martha on 5/22/2016.
 */
public class FlipHorizontal extends Animation{
    // region Static fields
    public static final int PIVOT_CENTER = 1, PIVOT_LEFT = 2, PIVOT_RIGHT = 3, PIVOT_NONE = 0;
    public static final String ACTION_FLIP_ANIMATION_COMPLETE = "action_flip_animation_complete";
    // endregion


    public AnimatorSet animationSet(View rootView, long duration, float degrees, int pivot, final FragmentActionListener listener){
        float viewWidth = rootView.getWidth(), viewHeight = rootView.getHeight();
        switch (pivot) {
            case PIVOT_NONE:
                break;
            case PIVOT_LEFT:
                rootView.setPivotX(0);
                rootView.setPivotY(viewHeight / 2);
                break;
            case PIVOT_RIGHT:
                rootView.setPivotX(viewWidth);
                rootView.setPivotY(viewHeight / 2);
                break;
            case PIVOT_CENTER:
                rootView.setPivotX(viewWidth / 2);
                rootView.setPivotY(viewHeight / 2);
                break;
        }
        AnimatorSet flipSet = new AnimatorSet();
        flipSet.play(ObjectAnimator.ofFloat(rootView, View.ROTATION_Y, rootView.getRotationY() + degrees));
        flipSet.setInterpolator(new AccelerateDecelerateInterpolator());
        flipSet.setDuration(duration);
        flipSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                if (listener != null) {
                    listener.actionComplete(ACTION_FLIP_ANIMATION_COMPLETE);
                }
            }
        });
        return flipSet;
    }
}
