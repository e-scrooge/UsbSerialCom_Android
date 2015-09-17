package com.team_sato.usbserialcom_android;

import java.io.*;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.hardware.usb.*;

import com.hoho.android.usbserial.driver.*;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
    private UsbSerialDriver usb = UsbSerialProber.acquire(manager);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        //UsbSerialDriver usb = UsbSerialProber.acquire(manager);

        if(this.usb != null){
            try{
                this.usb.open();
                this.usb.setBaudRate(9600);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            for(int count = 0; count < 5; count++){
                try {
                    this.usb.write("o".getBytes("UTF-8"), 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    this.usb.write("x".getBytes("UTF-8"), 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
