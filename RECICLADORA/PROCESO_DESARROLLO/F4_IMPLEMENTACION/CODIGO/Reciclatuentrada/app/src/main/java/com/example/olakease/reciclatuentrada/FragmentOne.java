package com.example.olakease.reciclatuentrada;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class FragmentOne extends Fragment {

    TextView txtArduino, txtarduinis;

    ProgressBar prgb;
    private ProgressDialog pDialog;

    final int RECIEVE_MESSAGE = 1;        // Status  for Handler
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder sb = new StringBuilder();

    private ConnectedThread mConnectedThread;

    // SPP UUID service
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    // MAC-address of Bluetooth module (you must edit this line)
    private static String address = "98:D3:31:40:20:27";
    private Chronometer crono;
    public static MainActivity objMa;
    public static String material, nick;
    public int Contadorlata = 0;
    public int Contadorpape = 0;
    public int Contadorpet = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout_one, container,
                false);
        nick = objMa.usuario;
        this.crono = (Chronometer) view.findViewById(R.id.chronometer);
        txtarduinis = (TextView) view.findViewById(R.id.txtarduinis);
        prgb = (ProgressBar) view.findViewById(R.id.progressBar);
        txtArduino = (TextView) view.findViewById(R.id.txtarduino);      // for display the received data from the Arduino




        btAdapter = BluetoothAdapter.getDefaultAdapter();       // get Bluetooth adapter

        checkBTState();
        startCrono(0);


        return view;
    }


    Handler h = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case RECIEVE_MESSAGE:
                    byte[] readBuf = (byte[]) msg.obj;
                    String strIncom = new String(readBuf, 0, msg.arg1);                 // create string from bytes array
                    sb.append(strIncom);                                                // append string
                    int endOfLineIndex = sb.indexOf("\n");
                    // determine the end-of-line
                    if (endOfLineIndex > 0) {                                            // if end-of-line,
                        String sbprint = sb.substring(0, endOfLineIndex);               // extract string
                        // sb.delete(0, sb.length());                                      // and clear
                        System.out.println("1111   " + sbprint);


                        if (sbprint.trim().equals("papel")) {
                            Contadorpape++;
                            alertaMateriales(2);
                        }
                        if (sbprint.trim().equals("lata")) {
                            Contadorlata++;
                            alertaMateriales(1);
                        }
                        if (sbprint.trim().equals("plastico")) {
                            Contadorpet++;
                            alertaMateriales(3);
                        }
                        if (sbprint.trim().equals("nada")) {
                            Contadorpet++;
                            alertaMateriales(5);
                        }


                    }
                    break;
            }
        }


    };













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


    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        if (Build.VERSION.SDK_INT >= 10) {
            try {
                final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[]{UUID.class});
                return (BluetoothSocket) m.invoke(device, MY_UUID);
            } catch (Exception e) {
                Log.e(null, "Could not create Insecure RFComm Connection", e);
            }
        }
        return device.createRfcommSocketToServiceRecord(MY_UUID);
    }



    @Override
    public void onResume() {
        super.onResume();

        Log.d(null, "...onResume - try connect...");

        // Set up a pointer to the remote node using it's address.
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        // Two things are needed to make a connection:
        //   A MAC address, which we got above.
        //   A Service ID or UUID.  In this case we are using the
        //     UUID for SPP.

        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
           // errorExit("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
        }

        // Discovery is resource intensive.  Make sure it isn't going on
        // when you attempt to connect and pass your message.
        btAdapter.cancelDiscovery();

        // Establish the connection.  This will block until it connects.
        Log.d(null, "...Connecting...");
        try {
            btSocket.connect();
            Log.d(null, "....Connection ok...");
        } catch (IOException e) {

        }

        // Create a data stream so we can talk to server.
        Log.d(null, "...Create Socket...");
        mConnectedThread=null;
        mConnectedThread = new ConnectedThread(btSocket);
        mConnectedThread.start();
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d(null, "...In onPause()...");


    }

    private void checkBTState() {
        // Check for Bluetooth support and then check to make sure it is turned on
        // Emulator doesn't support Bluetooth and will return null
        if (btAdapter == null) {
        } else {
            if (btAdapter.isEnabled()) {
                Log.d(null, "...Bluetooth ON...");
            } else {
                //Prompt user to turn on Bluetooth
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
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
            } catch (IOException e) {
            }

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
                    bytes = mmInStream.read(buffer);        // Get number of bytes and message in "buffer"
                    h.obtainMessage(RECIEVE_MESSAGE, bytes, -1, buffer).sendToTarget();     // Send to message queue Handler
                } catch (IOException e) {
                    break;
                }
            }
        }

    }


    public void alertaMateriales(int tipo) {
        if (tipo > 0 && tipo < 4) {

            startCrono(1);
            prgb.setVisibility(View.INVISIBLE);
            txtarduinis.setVisibility(View.INVISIBLE);
            txtArduino.setVisibility(View.INVISIBLE);
            if (tipo == 1) {
                //insertar lata
                material = "Latas";
            }
            if (tipo == 2) {
                prgb.setVisibility(View.VISIBLE);

                material = "Papel";
            }
            if (tipo == 3) {
                txtArduino.setVisibility(View.VISIBLE);

                material = "Pet";

            }
            new Insertar(FragmentOne.this).execute();
            //objMa.alertafr(1, "Felicidades usted ha ingresado " + material);
          //  pDialog.dismiss();


        } else {

        }

    }

    private boolean insertar() {


        HttpClient httpclient;
        List<NameValuePair> nameValuePairs;
        HttpPost httppost;
        httpclient = new DefaultHttpClient();
        httppost = new HttpPost("http://proyectositi.com/recicladora/conexiones/qqinsert.php"); // Url del Servidor
        //Anadimos nuestros datos
        nameValuePairs = new ArrayList<NameValuePair>(1);
        System.out.println("material "+material);
        nameValuePairs.add(new BasicNameValuePair("nick", nick));
        nameValuePairs.add(new BasicNameValuePair("material", material));

System.out.println("nick"+ nick + "m" + material);
        try {

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            httpclient.execute(httppost);
            return true;
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return false;
    }


    class Insertar extends AsyncTask<FragmentOne, String, String> {

        private FragmentOne context;


        Insertar(FragmentOne context) {
            this.context = context;
        }


        protected String doInBackground(FragmentOne... params) {
            // TODO Auto-generated method stub
            if (insertar()) context.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub

                }

            });

            return null;
        }

    }


    public void runOnUiThread(Runnable runnable) {
        // TODO Auto-generated method stub

    }

    public void onClick(View v) {
        // TODO Auto-generated method stub

    }
}



