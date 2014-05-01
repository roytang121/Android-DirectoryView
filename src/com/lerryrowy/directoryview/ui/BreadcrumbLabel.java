package com.lerryrowy.directoryview.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.View;

import com.lerryrowy.directoryview.Utils;

public class BreadcrumbLabel extends View{
	
	private Paint mTextPaint;
	private Paint mBgPaint;
	int w,h;
	int paddingLeft, paddingRight;
	private String path;
	private int mLabelWidth;
	
	public BreadcrumbLabel(Context context) {
		super(context);
		init();
	}
	
	public BreadcrumbLabel(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
		init();
	}
	
	public BreadcrumbLabel(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	
	private void init(){
		mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mTextPaint.setColor(Color.WHITE);
		mTextPaint.setTextSize(dp(14));	
		mTextPaint.setTextAlign(Align.CENTER);
		
		mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mBgPaint.setColor(Color.parseColor("#00adef"));
		
		paddingLeft = paddingRight = dp(10);
		
		//inEditMode
		if(isInEditMode()){
			path = "Text Label";
		}
	}
	
	private int dp(int n){
		return Utils.DpToPx(getContext(), n);
	}
	
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		w = measureWidth(widthMeasureSpec);
		h = measureHeight(heightMeasureSpec);
		this.setMeasuredDimension(w, h);
	}

	private int measureWidth(int widthMeasureSpec) {
		int mode = MeasureSpec.getMode(widthMeasureSpec);
		int size = MeasureSpec.getSize(widthMeasureSpec);
		int result = 0;
		if(mode == MeasureSpec.EXACTLY){
			return size;
		} else {
			mLabelWidth = (int) mTextPaint.measureText(path);
			result = mLabelWidth + getPaddingLeft() + getPaddingRight() + paddingLeft + paddingRight;
		}
		return result;
	}
	
	public void setPath(String path){
		this.path = path;
	}
	
	private int measureHeight(int heightMeasureSpec){
		return MeasureSpec.getSize(heightMeasureSpec);
	}
	
	@Override
	public void onDraw(Canvas canvas){
		canvas.drawRect(0, 0, w, h, mBgPaint);
		canvas.drawText(path, w/2, h/2 + measureTextHeight(mTextPaint)/2 - mTextPaint.descent(), mTextPaint);
	}
	
	private int measureTextHeight(Paint paint){
		return (int) (-paint.ascent() + paint.descent()) + getPaddingTop()
        + getPaddingBottom();
	}
}
