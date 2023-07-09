package com.pixelperfect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
public class OnboardingActivity extends AppCompatActivity {
    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators ;

    private MaterialButton buttonOnboardingAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_onboarding);
        SharedPreferences pref3 = getSharedPreferences("onboarding", MODE_PRIVATE);
        boolean check_onboarding = pref3.getBoolean("flag4", false);

        if(check_onboarding){
            Intent intent = new Intent(OnboardingActivity.this, MainActivity.class);
            finish();
            startActivity(intent);

        }

        buttonOnboardingAction = findViewById(R.id.buttonOnboardingAction);

        layoutOnboardingIndicators=findViewById(R.id.layoutOnboardingIndicators);


        setupOnboardingItems();

        ViewPager2 onboardingViewPager =findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicators();

        setCurrentOnboardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });

        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onboardingViewPager.getCurrentItem()+1 < onboardingAdapter.getItemCount())
                {
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem()+1);
                }
                else
                {
                    SharedPreferences pref3 = getSharedPreferences("onboarding", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref3.edit();
                    editor.putBoolean("flag4", true);
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(),SignInGoogleActivity.class));
                    finish();
                }
            }
        });
    }

    private void setupOnboardingItems() {
        List<OnboardingItems> onboardingItems= new ArrayList<>();

        OnboardingItems itemPayOnline= new OnboardingItems();
        itemPayOnline.setTitle("Welcome to WALL-ART");
        itemPayOnline.setDescription("Explore a vast collection of breathtaking wallpapers handpicked to suit your unique style and personality.");
        itemPayOnline.setImage(R.drawable.on1);

        OnboardingItems itemOnTheWay = new OnboardingItems();
        itemOnTheWay.setTitle("Elevate Your Mobile Experience");
        itemOnTheWay.setDescription("Whether you're seeking inspiration, personalization, or pure aesthetic delight, our extensive collection of stunning wallpapers has got you covered.");
        itemOnTheWay.setImage(R.drawable.on4);

        OnboardingItems itemEatTogether = new OnboardingItems();
        itemEatTogether.setTitle("Unlock a World of Beauty");
        itemEatTogether.setDescription("Our app offers an array of categories, from breathtaking landscapes to vibrant abstract art, allowing you to find the perfect wallpaper to match your unique style");
        itemEatTogether.setImage(R.drawable.on7);

        onboardingItems.add(itemPayOnline);
        onboardingItems.add(itemOnTheWay);
        onboardingItems.add(itemEatTogether);


        onboardingAdapter=new OnboardingAdapter(onboardingItems);
    }

    private void setupOnboardingIndicators(){
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);

        for(int i=0;i<indicators.length;i++)
        {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplication(),
                    R.drawable.onboarding_indicator_active
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }
    private void setCurrentOnboardingIndicator(int index){
        int childCount = layoutOnboardingIndicators.getChildCount();

        for(int i=0;i< childCount;i++)
        {
            ImageView imageView = (ImageView) layoutOnboardingIndicators.getChildAt(i);
            if(i==index)
            {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_active)
                );
            }
            else
            {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive)
                );
            }
        }
        if(index == onboardingAdapter.getItemCount() - 1)
        {
            buttonOnboardingAction.setText("Start");
        }
        else {
            buttonOnboardingAction.setText("Next");
        }

    }
}
