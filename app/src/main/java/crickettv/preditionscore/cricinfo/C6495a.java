package crickettv.preditionscore.cricinfo;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class C6495a {
    /* renamed from: a */
    public static String m21868a(String str) {
        try {
            return C6496b.m21869a(str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static class C6496b {

        /* renamed from: a */
        public static final byte[] f29033a = {54, 104, 107, 105, 110, 103, 118, 102, 102, 103, 104, 114, 115, 99, 111, 112};

        /* renamed from: a */
        public static String m21869a(String str) throws Exception {
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                int i2 = i * 2;
                bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(f29033a, "AES");
            Cipher instance = Cipher.getInstance("AES");
            instance.init(2, secretKeySpec);
            return new String(instance.doFinal(bArr));
        }
    }
}
