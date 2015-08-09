package ladsoft.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;

/**
 * Created by suematsu on 8/9/15.
 */
public class AnimatorHelper {

    /**
     * Animation duration presets.
     */
    public static final int SHORT_ANIMATION_DURATION = 500;
    public static final int MEDIUM_ANIMATION_DURATION = 1000;
    public static final int LONG_ANIMATION_DURATION = 2000;

    /**
     * Slide animation effect types.
     */
    public static final int SLIDE_BOTTOM_IN = 0;
    public static final int SLIDE_BOTTOM_OUT = 1;
    public static final int SLIDE_TOP_IN = 2;
    public static final int SLIDE_TOP_OUT = 3;
    public static final int SLIDE_LEFT_IN = 4;
    public static final int SLIDE_LEFT_OUT = 5;
    public static final int SLIDE_RIGHT_IN = 6;
    public static final int SLIDE_RIGHT_OUT = 7;


    public static void fadeContent(View view, boolean fadeIn, int duration) {

        //Set view opacity to 0%, but still VISIBLE
        final View content = view;

        if(fadeIn) {
            view.setAlpha(0f);
            view.setVisibility(View.VISIBLE);

            // Animates to 100% opacity, and clears any animation listener set on the view. Listeners
            // are not limited to the specific animation describes in the chained method calls.
            // Listeners are set on the ViewPropertyAnimator object for the view, which persists across
            // several animations.
            view.animate()
                    .alpha(1f)
                    .setDuration(duration)
                    .setListener(null);
        }
        else {
            view.setAlpha(1f);
            view.setVisibility(View.VISIBLE);

            // Animates the view to 0% opacity. After the animation ends, set its visibility to GONE
            // as an optimization step (it won't participate in layout passes, etc.)
            view.animate()
                    .alpha(0f)
                    .setDuration(duration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            content.setVisibility(View.GONE);
                        }
                    });
        }

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void slideContent(View view, int slideEffect, boolean fadeEffect, int duration) {

        Rect displaySizes = new Rect();
        view.getWindowVisibleDisplayFrame(displaySizes);

        view.setVisibility(View.VISIBLE);


        switch(slideEffect) {
            case SLIDE_TOP_IN:
                view.setTranslationY(- displaySizes.height());

                view.animate()
                        .translationY(0)
                        .alpha(1f)
                        .setDuration(duration)
                        .setListener(null);

                break;

            case SLIDE_TOP_OUT:
                //view.setTranslationY(- displaySizes.height());

                view.animate()
                        .translationY(- displaySizes.height())
                        .alpha(1f)
                        .setDuration(duration)
                        .setListener(null);
                break;

            case SLIDE_BOTTOM_IN:
                view.setTranslationY(displaySizes.height());

                view.animate()
                        .translationY(0)
                        .alpha(1f)
                        .setDuration(duration)
                        .setListener(null);
                break;

            case SLIDE_BOTTOM_OUT:
                view.animate()
                        .translationY(displaySizes.height())
                        .alpha(1f)
                        .setDuration(duration)
                        .setListener(null);
                break;

            case SLIDE_RIGHT_IN:
                view.setTranslationX(displaySizes.width());

                view.animate()
                        .translationX(0)
                        .alpha(1f)
                        .setDuration(duration)
                        .setListener(null);

                break;

            case SLIDE_RIGHT_OUT:
                view.animate()
                        .translationX(displaySizes.width())
                        .alpha(1f)
                        .setDuration(duration)
                        .setListener(null);

                break;

            case SLIDE_LEFT_IN:
                view.setTranslationX(- displaySizes.width());

                view.animate()
                        .translationX(0)
                        .alpha(1f)
                        .setDuration(duration)
                        .setListener(null);

                break;

            case SLIDE_LEFT_OUT:
                view.animate()
                        .translationX(displaySizes.width())
                        .alpha(1f)
                        .setDuration(duration)
                        .setListener(null);

                break;
        }






    }

}
