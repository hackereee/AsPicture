package com.select.picture.view;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import com.select.picture.R;
import com.select.picture.interf.OnLoadBitmapListener;
import com.select.picture.utils.Bimp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

@EViewGroup(R.layout.picture_view)
public class MyPictureView extends RelativeLayout{
	
	@ViewById(R.id.imageView)
	ImageView imageView;
	@ViewById(R.id.checkBox)
	CheckBox checkBox;
	
	String imagePath;
	
	
	@SuppressLint("NewApi")
	public MyPictureView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyPictureView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyPictureView(Context context) {
		super(context);
	}
	
	@Click(R.id.checkBox)
	void checkBoxClick(View v){
		CheckBox c = (CheckBox)v;
		if(c.isChecked()){
			if(imagePath != null)
			Bimp.addSelectBmpPath(imagePath);
		}else{
			if(imagePath != null)
				Bimp.removeSelectBmpPath(imagePath);
		}
	}
	
	@AfterInject
	void afterJcet(){
		
	}
	
	@AfterViews
	void afterView(){
	}
	
	public MyPictureView bind(String thumbnailPath,String _imageID){
		return this;
	}
	
	public MyPictureView bind(String imagePath){
		Bimp.forSquareImages(imagePath, new OnLoadBitmapListener() {
			
			@Override
			public void loadBitmapComplete(Bitmap bitmap) {
				if(imageView != null){
					imageView.setImageBitmap(bitmap);
				}
			}
		});
		this.imagePath = imagePath;
		checkBox.setChecked(Bimp.isExistSeletBmp(imagePath));
		return this;
	}

}
