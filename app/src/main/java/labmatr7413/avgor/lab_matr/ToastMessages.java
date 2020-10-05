package labmatr7413.avgor.lab_matr;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

    public class ToastMessages {

        private static void toastBottom(String s, Context context){
            Toast toast = android.widget.Toast.makeText(context,
                    s,
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 0, 10);
            toast.show();
        }

        static void dataError(Context context) {
            toastBottom(context.getString(R.string.check_data), context);
        }

        static void programError(Context context) {
            toastBottom("Программная ошибка, попробуйте еще раз", context);
        }

        static void noEnoughCoinsForGaussMethod(Context context) {
            toastBottom(context.getString(R.string.need_3_coins), context);
        }

        static void noEnoughCoinsForDetailedGaussMethod(Context context) {
            toastBottom(context.getString(R.string.need_5_coins), context);
        }

        static void errorShowRewardedAd(Context context) {
            toastBottom(context.getString(R.string.message_video_ad_not_loaded), context);
        }

        static void wrongIntervalError(Context context) {
            toastBottom("Неверно указан промежуток", context);
        }
        static void wrongIntervalFindAnswerError(Context context) {
            toastBottom("На данном промежутка не содержится корня уравнения", context);
        }


    }


