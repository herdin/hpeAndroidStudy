package hp.edu.mypaint;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by hanla on 2017-08-02.
 */

public class Pointer {
    public float x;
    public float y;
    public boolean isDraw;
    public Paint paint;

    public Pointer(float x, float y, int color, float strokeWidth, boolean isDraw) {
        this.x = x;
        this.y = y;
        this.isDraw = isDraw;
        this.paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
    }//END OF FUNCTION

}//END OF CLASS
