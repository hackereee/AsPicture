package com.select.picture.activity;

import com.select.picture.adapter.PictureCursorAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;


@SuppressLint("NewApi") public class BitmapSelectActivity extends Activity implements LoaderCallbacks<Cursor>{

	public static String[] STORE_IMAGES = {
		MediaStore.Images.Media.DISPLAY_NAME,
		MediaStore.Images.Media.LATITUDE,//Í¼Æ¬¾­¶È
		MediaStore.Images.Media.LONGITUDE,//Í¼Æ¬Î³¶È
		MediaStore.Images.Media.DATA,
		MediaStore.Images.Media._ID
	};
	
	private static String[] THUMBNAIL_IMAGES = {
		MediaStore.Images.Thumbnails._ID,
		MediaStore.Images.Thumbnails.DATA,
		MediaStore.Images.Thumbnails.IMAGE_ID,
		MediaStore.Images.Thumbnails.THUMB_DATA
	};
	
	PictureCursorAdapter pictureCursorAdapter;
	
	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		CursorLoader loader = new CursorLoader(this,
				MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
				THUMBNAIL_IMAGES, null, null, null);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		
	} 

}
