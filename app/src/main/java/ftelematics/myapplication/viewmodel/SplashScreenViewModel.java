package ftelematics.myapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;

public class SplashScreenViewModel extends AndroidViewModel {


    private final Context mContext;
    private Handler handler;
    private MutableLiveData<Boolean> openHome;

    public SplashScreenViewModel(@NonNull Application application) {
        super(application);
        mContext=application;
    }


    public MutableLiveData<Boolean> openHomeScreen() {
        openHome = new MutableLiveData<>();
        return openHome;
    }

     public void openHome()
     {
         handler=new Handler();
         handler.postDelayed(new Runnable() {
             @Override
             public void run() {
                 openHome.postValue(true);
             }
         },3000);
     }


}