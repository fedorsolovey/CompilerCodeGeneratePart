import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fedor on 08.11.15.
 */
public class ReadAndWriteFiles
{
    //чтение дерева из файла
    public Node readTreeFile()
    {
        File file = new File("/home/fedor/Programs/Qt/Projects/build-Syntax-Desktop_Qt_5_5_1_GCC_64bit-Debug/doc.log");
        BufferedReader reader;
        ArrayList<Object> arrayList = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(file));
            String inputString;
            int j = 0;

            while ((inputString = reader.readLine()) != null) {
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

    //чтение Map с переменными из файла
    public Map readMapFile()
    {
        File file = new File("/home/fedor/Programs/Qt/Projects/build-Syntax-Desktop_Qt_5_5_1_GCC_64bit-Debug/doc.p.map");
        BufferedReader reader;
        Map mapItem = new HashMap<String, VariableItem>();

        try
        {
            reader = new BufferedReader(new FileReader(file));
            String inputString;

            while ((inputString = reader.readLine()) != null)
            {
                String[] subStrings = inputString.split(" ");

                if (subStrings.length == 2)
                    mapItem.put(subStrings[0], new VariableItem(subStrings[1]));
                else if (subStrings.length == 3)
                    mapItem.put(subStrings[0], new VariableItem(subStrings[1], subStrings[2]));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return mapItem;
    }

    //запись в файл asm кода
    public void writeInFile(ArrayList<String> outputStrings)
    {
        File file = new File("filesForTests/programOutput.asm");

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
                bufferedWriter.write(stringItem + "\n");

            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
