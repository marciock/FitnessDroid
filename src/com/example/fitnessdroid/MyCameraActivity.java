package com.example.fitnessdroid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera.CameraInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.media.ExifInterface;

public class MyCameraActivity extends Activity {
	
	String myImagePath;
	Uri muri;
	int CAMERA_REQUEST=0;
	Bitmap bitmap;
	File templeImage;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_camera);
		
		clicaCamera();
			
		clicaStorage();
		
		
		
		
	}
	
	public void runActivity(Class<?> class1,String receptor1,String var1){
		Intent intent=new Intent(this,class1);
		intent.putExtra(receptor1, var1);
		intent.putExtra("diretorio","/mnt");
		intent.putExtra("individuo","com.example.fitnessdroid.FormUsuariosActivity");
		startActivity(intent);
		finish();
	}
	
	public void runEnviar(Class<?> class1,String receptor,String variavel){
		Intent intent=new Intent(getBaseContext(),class1);
		intent.putExtra(receptor, variavel);
		startActivity(intent);
		finish();
	}
	
	
	public void clicaStorage(){
		
		LinearLayout lStorage=(LinearLayout) findViewById(R.id.lStorage);
		
		lStorage.setClickable(true);
		lStorage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				runActivity(com.example.fitnessdroid.ListFoldersActivity.class,"filtro","image");
				
			}

			
		});
	}

	
	
	
	public void clicaCamera(){
		LinearLayout lCamera=(LinearLayout) findViewById(R.id.lCamera);
		
		lCamera.setClickable(true);
		lCamera.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				abreCamera();
				
			}

			
		});
	}
		
		public void abreCamera(){
			Intent cameraIntent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			
			
			cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, getTempFile());
			cameraIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
			cameraIntent.putExtra("return-data", true);
			startActivityForResult(cameraIntent,CAMERA_REQUEST);
			
		}
		private Uri getTempFile() {
			
			File root=new File(Environment.getExternalStorageDirectory(),"Pictures");
			
			if(!root.exists()){
				root.mkdir();
			}
			
			String fileName=""+System.currentTimeMillis();
			File file=new File(root,fileName+".jpeg");
			
			 muri=Uri.fromFile(file);
			 myImagePath=muri.getPath(); 
			Log.v("Tirando Foto",myImagePath);
			
			return muri;
		}
		

		protected void onActivityResult(int requestCode,int resultCode,Intent data){
			
			
			
			if(requestCode==CAMERA_REQUEST){
				if(resultCode==Activity.RESULT_OK){
					//BitmapFactory.Options opt= new BitmapFactory.Options();
						
					//opt.inSampleSize=8;
					
					//Bitmap newImage=Bitmap.createScaledBitmap(BitmapFactory.decodeFile(myImagePath), 150, 150, false);
					
					ajustPhoto();
					
					
					
					enviaBitmap();
				}
			}
			
		}
		
		
		protected void ajustPhoto(){
			templeImage=new File(myImagePath);
			
			getContentResolver().notifyChange(muri, null);
			ContentResolver cr=getContentResolver();
			
			
			Bitmap bitmap=null;
			int w=0;
			int h=0;
			
			Matrix mtx=new Matrix();
			
			//try{
				try {
					bitmap=android.provider.MediaStore.Images.Media.getBitmap(cr, muri);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				w=bitmap.getWidth();
				h=bitmap.getHeight();
				mtx=new Matrix();
				
				ExifInterface exif;
				try {
					exif = new ExifInterface(templeImage.getAbsolutePath());
				
				
				int orientation=exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
				
				switch(orientation){
				case 3:
					mtx.postRotate(180);
					break;
				case 6:
					mtx.postRotate(90);
					break;
				case 8:
					mtx.postRotate(270);
					break;
				default:
					mtx.postRotate(0);
					break;
				
				}
				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			//}
			
			Bitmap rotateBmp=Bitmap.createBitmap(bitmap,0,0,w,h,mtx,true);
			BitmapDrawable bmdb=new BitmapDrawable(rotateBmp);
			
			Integer lateral=256;
			
			try {
				FileOutputStream out=new FileOutputStream(myImagePath);
				Bitmap bmp=bmdb.getBitmap();
				
				Integer Idx=1;
				
				w=bmp.getWidth();
				h=bmp.getHeight();
				
				if(w>=h){
					
					Idx=w/lateral;
				}else{
					Idx=h/lateral;
				}
				
				w=w/Idx;
				h=h/Idx;
				
				Bitmap bmpReduce=Bitmap.createScaledBitmap(bmp, w, h, true);
				bmpReduce.compress(Bitmap.CompressFormat.JPEG, 90, out);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}

		public void enviaBitmap(){
			
			Intent intent=new Intent(getBaseContext(),com.example.fitnessdroid.FormUsuariosActivity.class);
			intent.putExtra("BitmapImage", myImagePath);
			startActivity(intent);
			finish();
		}
		
		public void enviaEstorage(){
			
			
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_my_camera, menu);
		return true;
	}
	
	
	
	
	
	
	
	

}
