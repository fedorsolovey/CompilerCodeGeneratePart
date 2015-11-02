
import java.io.*;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static Node nodeItem = new Node();

    public static void main(String[] args)
    {
        File file = new File("/home/fedor/Programs/Qt/Projects/build-Syntax-Desktop_Qt_5_5_1_GCC_64bit-Debug/doc.log");
        BufferedReader reader;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            String inputString;

            while ((inputString = reader.readLine()) != null)
            {
                String[] subStrings = inputString.split("\t");

                if (subStrings.length == 4)
                    nodeItem.insert(subStrings);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        nodeItem.traverseTree(Node.TraverseType.INORDER);
    }
}
