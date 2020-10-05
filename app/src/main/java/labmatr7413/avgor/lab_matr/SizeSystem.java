package labmatr7413.avgor.lab_matr;

import android.text.Html;
import android.text.Spanned;

import java.lang.reflect.Array;

public final class SizeSystem {

    public static final String[] SIZES_ARRAY_STRING = new String[]
            {
                    "Определите значение",
                    "x<sub>1</sub>, x<sub>2</sub>",
                    "x<sub>1</sub>, x<sub>2</sub>, x<sub>3</sub>",
                    "x<sub>1</sub>, x<sub>2</sub>, x<sub>3</sub>, x<sub>4</sub>",
                    "x<sub>1</sub>, x<sub>2</sub>, x<sub>3</sub>, x<sub>4</sub>, x<sub>5</sub> \n (*)"
            };

    private SizeSystem(){

    }

    public static Spanned[] getSystem() {
        Spanned[] result = new Spanned[SIZES_ARRAY_STRING.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Html.fromHtml("<b>" + SIZES_ARRAY_STRING[i] + "</b>");
        }
        return result;
    }


}
