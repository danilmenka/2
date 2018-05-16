package com.hfad;

        import android.content.Context;
        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.InputFilter;
        import android.text.Spanned;
        import android.text.method.ScrollingMovementMethod;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.EditText;
        import android.widget.TextView;

        import java.text.DecimalFormatSymbols;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;


public class Main2Activity extends AppCompatActivity {
    EditText editText4;
    EditText editText5;
    EditText editText6;
    TextView res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText4= findViewById(R.id.plain4);
        editText5= findViewById(R.id.plain5);
        editText6= findViewById(R.id.plain6);
        res = findViewById(R.id.result2);
        res.setMovementMethod(new ScrollingMovementMethod());


    }



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

    public void myButton3 (View view){
        onBackPressed();
    }
    public void myButton228(View view){

    }
}
