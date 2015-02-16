package com.select.picture.adapter;

import com.select.picture.view.MyPictureView;
import com.select.picture.view.MyPictureView_;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PictureCursorAdapter extends CursorAdapter{

	final static int PHOTO = 0;
	final static int PICTURE = 1;
	
	Boolean isNullCursor;
	
	Context mContext;
	LayoutInflater inflater;
	
	@Override
	public Object getItem(int position) {
		return super.getItem(position);
	}

	@Override
	public int getItemViewType(int position) {
//		if(position == 0){
//			return PHOTO;
//		}else{
//			return PICTURE;
//		}
		return isNullCursor ? PHOTO : PICTURE;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	public PictureCursorAdapter(Context context){
		super(context, null, true);
		this.isNullCursor = true;
		this.mContext = context;
		inflater = LayoutInflater.from(mContext);
	}
	
	public PictureCursorAdapter(Context context, Cursor c) {
		super(context, c);
	}
	
	public PictureCursorAdapter(Context context, Cursor c, boolean autoRequery) {
		super(context, c, autoRequery);
	}

	public PictureCursorAdapter(Context context, Cursor c, int flags) {
		super(context, c, flags);
	}
	

	

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		int position = cursor.getPosition();
		int type = getItemViewType(position);
		switch (type) {
		case PHOTO:
			TextView text = (TextView)view;
			text.setText("拍照");
			break;
		case PICTURE:
			MyPictureView pictureView = (MyPictureView)view;
			if(cursor.moveToFirst()){
				int dataIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
				//获取到图片路径
				String imagePath = cursor.getString(dataIndex);
				//异步加载图片
				pictureView.bind(imagePath);
			}
			break;
		default:
			break;
		}
		
	}

	@Override
	public int getCount() {
		return super.getCount()+1;
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
		int position = cursor.getPosition();//游标的行位置
		//第一次绑定adapter时候，cursor是null的，就用这个来区分拍照还是图片
		isNullCursor = cursor == null ? true : false;
	    int type = getItemViewType(position);
	    View newView = null;
	    switch(type){
	    case PHOTO:
	    	newView = new TextView(mContext);
	    	break;
	    case PICTURE:
	    	newView = MyPictureView_.build(mContext);
	    	break;
	    }
	    return newView;
	}

}
