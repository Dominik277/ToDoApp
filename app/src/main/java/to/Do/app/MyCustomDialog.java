package to.Do.app;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyCustomDialog extends DialogFragment {

    private static final String TAG = "MyCustomDialog";

    public interface OnInputListener{
        void sendInput(String input);
    }

    public OnInputListener mOnInputListener;

    private EditText mjestoUpisaPodsjetnika;
    private TextView gumbUnesi,gumbPonisti;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_alert,container,false);
        mjestoUpisaPodsjetnika = view.findViewById(R.id.mjestoUpisaPodsjetnika);
        gumbUnesi = view.findViewById(R.id.gumbUnesi);
        gumbPonisti = view.findViewById(R.id.gumbPonisti);

        gumbPonisti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getDialog().dismiss();
            }
        });

        gumbUnesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mjestoUpisaPodsjetnika.getText().toString();
                mOnInputListener.sendInput(input);
                getDialog().dismiss();
            }
        });
                return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mOnInputListener = (OnInputListener) getActivity();
        }catch (Exception e){
            Log.e(TAG,"onAttach: ClassCastException: " + e.getMessage());
        }
    }
}
