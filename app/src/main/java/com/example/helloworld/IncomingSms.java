package com.example.helloworld;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
import android.content.Context;
import android.content.ContentResolver;

public class IncomingSms extends BroadcastReceiver {

    // Get the object of SmsManager

    String contactName;
    String contactNumber;

    public void onReceive(Context context, Intent intent) {

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
        String message1="";

        try {


            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    String[] splitStr = message.split("\\s+");
                    System.out.println("split[0" );
                    System.out.print(splitStr[0]);
                    System.out.println("split[1" );
                    System.out.print(splitStr[1]);
                    if (splitStr[0].equalsIgnoreCase("sendname")) {


                        int length = splitStr.length;
                        System.out.println(length);


                        for(i=1;i<length;i++)
                            message1 += splitStr[i]+" ";

                        message=message1;




                        Log.i("SmsReceiver12", "senderNummm: " + senderNum + "; message: " + message);
                        Log.i("SmsReceiver12", "senderNummm: " + senderNum + "; message: " + message);
                        System.out.println("Message " + message);
                        System.out.println("serach by name called");
                        Log.d("ncomingsms", message);
                        Log.d("incomingsms", "serach by name called");

                        // Show Alert
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context,
                                "senderNum123: " + senderNum + ", message: " + message, duration);
                        toast.show();
                        if (message.matches(".*[a-zA-Z]+.*")) {
                            searchbyname s = new searchbyname();
                            System.out.println("serach by name123 called");

                            s.serachname(context, message, senderNum);

                       }


                    }
                }

                }
            }
        catch(Exception e){
                Log.e("SmsReceiver", "Exception smsReceiver" + e);

            }

        }



}