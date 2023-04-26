package ru.mirea.rebrov.cryptoloader;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyLoader extends AsyncTaskLoader<String>
{
    byte[] cipherTxt;
    byte[] key;
    SecretKey firstKey;

    public static final String ARG_WORD = "world";


    public MyLoader(@NonNull Context context, Bundle args)
    {
        super(context);
        if (args != null)
        {
            this.cipherTxt = args.getByteArray(ARG_WORD);
            this.key = args.getByteArray("key");
            this.firstKey = new SecretKeySpec(this.key, 0, this.key.length, "AES");
        }
    }


    @Override
    protected void onStartLoading()
    {
        super.onStartLoading();
        forceLoad();
    }


    @Override
    public String loadInBackground()
    {
        SystemClock.sleep(2000);
        return String.format("изначальное сообщение: %s",decryptMsg());
    }

    public String decryptMsg()
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, this.firstKey);
            return new String(cipher.doFinal(this.cipherTxt));
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
               | BadPaddingException | InvalidKeyException e)
        {
            throw new RuntimeException(e);
        }
    }
}



