import org.apache.xmlbeans.impl.xb.ltgfmt.Code;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main
{
    private static Node nodeItem;
    private static Map variablesMap = new HashMap<String, VariableItem>();

    public static void main(String[] args) throws Exception
    {
        Main main = new Main();

        nodeItem = main.readTreeFile();
        variablesMap = main.readMapFile();

        CodeGenerator codeGenerator = new CodeGenerator(nodeItem, variablesMap);
    }

    private Node readTreeFile()
    {
        File file = new File("/home/fedor/Programs/Qt/Projects/build-Syntax-Desktop_Qt_5_5_1_GCC_64bit-Debug/doc.log");
        BufferedReader reader;
        try
        {
            reader = new BufferedReader(new FileReader(file));
            ArrayList<Object> arrayList = new ArrayList<>();
            String inputString;
            int j = 0;

            while ((inputString = reader.readLine()) != null)
            {
                String[] subStrings = inputString.split("\t");

                if (subStrings.length == 4)
                {
                    arrayList.add(j, subStrings);
                    j++;
                }
            }

            return Node.createTree(arrayList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private Map readMapFile()
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
}
