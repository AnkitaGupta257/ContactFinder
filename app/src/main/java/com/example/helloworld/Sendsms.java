package com.example.helloworld;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.widget.Toast;


import java.util.List;

public class Sendsms extends AppCompatActivity {


    public void send(Context context, List list,List list1, String Sendnum)
    {

        String no=Sendnum;
        String msg = list.toString();
        String msg1=list1.toString();
        String messg="";
        msg = msg.substring(1, msg.length()-1);
        msg1= msg1.substring(1, msg1.length()-1);

        for(int i=0;i<list.size();i++)
        {
            messg +=list1.get(i)+":"+list.get(i)+" \n";

        }


        System.out.println("message send"+msg);
        Intent intent=new Intent(context.getApplicationContext(),MainActivity.class);
   //    PendingIntent pi= PendingIntent.getActivity(context.getApplicationContext(), 0, intent,0);

        //Get the SmsManager instance and call the sendTextMessage method to send message
        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage(no, null, messg, null,null);

        System.out.println("send function");

        Toast.makeText(context.getApplicationContext(), "Message Sent successfully12367!",
                Toast.LENGTH_LONG).show();
    }
}


