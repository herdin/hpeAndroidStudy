package hp.edu.mypaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by hanla on 2017-08-02.
 */

public class MyCanvas extends View {

    private String myText;
//    private float startX;
//    private float startY;
//    private float stopX;
//    private float stopY;
    private ArrayList<Pointer> list = new ArrayList<>();
    private Paint paint;

    public MyCanvas(final Context context, @Nullable AttributeSet attrs) { //layout xml 에서 설정된 값이 attrs 로 넘어오는듯
        super(context, attrs);
        this.myText = attrs.getAttributeValue(null, "myText");

        this.paint = new Paint();
        this.paint.setColor(Color.BLACK);

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Pointer pointer;
                Log.d(context.getString(R.string.debugTag), "MyCanvas.onTouch : COLOR(" + MyCanvas.this.paint.getColor() + ") STROK WIDTH(" + MyCanvas.this.paint.getStrokeWidth() + ")");

                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN :
                        pointer = new Pointer(motionEvent.getX(), motionEvent.getY(), MyCanvas.this.paint.getColor(), MyCanvas.this.paint.getStrokeWidth(), false);
                        MyCanvas.this.list.add(pointer);
                        invalidate(); //onDraw 를 호출
                        return true;
                    case MotionEvent.ACTION_MOVE :
                    case MotionEvent.ACTION_UP :
                        pointer = new Pointer(motionEvent.getX(), motionEvent.getY(), MyCanvas.this.paint.getColor(), MyCanvas.this.paint.getStrokeWidth(), true);
                        MyCanvas.this.list.add(pointer);
                        invalidate(); //onDraw 를 호출
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
        for(int i=0; i<list.size(); i++) {
            if(list.get(i).isDraw) {
                canvas.drawLine(list.get(i - 1).x, list.get(i - 1).y, list.get(i).x, list.get(i).y, list.get(i).paint);
            }
        }
//        canvas.drawLine(this.startX, startY, stopX, stopY, p);
    }//END OF FUNCTION

    public String getMyText() {
        return this.myText;
    }//END OF FUNCTION

    public void setPaintColor(int color) {
        this.paint.setColor(color);
    }//END OF FUNCTION

    public void setStrokeWidth(float strokeWidth) {
        this.paint.setStrokeWidth(strokeWidth);
    }
}//END OF CLASS