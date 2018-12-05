package labmatr7413.avgor.lab_matr;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

    public class ToastMessages {

        private static void toastBottom(String s, Context context){
            Toast toast = android.widget.Toast.makeText(context,
                    s,
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        }

        static void dataError(Context context) {
            toastBottom("Проверьте правильность введенной системы", context);
        }

        static void programError(Context context) {
            toastBottom("Программная ошибка, попробуйте еще раз", context);
        }

        static void wrongIntervalError(Context context) {
            toastBottom("Неверно указан промежуток", context);
        }
        static void wrongIntervalFindAnswerError(Context context) {
            toastBottom("На данном промежутка не содержится корня уравнения", context);
        }
    }


