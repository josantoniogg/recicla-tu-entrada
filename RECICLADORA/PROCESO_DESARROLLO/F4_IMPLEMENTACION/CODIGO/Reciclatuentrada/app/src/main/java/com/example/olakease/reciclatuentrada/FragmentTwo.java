package com.example.olakease.reciclatuentrada;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentTwo   extends Fragment {

	ImageView ivIcon;
	TextView tvItemName;

	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
	public static final String ITEM_NAME = "itemName";

	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	ArrayList<HashMap<String, String>> empresaList;

	// url to get all products list
	private static String url_all_empresas = "http://proyectositi.com/recicladora/conexiones/get_all_empresas.php";
	public static Actividad_Fragments objMa;
	public static String nick;

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PRODUCTS = "usuarios";
	private static final String TAG_ID = "nick";
	private static final String TAG_NOMBRE = "nombre";
	private static final String TAG_Total= "total";
	private static final String TAG_Material= "nombre_del_material";
	// products JSONArray
	JSONArray products = null;

	ListView lista;

	public FragmentTwo()
	{

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View view=inflater.inflate(R.layout.fragment_layout_two,container, false);
		nick=objMa.usuario;

		// Hashmap para el ListView
		empresaList = new ArrayList<HashMap<String, String>>();
		// Cargar los productos en el Background Thread
		new LoadAllProducts().execute();
		lista = (ListView)view.findViewById(R.id.listAllProducts);

		//ActionBar actionBar = getSupportedTypes();
		//actionBar.setDisplayHomeAsUpEnabled(true);
		return view;

	}




	class LoadAllProducts extends AsyncTask<String, String, String> {

		/**
		 * Antes de empezar el background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Cargando. Por favor espere...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		/**
		 * obteniendo todos los productos
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters

			HttpClient httpclient;
			List<NameValuePair> nameValuePairs;
			HttpPost httppost;
			httpclient=new DefaultHttpClient();
			httppost= new HttpPost(url_all_empresas); // Url del Servidor
			//A
			// adimos nuestros datos
			nameValuePairs = new ArrayList<NameValuePair>(0);
			nameValuePairs.add(new BasicNameValuePair("nick", nick.toString().trim()));

			try {

				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				httpclient.execute(httppost);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			List params = new ArrayList();



			JSONObject json = jParser.makeHttpRequest(url_all_empresas, "GET", params);

			Log.d("All Products: ", json.toString());

			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {

					products = json.getJSONArray(TAG_PRODUCTS);

					// looping through All Products
					//Log.i("ramiro", "produtos.length" + products.length());
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);
						System.out.println(c);

						// Storing each json item in variable

						String id = c.getString(TAG_ID);
						//String name = c.getString(TAG_NOMBRE);
						String total =c.getString(TAG_Total);
						String material =c.getString(TAG_Material);

						// creating new HashMap
						HashMap map = new HashMap();

						// adding each child node to HashMap key => value
						map.put(TAG_ID, id);
						//map.put(TAG_NOMBRE, name);
						map.put(TAG_Total, total);
						map.put(TAG_Material, material);
						empresaList.add(map);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			System.out.println("entro al onpostexecute");
			getActivity().runOnUiThread(new Runnable() {
				public void run() {

					System.out.println("entro run");

					/**
					 * Updating parsed JSON data into ListView
					 * */
					ListAdapter adapter = new SimpleAdapter(getActivity(), empresaList, R.layout.single_post, new String[]{TAG_ID, TAG_Total, TAG_Material
					}, new int[]{R.id.single_post_tv_id, R.id.total, R.id.material});
					// updating listview
					//setListAdapter(adapter);
					System.out.println("entro" + adapter);

					lista.setAdapter(adapter);
				}
			});

		}

	}
}
