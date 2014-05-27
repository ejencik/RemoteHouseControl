package zbynek.remotehouseholdcontrol;

import java.io.IOException;

import zbynek.remotehouseholdcontrol.nettools.CgiScriptCaller;
import zbynek.remotehouseholdcontrol.nettools.ConnectionCredentialsManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;



public class ControlFragment extends Fragment {

	private ConnectionCredentialsManager cm;
//	private ToggleButton heatingButton;
	private Switch heatingButton;
	private Switch SprinklerButton;
	private Button PulseButton;
	
	@Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  
        return inflater.inflate(R.layout.control_fragment_layout, container, false);  
    }  
       
	
	
	private OnCheckedChangeListener heatingButtonListener = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(final CompoundButton button, boolean isChecked) {
		
			AsyncTask<Boolean, Void, Boolean> setHeatingStatusTask = new AsyncTask<Boolean, Void, Boolean>() {
				@Override
				protected Boolean doInBackground(Boolean ... params) {
					boolean isChecked = params[0];
					CgiScriptCaller scriptCaller = new CgiScriptCaller(cm);
					boolean success;
					try {
						success = scriptCaller.callCGIScriptAndSetValue(isChecked);
						if (!success) {throw new IOException("Script 'setHeatingStatusTask' failed.");}
					} catch (IOException e) {
						return false;
											}
					return true;
				}
			};
			boolean result;
			try {
				result = setHeatingStatusTask.execute(isChecked).get();
			} catch (Exception e) { //collect all possible exceptions
				Toast.makeText(getActivity(),"Exception setHesting", Toast.LENGTH_LONG).show();					
				result = false;
			}
			if (!result) {
	//			showError("chybka 1");

				button.setChecked(!isChecked); //set previous state
			}
		}
	};

	
}	

