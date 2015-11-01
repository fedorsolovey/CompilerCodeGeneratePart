
import java.io.*;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static int MARKER = -1;
    private static Stack stack = new Stack();

    public static void main(String[] args) {
        VarGeneration varGeneration = new VarGeneration();
        varGeneration.generateVar();

        Node nodeItem = new Node();
        stack = new Stack();

        File file = new File("inputFiles/binary_tree");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                Pattern p = Pattern.compile("-?\\d+");
                Matcher m = p.matcher(text);

                Stack stackString = new Stack();

                while (m.find()) {
                    stackString.push(m.group());
                    nodeItem.insert(Integer.parseInt(m.group()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
