package hello.android.hp.edu.screen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("####DEBUG", "MyReceiver::onReceive()");

        //아래는 메세지처리
        Bundle bundle = intent.getExtras();
        Object[] msgs = (Object[]) bundle.get(context.getString(R.string.smsKey));
        SmsMessage[] smsMessages = new SmsMessage[msgs.length];
        int n=0;
        for(Object msg : msgs)
            smsMessages[n++] = SmsMessage.createFromPdu((byte[])msg);
        Toast.makeText(context, "SMS MESSAGE : " + smsMessages[0].getMessageBody(), Toast.LENGTH_SHORT).show();
    }//END OF FUNCTION

}//END OF CLASS
