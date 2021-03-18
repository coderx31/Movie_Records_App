package com.coderx.assignment02;

import android.text.InputFilter;
import android.text.Spanned;

public class NumberRange implements InputFilter {
    private int min;
    private int max;

    public NumberRange(int min, int max){
        this.min = min;
        this.max = max;
    }

    public NumberRange(String min, String max){
        this.min = Integer.parseInt(min);
        this.max = Integer.parseInt(max);
    }
    @Override
    public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
        try {
            int input = Integer.parseInt(spanned.toString() + charSequence.toString());
            if (inRange(min,max,input)){
                return null;
            }

        }catch (NumberFormatException ex){
            System.out.println(ex.getMessage());
        }
        return "";
    }

    private boolean inRange(int a, int b, int c){
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}
