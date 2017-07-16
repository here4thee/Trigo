package dengdeng.xm.trigo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import dengdeng.xm.trigo.database.Query;
import dengdeng.xm.trigo.domain.AccountInfo;
import io.netopen.hotbitmapgg.library.view.RingProgressBar;
import com.daimajia.numberprogressbar.NumberProgressBar;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.jar.Manifest;

import static android.content.ContentValues.TAG;

public class MonitorActivity extends Activity {

    TextView Mmonitor_target_data;
    TextView Mmonitor_finish_round;
    TextView Mmonitor_speed_data;
    TextView Mmonitor_record_data;

    RingProgressBar Mmonitor_progressBar;
    NumberProgressBar Mmonitor_speed_bar;

    Query Mquery;
    ArrayList<AccountInfo> users;
    AccountInfo user;

    String email;
    int user_index;
    int speed;
    int round;
    int record;
    int target;
    int count;

    MyHandler myHandler;

    // HC-05
    BluetoothAdapter mBluetoothAdapter;
    ConnectThread connectThread;
    ConnectedThread connectedThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);

        myHandler = new MyHandler();
        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        Mmonitor_target_data = (TextView)findViewById(R.id.monitor_target_data);
        Mmonitor_finish_round = (TextView)findViewById(R.id.monitor_finish_round);
        Mmonitor_speed_data = (TextView)findViewById(R.id.monitor_speed_data);
        Mmonitor_record_data = (TextView)findViewById(R.id.monitor_record_data);
        Mmonitor_progressBar = (RingProgressBar) findViewById(R.id.monitor_progressBar);
        Mmonitor_speed_bar = (NumberProgressBar) findViewById(R.id.monitor_speed_bar);

        Mquery = new Query(this);
        users = Mquery.queryAll();
        int i;
        for(i = 0; i < users.size(); i++){
            if(users.get(i).getEmail().equals(email)){
                user_index = i;
                break;
            }
        }
        user = users.get(user_index);

        Mmonitor_target_data.setText(Integer.toString(user.getTarget()));
        Mmonitor_finish_round.setText("0");
        Mmonitor_speed_data.setText("0");
        Mmonitor_record_data.setText(Integer.toString(user.getRecord()));

        //TODO: android.media.MediaPlayer.play
        target = user.getTarget();
        round = 0;
        speed = 0;
        record = user.getRecord();
        Mmonitor_progressBar.setMax(target);
        Mmonitor_speed_bar.setMax(record);
        Mmonitor_speed_bar.setProgressTextVisibility(NumberProgressBar.ProgressTextVisibility.Invisible);
        count = 0;

        // HC-05
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Device does not support Bluetooth.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
            Toast.makeText(this, "Bluetooth enabled.", Toast.LENGTH_SHORT).show();
        }
        Set<BluetoothDevice> pairedDevices  = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices .size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                connectThread = new ConnectThread(device);
                connectThread.start();
                Toast.makeText(this, "Device connected.", Toast.LENGTH_SHORT).show();
                break;
            }
        }  else {
            Toast.makeText(this, "No paired device found.", Toast.LENGTH_SHORT).show();
        }
    }

    // HC-05
    private class ConnectThread extends Thread {
        // TODO: UUID
        private final String MY_UUID = "00001101-0000-1000-8000-00805F9B34FB";
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            BluetoothSocket tmp = null;
            mmDevice = device;

            try {
                tmp = device.createRfcommSocketToServiceRecord(UUID.fromString(MY_UUID));
            } catch (IOException e) {
                e.printStackTrace();
            }
            mmSocket  = tmp;
        }

        public void run() {
            mBluetoothAdapter.cancelDiscovery();
            try {
                mmSocket .connect();
            } catch (IOException e) {
                try {
                    mmSocket.close();
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
                return;
            }
            connectedThread = new ConnectedThread(mmSocket);
            connectedThread.start();
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            int recvChar;
            String recvData = "";

            // TODO: finish receive data part
            while (true) {
                try {
                    recvChar = mmInStream.read();
                    if (recvChar == 13 || recvChar == 10) {
                        if (recvData.length() == 0) {
                            continue;
                        }
                        speed = Integer.parseInt(recvData);
                        if(speed == 0){
                            count = count + 1;
                        }
                        if(count == 100) {
                            break;
                        }
                        if(speed > record){
                            record = speed;
                        }
                        // TODO: modify formula
                        round = round + speed;

                        recvData = "";

                        Message message = new Message();
                        message.what = 0x101;
                        myHandler.sendMessage(message);

                        //TODO: play music
                        //TODO: pause music
                    } else {
                        recvData = recvData + (recvChar - 48);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
            int userTimes = user.getTimes();
            int userRound = user.getRound();
            user.setTimes(userTimes + 1);
            user.setRound(userRound + round);
            user.setRecord(record);
            Mquery.updateTimes(user);
            Mquery.updateRound(user);
            Mquery.updateRecord(user);
            Intent intent1 = new Intent();
            intent1.setClass(MonitorActivity.this,MoreActivity.class);
            intent1.putExtra("email", email);
            startActivity(intent1);
            MonitorActivity.this.finish();
        }

        public void write(byte[] bytes) {
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class MyHandler extends Handler {
        public MyHandler() {
        }

        public MyHandler(Looper L) {
            super(L);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0x101){
                if(round < target) {
                    Mmonitor_progressBar.setProgress(round);
                }
                else {
                    Mmonitor_progressBar.setProgress(target);
                }

                Mmonitor_speed_data.setText(Integer.toString(speed));
                Mmonitor_record_data.setText(Integer.toString(record));
                Mmonitor_finish_round.setText(Integer.toString(round));
                if (speed * 1.0 / record < 0.33) {
                    Mmonitor_speed_bar.setReachedBarColor(Color.rgb(255,179,167));
                }
                else if (speed * 1.0 / record > 0.66) {
                    Mmonitor_speed_bar.setReachedBarColor(Color.rgb(255,33,33));
                }
                else {
                    Mmonitor_speed_bar.setReachedBarColor(Color.rgb(48,223,243));
                }
                Mmonitor_speed_bar.setMax(record);
                Mmonitor_speed_bar.setProgress(speed);
            }
        }
    }
}
