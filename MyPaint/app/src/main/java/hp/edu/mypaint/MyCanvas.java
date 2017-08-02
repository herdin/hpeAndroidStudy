package hp.edu.mypaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hanla on 2017-08-02.
 */

public class MyCanvas extends View {

    private String myText;
    private float startX;
    private float startY;
    private float stopX;
    private float stopY;


    public MyCanvas(Context context, @Nullable AttributeSet attrs) { //layout xml 에서 설정된 값이 attrs 로 넘어오는듯
        super(context, attrs);
        this.myText = attrs.getAttributeValue(null, "myText");
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN :
                        startX = stopX = motionEvent.getX();
                        startY = stopY = motionEvent.getY();
                        invalidate();

                        return true;
                    case MotionEvent.ACTION_MOVE :
                        startX = stopX;
                        stopX = motionEvent.getX();
                        startY = stopY;
                        stopY = motionEvent.getY();
                        invalidate();
                        return true;
                    case MotionEvent.ACTION_UP :
                        startX = stopX;
                        stopX = motionEvent.getX();
                        startY = stopY;
                        stopY = motionEvent.getY();
                        invalidate();
                        return true;
                    default : break;
                }
                return false;
            }
        });
    }//END OF FUNCTION

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.RED);
        p.setStrokeWidth(3F);
        canvas.drawText(this.myText, 150, 150, p);
        canvas.drawLine(this.startX, startY, stopX, stopY, p);
    }//END OF FUNCTION

    public String getMyText() {
        return this.myText;
    }//END OF FUNCTION
}//END OF CLASS