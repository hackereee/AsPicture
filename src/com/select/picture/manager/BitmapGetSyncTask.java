package com.select.picture.manager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;

import com.select.picture.interf.OnLoadBitmapListener;

 /*��ִ̨�еõ������ε�bitmap*/
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
				//�����û���,ע�⣺�����������Ա���ͬʱʹ��
				options.inPurgeable = true;
				options.inInputShareable = true;
				
				Bitmap bitmap = BitmapFactory.decodeFile(path,options);
				//�õ�ͼƬ���ķ���ͼ
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
	    
		   * @param bitmap      ԭͼ
		   * @param edgeLength  ϣ���õ��������β��ֵı߳�
		   * @return  ���Ž�ȡ���в��ֺ��λͼ��
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
		    //ѹ����һ����С������edgeLength��bitmap
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
		                                                                                       
		       //��ͼ�н�ȡ���м�������β��֡�
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