package com.example.helloworld;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.content.Context;
import android.content.ContentResolver;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class searchbyname extends AppCompatActivity {

    String contactName;
    String contactNumber;
    List<String> list = new ArrayList<String>();
    String has_phone;
    String id;
    String name;
    List<String> list1 = new ArrayList<String>();




    public void serachname(Context context,String message,String senderNum) {
        Log.i("SmsReceiver", " message: " + message);
        System.out.println("Message12345 " + message);
        System.out.println("serchbyanme 1");
        Log.i("serachbyname", "in serch function");
       /* Cursor contactLookupCursor =
                getContentResolver().query(
                        Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                                Uri.encode("message")),
                        new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup.NUMBER},
                        null,
                        null,
                        null);


        try {
            while (contactLookupCursor.moveToNext()) {
                contactName = contactLookupCursor.getString(contactLookupCursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup.DISPLAY_NAME));
                contactNumber = contactLookupCursor.getString(contactLookupCursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup.NUMBER));
            }

            System.out.println("contact name ,contactnumber" + contactName + contactNumber);


        } finally {
            contactLookupCursor.close();
        }*/

        String contname = message;
        Uri lkup = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI, contname);
        System.out.println("serchbyanme 47");


       /* Cursor idCursor =context.getContentResolver().query(lkup, new String[]{
                        ContactsContract.CommonDataKinds.Phone.NUMBER,
                        ContactsContract.CommonDataKinds.Phone.TYPE },
                null, null, null);*/
        Cursor idCursor = context.getContentResolver().query(lkup, null, null, null, null);
        System.out.println("serchbyanme 49");
        while (idCursor.moveToNext()) {
            id = idCursor.getString(idCursor.getColumnIndex(ContactsContract.Contacts._ID));
            String key = idCursor.getString(idCursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
            name = idCursor.getString(idCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            has_phone = idCursor.getString(idCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
            boolean flag=name.replace(" ", "").equalsIgnoreCase(message.replace(" ", ""));
            System.out.println("flag:"+flag);
         // if (flag) {
                if (!has_phone.endsWith("0")) {
                    System.out.println("abcdef" + name);

                    Cursor phones = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
                    System.out.println("abcdef" + name);
                    while (phones.moveToNext()) {
                        String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Log.i("Mobile number", phoneNumber);
                        System.out.println("Mobile number" + phoneNumber);
                        boolean i = list.contains(phoneNumber);
                        System.out.println("Listtt:" + list);
                        // System.out.println("bool i:" +i);
                        phoneNumber = phoneNumber.replaceAll("\\s", "");
                        char first = phoneNumber.charAt(0);
                        if (first == '+') {
                            phoneNumber = phoneNumber.substring(3);
                        }
                        if (!list.contains(phoneNumber)) {
                            System.out.println("bool i:" + i);
                            list1.add(name);
                            list.add(phoneNumber);
                        }


                        //   Sendsms s=new Sendsms();
                        //     s.send(context,phoneNumber,senderNum);
                    }

                    // Sendsms s=new Sendsms();
                    //s.send(context,list,senderNum);
                    //System.out.println("list:"+list);
                    phones.close();
                    // GetPhoneNumber(context,id);
                }

                // int number1 = idCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                //String num=idCursor.getString(number1);
//            String number=idCursor.getString(0);


                System.out.println("serchbyanme 54");
                Log.d("searchbyname", "search: " + id + " name: " + name + "has_phone" + has_phone);
         //       // idCursor.close();
         //  }
        }

       Sendsms s=new Sendsms();

        System.out.println("send function called");
        s.send(context, list,list1, senderNum);

        System.out.println("list:"+list);
     // System.out.println("serchbyanme 57");
       // System.out.println("list:"+list);
        idCursor.close();


    }


      /*  public String GetPhoneNumber(Context context,String id)
        {

            System.out.println("abcdefghghkj");
            String number = "";
            Cursor phones = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone._ID + " = " + id, null, null);
            System.out.println("abcdefghghkj12");

            if(phones.getCount() > 0)
            {
                System.out.println("abcdefghghkj1234");
                while(phones.moveToNext())
                {
                    System.out.println("abcdefghghkj675");
                    number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }
                System.out.println("numberwed" +number);
           }
            System.out.println("abcdefghghkj 94 ");
            phones.close();

            return number;
        }
*/
    }





