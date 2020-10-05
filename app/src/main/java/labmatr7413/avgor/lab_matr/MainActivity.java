package labmatr7413.avgor.lab_matr;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.button.MaterialButton;

/*
inmobi
 */
//import com.inmobi.ads.InMobiAdRequestStatus;
//import com.inmobi.ads.InMobiInterstitial;
//import com.inmobi.ads.listeners.InterstitialAdEventListener;
//import com.inmobi.sdk.InMobiSdk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final int PRICE_GAUSS = 3;
    private static final int PRICE_GAUSS_DETAILED = 5;
    private static final int BASE_COINS = 15;
    private static final int REWARD_COINS_FOR_AD = 15;


    Spinner spinnerSizeSystem;
    LinearLayout systemLinearLayout;
    LinearLayout matrixGaussLinearLayout;
    SharedPreferences sharedPreferences;

    TextView textViewNumCoins;

    LinearLayout matrixTextAnswers;
    Context context;

    DrawSystem drawSystem;
    System system;

    MaterialButton buttonGauss;
    MaterialButton buttonDetailedGauss;
    MaterialButton buttonShowAd;


    /*
    inmoby
     */
//    InMobiInterstitial interstitialAd;
//    InterstitialAdEventListener adEventListener;
    RewardedAd rewardedAd;
    RewardedAdLoadCallback rewardedAdCallback;

    int currentCoins;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplication().getApplicationContext();

        SizeSystem.SIZES_ARRAY_STRING[0] = getString(R.string.select_value);

        systemLinearLayout = findViewById(R.id.linear_layout_system_picture);
        spinnerSizeSystem = findViewById(R.id.spinner_size_system);

        ArrayAdapter<Spanned> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, SizeSystem.getSystem());

        adapter.setDropDownViewResource(R.layout.spinner_item);

        buttonGauss = findViewById(R.id.button_method_Gauss);
        buttonDetailedGauss = findViewById(R.id.button_detailed_method_Gauss);
        buttonShowAd = findViewById(R.id.buttonShowAd);

        matrixGaussLinearLayout = findViewById(R.id.matrix_Gauss);

        textViewNumCoins = findViewById(R.id.textViewNumCoins);

        matrixTextAnswers = findViewById(R.id.answer_Gauss);
        spinnerSizeSystem.setAdapter(adapter);
        spinnerSizeSystem.setSelection(0);

        initSharedPreferences();
        forFirstLaunch();
        coinsInit();

        setButtonShowAdActive(false);

        /* inmobi */
//        inmobiInit();

        /* admob */
        admobInit();
        createAndLoadRewardedAd();

        buttonsInit();

        spinnerSizeSystem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                drawSystem = new DrawSystem(context, systemLinearLayout, position + 1);
                if (position != 0) {
                    drawSystem.draw();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        updateTextViewCoins();

    }

    /*  admob */
    private void admobInit() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

    }

    private void createAndLoadRewardedAd(){
        rewardedAd = new RewardedAd(this, "ca-app-pub-4485041003507930/3116104031");
        /*
        for tests
         */
        //rewardedAd = new RewardedAd(this, "ca-app-pub-3940256099942544/5224354917");
        rewardedAdCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                Toast.makeText(getApplicationContext(), "success!", Toast.LENGTH_SHORT).show();
                setButtonShowAdActive(true);
            }

            @Override
            public void onRewardedAdFailedToLoad(LoadAdError adError) {
                Toast.makeText(getApplicationContext(), "failed!", Toast.LENGTH_SHORT).show();
                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    rewardedAd.loadAd(new AdRequest.Builder().build(), rewardedAdCallback);
                });
                thread.run();
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), rewardedAdCallback);

    }

    private void buttonsInit() {
        buttonGauss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canShowGauss()) {
                    if (showGauss()) {
                        currentCoins -= PRICE_GAUSS;
                        updateTextViewCoins();
                    }
                } else {
                    ToastMessages.noEnoughCoinsForGaussMethod(getBaseContext());
                }
            }

        });

        buttonDetailedGauss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (canShowDetailedGauss()) {
                    if (showDetailedGauss()) {
                        currentCoins -= PRICE_GAUSS_DETAILED;
                        updateTextViewCoins();
                    }
                } else {
                    ToastMessages.noEnoughCoinsForDetailedGaussMethod(getBaseContext());
                }
            }
        });

        /*inmobi*/
        /*
        buttonShowAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd.isReady()) {
                    interstitialAd.show();
                } else {
                    ToastMessages.errorShowRewardedAd(getBaseContext());
                }
            }
        });
        */

        /*
        admob
         */
        buttonShowAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rewardedAd.isLoaded()) {
                    Activity activityContext = MainActivity.this;
                    RewardedAdCallback adCallback = new RewardedAdCallback() {
                        @Override
                        public void onRewardedAdOpened() {
                            setButtonShowAdActive(false);
                        }

                        @Override
                        public void onRewardedAdClosed() {
                           createAndLoadRewardedAd();
                        }

                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem reward) {
                            currentCoins += REWARD_COINS_FOR_AD;
                            updateTextViewCoins();
                        }

                        @Override
                        public void onRewardedAdFailedToShow(AdError adError) {
                        }
                    };
                    rewardedAd.show(activityContext, adCallback);
                } else {
                    Log.d("TAG", "The rewarded ad wasn't loaded yet.");
                }
            }
        });


    }

    private void setButtonShowAdActive(boolean isActive) {
        buttonShowAd.setEnabled(isActive);
    }

    private boolean showGauss() {
        try {
            system = new System(drawSystem.getIdEditTexts().length, drawSystem.getIdEditTexts()[0].length);
            parseEditTexts(drawSystem);
            MethodGauss methodGauss = new MethodGauss(system.systemCoefficients);
            methodGauss.Gauss();
            DrawMatrix drawMatrixGauss = new DrawMatrix(
                    context,
                    matrixGaussLinearLayout,
                    matrixTextAnswers,
                    methodGauss);
            drawMatrixGauss.draw();
        } catch (Exception e) {
            ToastMessages.dataError(context);
            return false;
        }
        return true;
    }

    private void updateTextViewCoins() {
        textViewNumCoins.setText(String.valueOf(currentCoins));
    }

    private boolean canShowGauss() {
        return (currentCoins - PRICE_GAUSS) >= 0;
    }

    private boolean canShowDetailedGauss() {
        return (currentCoins - PRICE_GAUSS_DETAILED) >= 0;
    }

    private boolean showDetailedGauss() {
        try {
            system = new System(drawSystem.getIdEditTexts().length, drawSystem.getIdEditTexts()[0].length);
            parseEditTexts(drawSystem);
            MethodGauss methodGauss = new MethodGauss(system.systemCoefficients);
            methodGauss.Gauss();
            DrawMatrix drawMatrixGauss = new DrawMatrix(
                    context,
                    matrixGaussLinearLayout,
                    matrixTextAnswers,
                    methodGauss);
            drawMatrixGauss.drawDetailed();
        } catch (Exception e) {
            ToastMessages.dataError(context);
            return false;
        }
        return true;
    }

    void parseEditTexts(DrawSystem drawSystem) {
        for (int i = 0; i < system.getSystemCoefficients().length; i++) {
            for (int j = 0; j < system.getSystemCoefficients()[0].length; j++) {
                EditText editText = (EditText) findViewById(drawSystem.getIdEditTexts()[i][j]);
                Fraction fraction = new Fraction(Integer.parseInt(editText.getText().toString()), 1);
                system.setSystemCoefficients(fraction, i, j);
            }
        }
    }

    private void forFirstLaunch() {
        boolean isFirstLaunch = sharedPreferences.getBoolean(getString(R.string.sp_is_first_launch), true);
        if (isFirstLaunch) {
            currentCoins = BASE_COINS;
            sharedPreferences.edit().putInt(getString(R.string.sp_num_coins), currentCoins).apply();
            sharedPreferences.edit().putBoolean(getString(R.string.sp_is_first_launch), false).apply();
        }
    }

    private void coinsInit() {
        currentCoins = sharedPreferences.getInt(getString(R.string.sp_num_coins), BASE_COINS);
    }

    private void initSharedPreferences() {
        sharedPreferences = getSharedPreferences(getString(R.string.sp_app), MODE_PRIVATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sharedPreferences.edit().putInt(getString(R.string.sp_num_coins), currentCoins).apply();
    }

    /* inmobi */

//    private void inmobiInit(){
//        inmobiSdkInit();
//        adEventListenerInit();
//        inmobiInterstitialInit();
//        interstitialAd.load();
//    }
//
//    private void inmobiSdkInit(){
//        JSONObject consentObject = new JSONObject();
//        try {
//            // Provide correct consent value to sdk which is obtained by User
//            consentObject.put(InMobiSdk.IM_GDPR_CONSENT_AVAILABLE, true);
//            // Provide 0 if GDPR is not applicable and 1 if applicable
//            consentObject.put("gdpr", "0");
//            // Provide user consent in IAB format
//            consentObject.put(InMobiSdk.IM_GDPR_CONSENT_IAB, "CMP");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        InMobiSdk.init(this, "b14147aa5f604b08b4a728438ee1f4ee", consentObject);
//    }
//
//    private void inmobiInterstitialInit(){
//        interstitialAd = new InMobiInterstitial(this, 1575170449336L, adEventListener);
//    }
//
//    private void adEventListenerInit(){
//        adEventListener = new InterstitialAdEventListener() {
//
//            @Override
//            public void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial) {
//                super.onAdLoadSucceeded(inMobiInterstitial);
//                setButtonShowAdActive(true);
////                mToaster.messageWarningBlack("onAdLoadSucceeded");
//            }
//
//            @Override
//            public void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus) {
//                super.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
////                Toast.makeText(getBaseContext(), "failed",Toast.LENGTH_SHORT).show();
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    public void run() {
//                        if (!interstitialAd.isReady()) {
//                            interstitialAd.load();
//                        }
//                    }
//                }, 2000); //specify the number of milliseconds
//
////                mToaster.messageWarningBlack("onAdLoadFailed");
//            }
//
//            @Override
//            public void onAdReceived(InMobiInterstitial inMobiInterstitial) {
////                mToaster.messageWarningBlack("onAdReceived");
//                super.onAdReceived(inMobiInterstitial);
////                Toast.makeText(getBaseContext(), "received",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAdClicked(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
//                super.onAdClicked(inMobiInterstitial, map);
//            }
//
//            @Override
//            public void onAdWillDisplay(InMobiInterstitial inMobiInterstitial) {
//                super.onAdWillDisplay(inMobiInterstitial);
//            }
//
//            @Override
//            public void onAdDisplayed(InMobiInterstitial inMobiInterstitial) {
//                super.onAdDisplayed(inMobiInterstitial);
//            }
//
//            @Override
//            public void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial) {
//                super.onAdDisplayFailed(inMobiInterstitial);
//            }
//
//            @Override
//            public void onAdDismissed(InMobiInterstitial inMobiInterstitial) {
//                super.onAdDismissed(inMobiInterstitial);
//                interstitialAd.load();
//            }
//
//            @Override
//            public void onUserLeftApplication(InMobiInterstitial inMobiInterstitial) {
//                super.onUserLeftApplication(inMobiInterstitial);
//            }
//
//            @Override
//            public void onRewardsUnlocked(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
//                super.onRewardsUnlocked(inMobiInterstitial, map);
//                currentCoins += REWARD_COINS_FOR_AD;
//                updateTextViewCoins();
//                setButtonShowAdActive(false);
//            }
//
//            @Override
//            public void onRequestPayloadCreated(byte[] bytes) {
//                super.onRequestPayloadCreated(bytes);
//            }
//
//            @Override
//            public void onRequestPayloadCreationFailed(InMobiAdRequestStatus inMobiAdRequestStatus) {
//                super.onRequestPayloadCreationFailed(inMobiAdRequestStatus);
//            }
//        };
//    }

}
