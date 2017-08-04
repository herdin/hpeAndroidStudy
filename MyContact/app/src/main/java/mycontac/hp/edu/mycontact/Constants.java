package mycontac.hp.edu.mycontact;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

/**
 * Created by hanla on 2017-08-04.
 */

public class Constants {
    public static void printDebug(Context context, Object obj) {
        Log.d(context.getString(R.string.debug_tag), obj.toString());
    }//END OF FUNCTION
    public static final Uri cns_uri = Uri.parse("content://hp.cns");
    public static final String _id = "_id";
    public static final String name = "name";
    public static final String tel = "tel";
}//END OF CLASS
