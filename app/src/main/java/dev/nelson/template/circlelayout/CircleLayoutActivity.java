package dev.nelson.template.circlelayout;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.nelson.template.R;

public class CircleLayoutActivity extends AppCompatActivity {

    @BindView(R.id.root) ConstraintLayout root;
    @BindView(R.id.sun_image) ImageView sunImage;
    @BindView(R.id.earth_image) ImageView earthImage;
    @BindView(R.id.mars_image) ImageView marsImage;
    @BindView(R.id.saturn_image) ImageView saturnImage;

    private ConstraintSet orbitsConstraint = new ConstraintSet();
    private ConstraintSet detailsConstraint = new ConstraintSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orbits);
        ButterKnife.bind(this);
        orbitsConstraint.clone(root);
        detailsConstraint.clone(this, R.layout.activity_details);

        ValueAnimator earthAnimator = animatePlanet(earthImage, TimeUnit.SECONDS.toMillis(2));
        ValueAnimator marsAnimator = animatePlanet(marsImage, TimeUnit.SECONDS.toMillis(6));
        ValueAnimator saturnAnimator = animatePlanet(saturnImage, TimeUnit.SECONDS.toMillis(12));

        startAmin(earthAnimator, marsAnimator, saturnAnimator);

        sunImage.setOnClickListener(view -> {
            cancelAnim(earthAnimator, marsAnimator, saturnAnimator);
            TransitionManager.beginDelayedTransition(root);
            detailsConstraint.applyTo(root);
        });
    }

    private void cancelAnim(ValueAnimator earthAnimator, ValueAnimator marsAnimator, ValueAnimator saturnAnimator) {
        earthAnimator.cancel();
        marsAnimator.cancel();
        saturnAnimator.cancel();
    }

    private void startAmin(ValueAnimator earthAnimator, ValueAnimator marsAnimator, ValueAnimator saturnAnimator) {
        earthAnimator.start();
        marsAnimator.start();
        saturnAnimator.start();
    }

    private ValueAnimator animatePlanet(ImageView planet, long orbitDuration) {
        ValueAnimator anim = ValueAnimator.ofInt(0, 359);
        anim.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) planet.getLayoutParams();
            layoutParams.circleAngle = val;
            planet.setLayoutParams(layoutParams);
        });
        anim.setDuration(orbitDuration);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatMode(ValueAnimator.RESTART);
        anim.setRepeatCount(ValueAnimator.INFINITE);

        return anim;
    }
}
