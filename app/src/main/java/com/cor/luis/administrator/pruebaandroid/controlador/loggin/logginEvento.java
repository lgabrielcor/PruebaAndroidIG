package com.cor.luis.administrator.pruebaandroid.controlador.loggin;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Administrator on 15/07/2016.
 */
public class logginEvento {
    //TODO: crear el metodo para retornar eventos


    public static final String logFileSTR = "log.txt";
    public static void insertaLog(Context context, String text)
    {
        File logFile = new File(context.getFilesDir().getPath().toString()+"/"+logFileSTR);
        Log.d("ruta de app", context.getFilesDir().getPath().toString()+"/"+logFileSTR);
        if (!logFile.exists())
        {
            try
            {
                logFile.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String obtieneLog(Context context){

        //Get the text file
        File file = new File(context.getFilesDir().getPath().toString()+"/"+logFileSTR);

//Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
        }

        Log.d("datos ", text.toString());
        return text.toString();
    }
}
