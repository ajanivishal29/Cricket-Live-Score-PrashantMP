package crickettv.preditionscore.cricinfo.Main_Ads.Blue_mycustomtab;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

public class C1948u6 {
    public static final Object f12167a = new Object();

    public static boolean m10708a(Context context, Intent[] intentArr, Bundle bundle) {
        int i = Build.VERSION.SDK_INT;
        context.startActivities(intentArr, bundle);
        return true;
    }

    public static ColorStateList m10709b(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(i);
        }
        return context.getResources().getColorStateList(i);
    }

    public static Drawable m10710c(Context context, int i) {
        int i2 = Build.VERSION.SDK_INT;
        return context.getDrawable(i);
    }

    public static void m10707a(Context context, Intent intent, Bundle bundle) {
        int i = Build.VERSION.SDK_INT;
        context.startActivity(intent, bundle);
    }

    public static int m10706a(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColor(i);
        }
        return context.getResources().getColor(i);
    }
}
