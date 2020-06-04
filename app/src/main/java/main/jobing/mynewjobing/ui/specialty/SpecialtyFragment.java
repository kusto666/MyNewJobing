package main.jobing.mynewjobing.ui.specialty;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import main.jobing.mynewjobing.R;
import main.jobing.mynewjobing.ui.mainfragment.MainFragment;

public class SpecialtyFragment extends Fragment {
    final public String LOG_TAG = "myLogs";
    // курсор
    Cursor c = null;
   // private SpecialtyViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*notificationsViewModel =
                ViewModelProviders.of(this).get(SpecialtyViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_specialty, container, false);
        //final TextView textView = root.findViewById(R.id.text_notifications);
      /*  notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        TextView textView = root.findViewById(R.id.my_text);
        Log.d(LOG_TAG, "--- Все записи ---");
        c = MainFragment.db.query("employee", null, null,
                null, null, null, null);

        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = "
                                + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG, str);

                } while (c.moveToNext());
            }
            c.close();
        } else
            Log.d(LOG_TAG, "Cursor is null");

        return root;
    }
}