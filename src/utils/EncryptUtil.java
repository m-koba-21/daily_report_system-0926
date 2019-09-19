package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class EncryptUtil {
	//Javaで文字列を SHA256 で暗号化する方法は、少し面倒だから↓
	//getPasswordEncrypt(src) のようにメソッド名をひとつ記述すれば
	//実行できる暗号化メソッドを用意します。
	//このメソッドは、様々なコントローラで使えるよう、DBUtil のようなユーティリティにしましょう。
    public static String getPasswordEncrypt(String plain_p, String salt) {
        String ret = "";

        if(plain_p != null && !plain_p.equals("")) {
            byte[] bytes;
            String password = plain_p + salt;
            try {
                bytes = MessageDigest.getInstance("SHA-256").digest(password.getBytes());
                ret = DatatypeConverter.printHexBinary(bytes);
            } catch(NoSuchAlgorithmException ex) {}
        }

        return ret;
    }
}