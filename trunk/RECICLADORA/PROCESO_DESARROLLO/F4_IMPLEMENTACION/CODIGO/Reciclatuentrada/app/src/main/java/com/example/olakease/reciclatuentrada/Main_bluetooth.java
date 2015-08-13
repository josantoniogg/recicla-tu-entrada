package com.example.olakease.reciclatuentrada;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.example.olakease.reciclatuentrada.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class Main_bluetooth extends Activity {


    TextView txtArduino;
    Handler h;

    final int RECIEVE_MESSAGE = 1;		// Status  for Handler
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder sb = new StringBuilder();


    private ConnectedThread mConnectedThread;

    // SPP UUID service
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private Chronometer crono;
    private ProgressBar prgb;
    public static String material,usuario;

    // MAC-address of Bluetooth module (you must edit this line)
    private static String address = "98:D3:31:40:20:27";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_bluetooth);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        usuario=getIntent().getStringExtra("usuario");
        prgb=(ProgressBar)findViewById(R.id.progressBar);
        this.crono = (Chronometer) findViewById(R.id.chronometer);
        txtArduino = (TextView) findViewById(R.id.txtarduino);      // for display the received data from the Arduino

        h = new Handler() {
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case RECIEVE_MESSAGE:													// if receive massage
                        byte[] readBuf = (byte[]) msg.obj;
                        String strIncom = new String(readBuf, 0, msg.arg1);					// create string from bytes array
                        sb.append(strIncom);												// append string
                        int endOfLineIndex = sb.indexOf("\r\n");							// determine the end-of-line
                        if (endOfLineIndex > 0) { 											// if end-of-line,
                            String sbprint = sb.substring(0, endOfLineIndex);				// extract string
                            sb.delete(0, sb.length());										// and clear
System.out.println("111 "+sbprint);
                            try {
                                alerta(sbprint);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        //Log.d(TAG, "...String:"+ sb.toString() +  "Byte:" + msg.arg1 + "...");
                        break;
                }
            };
        };

        btAdapter = BluetoothAdapter.getDefaultAdapter();		// get Bluetooth adapter
        checkBTState();

startCrono(0);

    }

    public void alerta(String valor) throws IOException {
        prgb.setVisibility(View.INVISIBLE);
        txtArduino.setVisibility(View.INVISIBLE);
        valor = valor.trim();
        String titulo = "Felicidades";
        int valores=0;
        switch (valor) {
            case "lata": {
                material = "Latas";
            }
            break;
            case "papel": {
                material = "Papel";
            }
            break;
            case "plastico": {
                material = "Pet";
            }
            break;
            case "nada": {
        valores++;
            }
            break;

        }
        if (valores==1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("existe un error vuelva a intentarlo, si el error perciste consulte a un profesional");
            builder.setPositiveButton("OK", null);
            builder.setCancelable(false);
            builder.create();
            builder.show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Felicidades");
            builder.setMessage("Usted ha ingresado al contenedor" + material);
            builder.setPositiveButton("OK", null);
            builder.setCancelable(false);
            builder.create();
            builder.show();
//new Insertar(Main_bluetooth.this).execute();
            inserta(material, usuario);
        }
    }




    public void inserta(String materials,String nick){
        HttpClient Client = new DefaultHttpClient();

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("nick",nick ));
        nameValuePairs.add(new BasicNameValuePair("material", materials));

        try {

            String SetServerString = "";

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://proyectositi.com/recicladora/conexiones/update.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();

            SetServerString = httpclient.execute(httppost, responseHandler);

        }  catch(Exception ex) {
            // failed
        }
    }












public void insert() throws IOException {
    try {
        URL url = new URL("http://proyectositi.com/recicladora/conexiones/qqinsert.php");
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }
    HttpClient client = new DefaultHttpClient();
    HttpGet request = new HttpGet();
    try {
        request.setURI(new URI("http://proyectositi.com/recicladora/conexiones/qqinsert.php"));
    } catch (URISyntaxException e) {
        e.printStackTrace();
    }


    HttpResponse response = null;
    try {
        response = client.execute(request);
    } catch (IOException e) {
        e.printStackTrace();
    }
    try {
        BufferedReader in = new BufferedReader
                (new InputStreamReader(response.getEntity().getContent()));
    } catch (IOException e) {
        e.printStackTrace();
    }
    URL url = null;
    try {
        url = new URL("http://proyectositi.com/recicladora/conexiones/qqinsert.php");
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }

    String data  = URLEncoder.encode("nick", "UTF-8")
            + "=" + URLEncoder.encode(usuario, "UTF-8");
    data += "&" + URLEncoder.encode("material", "UTF-8")
            + "=" + URLEncoder.encode(material, "UTF-8");
    URLConnection conn = url.openConnection();
    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
    wr.write( data );
    BufferedReader reader = new BufferedReader(new
            InputStreamReader(conn.getInputStream()));
}



    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        if(Build.VERSION.SDK_INT >= 10){
            try {
                final Method  m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[] { UUID.class });
                return (BluetoothSocket) m.invoke(device, MY_UUID);
            } catch (Exception e) {
            }
        }
        return  device.createRfcommSocketToServiceRecord(MY_UUID);
    }
    public void startCrono(int valor) {
        int cont = 37000;
        if (valor == 1) {
            cont = 10;
        }

        new CountDownTimer(cont, 1000) {

            public void onTick(long millisUntilFinished) {
                txtArduino.setText("Segundos para ingresar: " + millisUntilFinished / 1000);
            }

            public void onFinish() {



            }
        }.start();


    }
    @Override
    public void onResume() {
        super.onResume();


        // Set up a pointer to the remote node using it's address.
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        // Two things are needed to make a connection:
        //   A MAC address, which we got above.
        //   A Service ID or UUID.  In this case we are using the
        //     UUID for SPP.

        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            errorExit("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
        }

    /*try {
      btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
    } catch (IOException e) {
      errorExit("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
    }*/

        // Discovery is resource intensive.  Make sure it isn't going on
        // when you attempt to connect and pass your message.
        btAdapter.cancelDiscovery();

        // Establish the connection.  This will block until it connects.
        try {
            btSocket.connect();

        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {
                errorExit("Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
            }
        }

        // Create a data stream so we can talk to server.

        mConnectedThread = new ConnectedThread(btSocket);
        mConnectedThread.start();
    }

    @Override
    public void onPause() {
        super.onPause();


        try     {
            btSocket.close();
        } catch (IOException e2) {
            errorExit("Fatal Error", "In onPause() and failed to close socket." + e2.getMessage() + ".");
        }
    }

    private void checkBTState() {
        // Check for Bluetooth support and then check to make sure it is turned on
        // Emulator doesn't support Bluetooth and will return null
        if(btAdapter==null) {
            errorExit("Fatal Error", "Bluetooth not support");
        } else {
            if (btAdapter.isEnabled()) {
            } else {
                //Prompt user to turn on Bluetooth
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

    private void errorExit(String title, String message){
        Toast.makeText(getBaseContext(), title + " - " + message, Toast.LENGTH_LONG).show();
        finish();
    }

    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[256];  // buffer store for the stream
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);		// Get number of bytes and message in "buffer"
                    h.obtainMessage(RECIEVE_MESSAGE, bytes, -1, buffer).sendToTarget();		// Send to message queue Handler
                } catch (IOException e) {
                    break;
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        public void write(String message) {
            byte[] msgBuffer = message.getBytes();
            try {
                mmOutStream.write(msgBuffer);
            } catch (IOException e) {
            }
        }
    }





    class Insertar extends AsyncTask<Main_bluetooth, String, String> {

        private Main_bluetooth context;


        Insertar(Main_bluetooth context) {
            this.context = context;
        }


        protected String doInBackground(Main_bluetooth... params) {

            HttpClient httpclient;
            List<NameValuePair> nameValuePairs;
            HttpPost httppost;
            httpclient = new DefaultHttpClient();
            httppost = new HttpPost("http://proyectositi.com/recicladora/conexiones/qqinsert.php"); // Url del Servidor
            //Anadimos nuestros datos
            nameValuePairs = new ArrayList<NameValuePair>(1);
            System.out.println("material "+material);
            nameValuePairs.add(new BasicNameValuePair("nick", usuario));
            nameValuePairs.add(new BasicNameValuePair("material", material));

            System.out.println("nick"+ usuario + "m" + material);
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


            return "";

        }

    }

}
