package com.me.extractgifapp.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by cs on 2019/5/23.
 */
public class BitmapUtils {

	/*
	*  public static Bitmap createPhotos(Bitmap bitmap){
  if(bitmap!=null){
   Matrix m=new Matrix();
   try{
    m.setRotate(90, bitmap.getWidth()/2, bitmap.getHeight()/2);//90就是我们需要选择的90度
    Bitmap bmp2=Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
    bitmap.recycle();
    bitmap=bmp2;
   }catch(Exception ex){
    System.out.print("创建图片失败！"+ex);
   }
  }
  return bitmap;
 }

	* */

	public static Bitmap correctBitmap(Bitmap bitmap){

		Matrix matrix = new Matrix();
		matrix.setRotate(90,bitmap.getWidth()/2,bitmap.getHeight()/2);
		Bitmap bt = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
		bitmap.recycle();
		bitmap = bt;
		return bitmap;
	}

}
