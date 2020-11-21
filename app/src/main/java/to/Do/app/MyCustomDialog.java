package to.Do.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyCustomDialog extends DialogFragment {

    public interface OnInputListener{
        void sendInput(String input);
    }

    public OnInputListener mOnInputListener;

    private EditText mInput;
    private TextView mActionOk,mActionCanlcel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_alert,container,false);
        mInput = view.findViewById(R.id.input);
        mActionOk = view.findViewById(R.id.actionOk);
        mActionCanlcel = view.findViewById(R.id.actionCancel);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
