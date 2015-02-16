package com.select.picture.manager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;

import com.select.picture.interf.OnLoadBitmapListener;

 /*后台执行得到正方形的bitmap*/
	  public class BitmapGetSyncTask extends AsyncTask<String, Integer, Bitmap>{

		OnLoadBitmapListener listener_;  
		  
		public BitmapGetSyncTask(OnLoadBitmapListener listener){
			this.listener_ = listener;
		}
		

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			if(params != null){  
				String path = params.toString();
				Options options = new Options();
//				options.inPreferQualityOverSpeed = true;
				options.inPreferredConfig = Bitmap.Config.RGB_565;
				//软引用机制,注意：下面两个属性必须同时使用
				options.inPurgeable = true;
				options.inInputShareable = true;
				
				Bitmap bitmap = BitmapFactory.decodeFile(path,options);
				//得到图片中心方形图
				bitmap = centerSquareScaleBitmap(bitmap, 50);
			}
			return null;
		}
		
		 
		@Override
		protected void onPostExecute(Bitmap result) {
			if(listener_ != null){
				listener_.loadBitmapComplete(result);
			}
			super.onPostExecute(result);
		}
		
		
		/**
	    
		   * @param bitmap      原图
		   * @param edgeLength  希望得到的正方形部分的边长
		   * @return  缩放截取正中部分后的位图。
		   */
		  public static Bitmap centerSquareScaleBitmap(Bitmap bitmap, int edgeLength)
		  {
		   if(null == bitmap || edgeLength <= 0)
		   {
		    return  null;
		   }
		                                                                                 
		   Bitmap result = bitmap;
		   int widthOrg = bitmap.getWidth();
		   int heightOrg = bitmap.getHeight();
		                                                                                 
		   if(widthOrg > edgeLength && heightOrg > edgeLength)
		   {
		    //压缩到一个最小长度是edgeLength的bitmap
		    int longerEdge = (int)(edgeLength * Math.max(widthOrg, heightOrg) / Math.min(widthOrg, heightOrg));
		    int scaledWidth = widthOrg > heightOrg ? longerEdge : edgeLength;
		    int scaledHeight = widthOrg > heightOrg ? edgeLength : longerEdge;
		    Bitmap scaledBitmap;
		                                                                                  
		          try{
		           scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);
		          }
		          catch(Exception e){
		           return null;
		          }
		                                                                                       
		       //从图中截取正中间的正方形部分。
		       int xTopLeft = (scaledWidth - edgeLength) / 2;
		       int yTopLeft = (scaledHeight - edgeLength) / 2;
		                                                                                     
		       try{
		        result = Bitmap.createBitmap(scaledBitmap, xTopLeft, yTopLeft, edgeLength, edgeLength);
		        scaledBitmap.recycle();
		       }
		       catch(Exception e){
		        return null;
		       }       
		   }
		                                                                                      
		   return result;
		  }
		  
}