package com.example.dikkate.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;
import com.example.dikkate.R;

import java.util.ArrayList;
import java.util.List;

public class AhoyCardView extends AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("You name it", "Register the problem that needs to be solved.", R.drawable.problem);
        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("We do it", "Dikkate's employee will come and repair the problem with love.", R.drawable.sol);
        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard("Review and Rate", "You can rate the Dikkate's employee and rate them accordingly", R.drawable.review);
        AhoyOnboarderCard ahoyOnboarderCard4 = new AhoyOnboarderCard("Quality repair, fair pricing", "Different methods for paying. Even can pay after observing for 2-3 business days.", R.drawable.payment);

        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard3.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard4.setBackgroundColor(R.color.black_transparent);

        List<AhoyOnboarderCard> pages = new ArrayList<>();

        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);
        pages.add(ahoyOnboarderCard4);

        for (AhoyOnboarderCard page : pages) {
            page.setTitleColor(R.color.white);
            page.setDescriptionColor(R.color.grey_200);
            page.setTitleTextSize(dpToPixels(12, this));
            page.setDescriptionTextSize(dpToPixels(8, this));
            //page.setIconLayoutParams(width, height, marginTop, marginLeft, marginRight, marginBottom);
        }

        setFinishButtonTitle("Finish");
        showNavigationControls(true);
        setGradientBackground();

        //set the button style you created
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setFinishButtonDrawableStyle(ContextCompat.getDrawable(this, R.drawable.rounded_button));
        }

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        setFont(face);

        setOnboardPages(pages);
    }

    @Override
    public void onFinishButtonPressed() {
        Toast.makeText(this, "Finish Pressed", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(AhoyCardView.this,NavigationDrawerActivity.class);
        startActivity(intent);
        finish();
    }
}