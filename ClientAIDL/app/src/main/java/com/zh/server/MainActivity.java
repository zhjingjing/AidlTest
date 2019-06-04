package com.zh.server;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.FloatProperty;

import com.zh.server.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private IMyAidlInterface myAidlInterface;
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAidlInterface=IMyAidlInterface.Stub.asInterface(service);
            try {
                List<People> list=myAidlInterface.getPeople();
                StringBuilder sb=new StringBuilder();
                for (int i=0;i<list.size();i++){
                    sb.append(list.get(i).name+"\n");
                }
                binding.tvInfo.setText(sb.toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myAidlInterface=null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil. setContentView(this,R.layout.activity_main);
        binding.setPresenter(this);

    }

    public void onGetInfoClicked(){
        Intent intentService = new Intent();
        intentService.setPackage("com.zh.server");
        intentService.setAction("com.zh.server.MyService");
        MainActivity.this.bindService(intentService, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
