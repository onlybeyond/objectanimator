package com.onlybeyond.objectanimator.ui.utils;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static com.onlybeyond.objectanimator.ui.utils.LogUtils.*;


/**
 * Created by only on 16/8/19.
 * Email: onlybeyond99@gmail.com
 */
public class FileUtils {
    private static String TAG=makeLogTag(FileUtils.class);

    /**
     * 读取文本数据
     *
     * @param context
     *            程序上下文
     * @param fileName
     *            文件名
     * @return String, 读取到的文本内容，失败返回null
     */
    public static String readAssets(Context context, String fileName)
    {
        InputStream is = null;
        String content = null;
        try
        {
            is = context.getAssets().open(fileName);
            if (is != null)
            {

                byte[] buffer = new byte[1024];
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                while (true)
                {
                    int readLength = is.read(buffer);
                    if (readLength == -1) break;
                    arrayOutputStream.write(buffer, 0, readLength);
                }
                is.close();
                arrayOutputStream.close();
                content = new String(arrayOutputStream.toByteArray());

            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            content = null;
        }
        finally
        {
            try
            {
                if (is != null) is.close();
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
        return content;
    }


}
