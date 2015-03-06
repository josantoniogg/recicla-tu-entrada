package vista;
import java.util.Date;

import Datos.DataBaseManager;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.reciclatuentrada.R;

public class FragmentThree extends Fragment implements OnClickListener{
	Button btnguardar;
	View view;
	java.util.Date fecha = new Date();
	

	public static  String strNombre,strApellidos,strFecha;
	
	
	DataBaseManager manager;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		 view = inflater.inflate(R.layout.fragment_layout_three, container,
				false);
		 Button btn =(Button)view.findViewById(R.id.button1);
		 
		 btn.setOnClickListener(this);
		return view;
	

	}

	int año,mes,dia,año_actual,error;
	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:{
			EditText txtnombre=(EditText)view.findViewById(R.id.editText1);
			EditText txtapellidos=(EditText)view.findViewById(R.id.editText2);
			EditText txtFechaAAAA=(EditText)view.findViewById(R.id.editText3);
			EditText txtFechaMM=(EditText)view.findViewById(R.id.editText4);
			EditText txtFechaDD=(EditText)view.findViewById(R.id.editText5);
			/* año =Integer.parseInt(txtFechaAAAA.getText().toString());
			 dia=Integer.parseInt(txtFechaDD.getText().toString());
			 mes=Integer.parseInt(txtFechaMM.getText().toString());
			 año_actual=fecha.getYear();*/
			strNombre=txtnombre.getText().toString();
			strApellidos=txtapellidos.getText().toString();
			strFecha=txtFechaAAAA.getText().toString()+"-"+txtFechaMM.getText().toString()+"-"+txtFechaDD.getText().toString();
			int error =0;
			año=año_actual-año;
			if (strNombre.trim().matches("")){
				System.out.println("error en el nombre");
				error++;
				}else if (strApellidos.trim().matches("")){
					System.out.println("error en el apellido");
					error++;
				}else if(strFecha.trim().matches("")){
					System.out.println("error en la fecha");
					error++;
				}else{
					año =Integer.parseInt(txtFechaAAAA.getText().toString());
					 dia=Integer.parseInt(txtFechaDD.getText().toString());
					 mes=Integer.parseInt(txtFechaMM.getText().toString());	
				}
			if(año_actual<17){
				
			
				System.out.println("es menor de edad");
				error++;
				} if(mes>12){
					error++;
				} if (dia>31){
					error++;
				}if ((año_actual-año)>89){
					error++;
				} if(error==0) {
					manager.insertar(strNombre, strApellidos, strFecha);
					AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
					alertDialog.setTitle("Felicitaciones");
					alertDialog.setMessage("Se han ingresado los datos con éxito");
					alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					// here you can add functions
					}
					});
					alertDialog.setIcon(R.drawable.ic_alert);
					alertDialog.show();
				
				} else if(error>0){
					AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
					alertDialog.setTitle("Error...");
					alertDialog.setMessage("Existen errores intenta de nuevo ");
					alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					// here you can add functions
					}
					});
					alertDialog.setIcon(R.drawable.ic_alert);
					alertDialog.show();
					error=0;
				}
			
					
			txtnombre.setText("");
			txtapellidos.setText("");
			txtFechaAAAA.setText("");
			txtFechaDD.setText("");
			txtFechaMM.setText("");
			
	        };break;
		}
	}

	
			
}


		
		
		
		
		
		
		
		
		
