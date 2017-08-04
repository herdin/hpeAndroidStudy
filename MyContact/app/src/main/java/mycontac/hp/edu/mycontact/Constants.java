package mycontac.hp.edu.mycontact;

import android.content.Context;
import android.util.Log;

/**
 * Created by hanla on 2017-08-04.
 */

public class Constants {
    public static void printDebug(Context context, Object obj) {
        Log.d(context.getString(R.string.debug_tag), obj.toString());
    }//END OF FUNCTION
}//END OF CLASS
