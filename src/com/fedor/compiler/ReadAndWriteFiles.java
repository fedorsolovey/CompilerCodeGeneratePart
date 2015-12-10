package com.fedor.compiler;
import java.io.*;
import java.util.ArrayList;


/**
 * Created by fedor on 08.11.15.
 */
public class ReadAndWriteFiles
{
    //чтение дерева из файла
    public static Node readTreeFile()
    {
        File file = new File("code.log");
        BufferedReader reader;
        ArrayList<Object> arrayList = new ArrayList<>();

        try
        {
            reader = new BufferedReader(new FileReader(file));
            String inputString;
            int j = 0;

            while ((inputString = reader.readLine()) != null)
            {
                String[] subStrings = inputString.split("\t");

                if (subStrings.length == 4) {
                    arrayList.add(j, subStrings);
                    j++;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return Node.createTree(arrayList);
    }

    //запись в файл asm кода
    public static void writeInFile(ArrayList<String> outputStrings)
    {
        File file = new File("program.asm");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (String stringItem : outputStrings)
            {
                stringItem = stringItem.replaceAll("\\s+", "\t");
                bufferedWriter.write(stringItem + "\n");
            }

            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
