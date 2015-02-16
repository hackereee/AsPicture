package com.select.picture.utils;

import java.util.ArrayList;

import com.select.picture.interf.OnLoadBitmapListener;
import com.select.picture.manager.BitmapGetSyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;

public class Bimp {

	public static ArrayList<String> selectBmpPaths = new ArrayList<String>();
	
	public static void forSquareThumbnails(String thumbnailPath,String _imageID){
		
	}
	
	public static void forSquareImages(String imagePath,OnLoadBitmapListener listener){
		//异步执行图片缩略图的得到
		BitmapGetSyncTask task = new BitmapGetSyncTask(listener);
		task.execute(imagePath);
	}
	
	public static void addSelectBmpPath(String path){
		if(selectBmpPaths == null){
			selectBmpPaths = new ArrayList<String>();
		}
		selectBmpPaths.add(path);
	}
	
	public static void removeSelectBmpPath(String path){
		if(selectBmpPaths != null){
			selectBmpPaths.remove(path);
		}
	}
	
	public static Boolean isExistSeletBmp(String path){
		Boolean isExist = false;
		if(path != null)
			isExist =  selectBmpPaths.contains(path) ? true : false;
		return isExist;
	}
}