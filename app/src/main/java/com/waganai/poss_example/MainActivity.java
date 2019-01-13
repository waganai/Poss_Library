package com.waganai.poss_example;

import android.Manifest;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.File;

import poss.Config;
import poss.Poss;
import poss.User;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    //public native String stringFromJNI();

    private void init() {
        final TextView tv = (TextView) findViewById(R.id.sample_text);

        XPermissionUtils.requestPermissionsForActivity(MainActivity.this,
                1,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_NETWORK_STATE},
                new XPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {

                    }

                    @Override
                    public void onPermissionDenied() {
                        finish();
                    }
                });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Config config = Poss.createDefaultConfig();

                    config.setTCPPort(8068);
                    config.setUDPPort(8068);
                    config.setRPCPort(18068);

                    config.setZone(2);

                    File cacheDir = new File(Environment.getExternalStorageDirectory().getPath() +
                            "/POSS_Cache");

                    boolean directoryExist;
                    directoryExist = cacheDir.exists();
                    if (!directoryExist) {
                        directoryExist = cacheDir.mkdir();
                    }

                    if (directoryExist) {
                        config.setDir(cacheDir.getAbsolutePath());

                        config.setBootstrap("[\n" +
                                "  {\n" +
                                "    \"Name\": \"aws-bootstrap\",\n" +
                                "    \"IP\": \"54.202.181.27\",\n" +
                                "    \"TCPPort\": 8020,\n" +
                                "    \"UDPPort\": 8020,\n" +
                                "    \"PeerID\": \"\"\n" +
                                "  },\n" +
                                "  {\n" +
                                "    \"Name\": \"ali-bootstrap\",\n" +
                                "    \"IP\": \"47.110.88.167\",\n" +
                                "    \"TCPPort\": 8020,\n" +
                                "    \"UDPPort\": 8020,\n" +
                                "    \"PeerID\": \"\"\n" +
                                "  }\n" +
                                "]");

                        config.getQosServerConfig().setEnable(true);
                        config.getQosServerConfig().setNetwork("udp");
                        config.getQosServerConfig().setAddr("192.168.50.208:9090");//if the address is incorrect, the qoslog will saved in local
                        config.getQosServerConfig().setTag("ppioqos");
                        config.getQosServerConfig().setDir(Environment.getExternalStorageDirectory().getPath() +
                                "/POSS_Cache/qoslog/");

                        final User user = Poss.createUser(config);

                        user.init("0xAB441716AF7C56DA8D76991D2C6C451C2451D919045A54E4506E0E1891712E35");

                        user.startDaemon();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    tv.setText("private key : \n" + user.exportWalletKey() +
                                            "\n\naddress: \n" + user.exportWalletAccount());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } else {
                    }
                } catch (Exception e) {
                    Log.e(TAG, "login error : " + e.getMessage());

                    e.printStackTrace();
                }
            }
        }).start();
    }
}
