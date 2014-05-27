package com.example.testhttpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		
		//HttpAsyncTask task = new HttpAsyncTask();
		//task.execute("");
		
		HttpPostAsyncTask task1 = new HttpPostAsyncTask();
		task1.execute("");
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	  private class HttpAsyncTask extends AsyncTask<String, Void, String> {
	        @SuppressWarnings("unused")
			@Override
	        protected String doInBackground(String... urls) {
	 
	        	HttpClient httpClient = new DefaultHttpClient();
	    		HttpGet httpGet = new HttpGet("http");
	    		StringBuffer result = new StringBuffer();
	    		HttpResponse response;
				try {
					response = httpClient.execute(httpGet);
					BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

					
					String line = "";
					while ((line = rd.readLine()) != null) {
					    result.append(line);
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					JSONObject jsonObject = new JSONObject(result.toString());
					JSONArray jsonArray =  jsonObject.getJSONArray("breaking_news");
					if (jsonArray.length() > 0){
						for(int i= 0; i < jsonArray.length();i++){
							JSONObject object = (JSONObject) jsonArray.get(i);
							String id = (String) object.get("breaking_news_id");
						}
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return result.toString();
				
				
	        }
	        // onPostExecute displays the results of the AsyncTask.
	        @Override
	        protected void onPostExecute(String result) {
	            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
	       }
	    }
	  
	  
	  private class HttpPostAsyncTask extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			  HttpClient httpclient = new DefaultHttpClient();
			    HttpPost httppost = new HttpPost("http");
			    StringBuffer result = new StringBuffer();
			    try {
			        // Add your data
			        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			        nameValuePairs.add(new BasicNameValuePair("beer_id", "X"));
			        nameValuePairs.add(new BasicNameValuePair("queue", "1"));
			        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			        // Execute HTTP Post Request
			        HttpResponse response = httpclient.execute(httppost);
			        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

					
					String line = "";
					while ((line = rd.readLine()) != null) {
					    result.append(line);
					}
			        
			    } catch (ClientProtocolException e) {
			        // TODO Auto-generated catch block
			    } catch (IOException e) {
			        // TODO Auto-generated catch block
			    }
				return result.toString();
		}
		  
	  }

}
