package com.hfad;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormatSymbols;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {



    public class NegativeDecimalInputFilter implements InputFilter {
        private final Pattern mPattern;
        public NegativeDecimalInputFilter() {
            DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
            this.mPattern = Pattern.compile("(0|[1-9]+[0-9]*)?(["+dfs.getDecimalSeparator()+"][0-9]{0,2})?");
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            String result =
                    dest.subSequence(0, dstart)
                            + source.toString()
                            + dest.subSequence(dend, dest.length());
            Matcher matcher = mPattern.matcher(result);
            if (!matcher.matches()) return dest.subSequence(dstart, dend);
            return null;
        }
    }

    EditText editText1;
    EditText editText2;
    EditText editText3;
    TextView res;
    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                v.clearFocus();
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            }
        }

        return super.dispatchTouchEvent(event);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1= findViewById(R.id.plain1);
        editText2= findViewById(R.id.plain2);
        editText3= findViewById(R.id.plain3);
        res = findViewById(R.id.result);
        res.setMovementMethod(new ScrollingMovementMethod());
        editText1.setFilters(new InputFilter[]{new NegativeDecimalInputFilter()});
        editText2.setFilters(new InputFilter[]{new NegativeDecimalInputFilter()});
        editText3.setFilters(new InputFilter[]{new NegativeDecimalInputFilter()});



    }
    public void myButton (View view){
        String kmString0 = editText1.getText().toString();
        String kmString = kmString0.replace(',','.');
        String liString0 = editText2.getText().toString();
        String liString = liString0.replace(',','.');
        String ruString0 = editText3.getText().toString();
        String ruString = ruString0.replace(',','.');
        float km;
        float li ;
        float ru = 0;
        float result;
        float resultRu = 0;
        String izy;
        String izyRu;
        if (kmString.length()>0 && liString.length()>0)
        {   km = Float.parseFloat(kmString);
            li = Float.parseFloat(liString);

            if (km!=0 && li!=0) {
                result = li * 100 / km;
                if(ruString.length()>0 ){
                    ru = Float.parseFloat(ruString); }
                    if (ru !=0){
                    resultRu = ru*result/100;

                    }

                    if (resultRu >0){

                izyRu = Float.toString(resultRu);
                String izyRuPers = izyRu.replace('.',',');
                izy = Float.toString(result);
                String izyPers = izy.replace('.',',');
                res.setText("Расход топлива: "+izyPers+" "+"л/100км" + "\n" + "Стоимость 1км"+izyRuPers+" руб.");}
                else {izy = Float.toString(result);
                String izyPers = izy.replace('.',',');
                        res.setText("Расход топлива: "+izyPers);}}
             else {    Toast toast = Toast.makeText(getApplicationContext(),
                    "Значения пройденного расстояния и объема израсходанного топлива не могут " +
                            "равняться 0",
                    Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();}

        } else {    Toast toast = Toast.makeText(getApplicationContext(),
                "Введите значения пройденного расстояния и объем израсходанного топлива",
                Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();}

    }
    public void myButton1 (View view){
        Intent intent= new Intent(MainActivity.this,Main2Activity.class);
    startActivity(intent);

    }
}
