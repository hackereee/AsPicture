package com.select.picture.adapter;

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
	
	Context mContext;
	LayoutInflater inflater;
	
	@Override
	public Object getItem(int position) {
		return super.getItem(position);
	}

	@Override
	public int getItemViewType(int position) {
		if(position == 0){
			return PHOTO;
		}else{
			return PICTURE;
		}
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	public PictureCursorAdapter(Context context){
		super(context, null, true);
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
		String thumbnailPath = null;
		if(cursor.moveToFirst()){
		int thumbnailIndex = cursor.getColumnIndex(MediaStore.Images.Thumbnails.DATA);
		thumbnailPath = cursor.getString(thumbnailIndex);
		}
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
		int position = cursor.getPosition();//游标的行位置
	    int type = getItemViewType(position);
	    View newView = null;
	    switch(type){
	    case PHOTO:
	    	newView = new TextView(mContext);
	    	break;
	    case PICTURE:
	    	break;
	    }
	    return newView;
	}

}
