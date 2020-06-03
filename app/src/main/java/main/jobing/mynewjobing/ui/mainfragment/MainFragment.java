package main.jobing.mynewjobing.ui.mainfragment;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

import main.jobing.mynewjobing.AppController;
import main.jobing.mynewjobing.DBHelper;
import main.jobing.mynewjobing.R;
import main.jobing.mynewjobing.employee.MyEmployee;

public class MainFragment extends Fragment {

    ArrayList<MyEmployee> myEmployeeArrayList;// Массив с обектами сотрудников!
    MyEmployee tempEmployee = null; // Сюда пишем нового сотрудника!

    Context thiscontext;
    // Progress dialog
    private ProgressDialog pDialog;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        thiscontext = context;
    }

    private static String myTAG = MainFragment.class.getSimpleName();

    private static String urlJsonObj = "https://gitlab.65apps.com/65gb/static/raw/master/testTask.json";

    private MainViewModel dashboardViewModel;
    private Button btnRefreshAllUsers;
    private Button btnClearDB;
    public static TextView txtResponse;
    //public static String jsonResponse;

    public static  org.json.simple.JSONArray resultJsonArrayMain; // Массив со всеми данными из JSON файла
    public static org.json.simple.JSONObject resultJsonObjMain; // Объект со всеми данными из JSON файла
    public static org.json.simple.JSONObject resultJsonSpecialtyArray;
    public static  org.json.simple.parser.JSONParser jsonParser;

    public static String stJsonResponseArray;
    public static String jsonResponse;

    DBHelper dbHelper;
    SQLiteDatabase db;
    ContentValues cv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        thiscontext = container.getContext();
        dashboardViewModel =
                ViewModelProviders.of(this).get(MainViewModel.class);
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        //final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        btnRefreshAllUsers =  (Button) root.findViewById(R.id.btnRefreshAllUsers);
        btnClearDB =  (Button) root.findViewById(R.id.btnClearDB);
        txtResponse = root.findViewById(R.id.txtResponse);

        btnRefreshAllUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonObjectRequest(thiscontext);// Основная функция парсинга JSON - остальное внутри!!!
            }
        });

        btnClearDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearDB();
            }
        });

        dbHelper = new DBHelper(thiscontext);
        // подключаемся к БД
        db = dbHelper.getWritableDatabase();

        pDialog = new ProgressDialog(thiscontext);
        return root;
    }
    public void makeJsonObjectRequest(Context context) {

        cv = new ContentValues();
        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        showpDialog();
        jsonResponse = "";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(myTAG, response.toString());

                try {
                    stJsonResponseArray = response.getString("response");
                    System.out.println("jsonResponseArray = " + stJsonResponseArray);
                    if (jsonParser == null) {
                        jsonParser = new org.json.simple.parser.JSONParser();// Тогда создаем заново!!!
                    }
                    try {
                        resultJsonArrayMain = (org.json.simple.JSONArray) jsonParser.parse(stJsonResponseArray);
                        System.out.println("resultJson = " + resultJsonArrayMain.toJSONString());
                        Object[] myObj = resultJsonArrayMain.toArray();
                        Object[] myObjSpeciale = null;

                        myEmployeeArrayList = new ArrayList<>();

                        for(int i = 0; i < myObj.length; i++)
                        {
                            resultJsonObjMain = (org.json.simple.JSONObject) jsonParser.parse(myObj[i].toString());

                            tempEmployee = new MyEmployee();

                            String stTempf_name = (String) resultJsonObjMain.get("f_name");
                            tempEmployee.f_name = (String) resultJsonObjMain.get("f_name");

                            String stTempl_name = (String) resultJsonObjMain.get("l_name");
                            tempEmployee.l_name = (String) resultJsonObjMain.get("l_name");

                            String stTempbirthday = (String) resultJsonObjMain.get("birthday");
                            tempEmployee.birthday = (String) resultJsonObjMain.get("birthday");

                            String stTempavatr_url = (String) resultJsonObjMain.get("avatr_url");
                            tempEmployee.avatr_url = (String) resultJsonObjMain.get("avatr_url");

                            org.json.simple.JSONArray stJsonResponseArraySpeciale =
                                    (org.json.simple.JSONArray) resultJsonObjMain.get("specialty");


                            if(stTempf_name != null)
                                System.out.println("stTempf_name = " + stTempf_name.toString());
                            jsonResponse += "f_name: " + stTempf_name + "\n\n";
                            if(stTempl_name != null)
                                System.out.println("stTempl_name = " + stTempl_name.toString());
                            jsonResponse += "l_name: " + stTempl_name + "\n\n";
                            if(stTempbirthday != null)
                                System.out.println("stTempbirthday = " + stTempbirthday.toString());
                            jsonResponse += "birthday: " + stTempbirthday + "\n\n";
                            if(stTempavatr_url != null)
                                System.out.println("stTempavatr_url = " + stTempavatr_url.toString());
                            jsonResponse += "URL_PHOTO: " + stTempavatr_url + "\n\n";
                            MainFragment.txtResponse.setText(jsonResponse);
                            if(stJsonResponseArraySpeciale != null)
                                myObjSpeciale = stJsonResponseArraySpeciale.toArray();

                            for(int j = 0; j < myObjSpeciale.length; j++)
                            {
                                resultJsonSpecialtyArray =
                                        (org.json.simple.JSONObject)jsonParser.parse(myObjSpeciale[j].toString());

                                Long iSpecialty_id = (Long) resultJsonSpecialtyArray.get("specialty_id");

                                if(iSpecialty_id != null)
                                {
                                    System.out.println("specialty_id = " + iSpecialty_id.toString());
                                    tempEmployee.specialty_id = iSpecialty_id;

                                    jsonResponse += "specialty_id: " + iSpecialty_id.toString() + "\n\n";
                                    String stName = (String) resultJsonSpecialtyArray.get("name");

                                    if(stName != null)
                                        System.out.println("specialty = " + stName);
                                    tempEmployee.specialty_name = stName;

                                    jsonResponse += "specialty: " + stName + "\n\n";
                                }
                                else
                                {
                                    tempEmployee.specialty_name = "";
                                    tempEmployee.specialty_id = Long.valueOf(-1);
                                }

                            }

                            jsonResponse += "**********************************************" + "\n\n";
                            System.out.println("--------------------------------------------");

                            myEmployeeArrayList.add(tempEmployee);

                            Log.d(dbHelper.LOG_TAG, "--- Insert in mytable: ---");
                            // подготовим данные для вставки в виде пар: наименование столбца - значение
                            cv.put("fname", stTempf_name);
                            cv.put("lname", stTempl_name);
                            // вставляем запись и получаем ее ID
                            long rowID = db.insert("mytable", null, cv);
                            Log.d(dbHelper.LOG_TAG, "row inserted, ID = " + rowID);

                            Log.d(dbHelper.LOG_TAG, "--- Rows in mytable: ---");
                            // делаем запрос всех данных из таблицы mytable, получаем Cursor
                            Cursor c = db.query("mytable", null, null, null,
                                    null, null, null);

                            // ставим позицию курсора на первую строку выборки
                            // если в выборке нет строк, вернется false
                            if (c.moveToFirst()) {

                                // определяем номера столбцов по имени в выборке
                                int idColIndex = c.getColumnIndex("id");
                                int nameColIndex = c.getColumnIndex("fname");
                                int emailColIndex = c.getColumnIndex("lname");

                                do {
                                    // получаем значения по номерам столбцов и пишем все в лог
                                    Log.d(dbHelper.LOG_TAG,
                                            "ID = " + c.getInt(idColIndex) +
                                                    ", fname = " + c.getString(nameColIndex) +
                                                    ", lname = " + c.getString(emailColIndex));
                                    // переход на следующую строку
                                    // а если следующей нет (текущая - последняя), то false - выходим из цикла
                                } while (c.moveToNext());
                            } else
                                Log.d(dbHelper.LOG_TAG, "0 rows");
                            c.close();
                            hidepDialog();

                            System.out.println("Проверка листа с объектами:");
                            System.out.println("myEmployeeArrayList count = " + myEmployeeArrayList.size());
                            for(int k = 0; k < myEmployeeArrayList.size(); k++)
                            {
                                System.out.println("fNAME = " + ((MyEmployee)myEmployeeArrayList.get(k)).f_name);
                                System.out.println("--------------------------------------------");
                            }
                        }
                    } catch (ParseException e) {
                        System.err.println(e.toString());
                        hidepDialog();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(thiscontext,
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    hidepDialog();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(myTAG, "Error: " + error.getMessage());
                Toast.makeText(thiscontext,
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
    public void ClearDB()
    {
        Log.d(dbHelper.LOG_TAG, "--- Clear mytable: ---");
        // удаляем все записи
        int clearCount = db.delete("mytable", null, null);
        Log.d(dbHelper.LOG_TAG, "deleted rows count = " + clearCount);
        txtResponse.setText("Таблица очищена!" + "\n\n" +
                             "Удалено количество строк = " + clearCount + "\n\n" );
      }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}