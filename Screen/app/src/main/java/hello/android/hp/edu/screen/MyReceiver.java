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
        Log.d(context.getString(R.string.debugId), this.getClass().getSimpleName() + "::onReceive()");

        //아래는 메세지처리
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            Object[] msgs = (Object[]) bundle.get(context.getString(R.string.smsKey)); //문자메세지가 왔을경우, 메세지를 봅아서 토스트로 보여준다.
            SmsMessage[] smsMessages = new SmsMessage[msgs.length];
            int n=0;
            for(Object msg : msgs)
                smsMessages[n++] = SmsMessage.createFromPdu((byte[])msg);
            Toast.makeText(context, "SMS MESSAGE : " + smsMessages[0].getMessageBody(), Toast.LENGTH_SHORT).show();
        }
    }//END OF FUNCTION

}//END OF CLASS
