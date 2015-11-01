import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Fedor Solovev on 04.10.2015.
 */

public class VarGeneration
{
    private ArrayList<String> outFileArray = new ArrayList<String>(); //array with output's strings on asm

    protected void generateVar()
    {
        File file = new File("inputFiles/file1.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null)
            {
                this.findingVar(text);
                this.findingConst(text);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }

        this.printGeneratedFile();
    }

    private void findingVar(String varString)
    {
        if(varString.toLowerCase().contains("var ") && varString.toLowerCase().contains("integer"))
        {
            if(varString.contains(","))
            {
                String[] vars = varString.replaceAll("(var)|(\\s)", "").split(":")[0].split(",");

                for (String item : vars)
                    this.generateStringFromVarName(item);
            }
            else
            {
                String varName = varString.substring(varString.indexOf("var ") + 4, varString.indexOf(":"));
                this.generateStringFromVarName(varName);
            }
        }
    }

    private void findingConst(String constString)
    {
        if (constString.toLowerCase().contains("const"))
        {
            String[] vars = constString.toLowerCase().replaceAll("(const)|(\\s)", "").split(";")[0].split(",");
            for(String item : vars)
                this.generateStringFromConstName(item.substring(0, item.indexOf("=")), item.replaceAll("\\D+", ""));
        }
    }

    private void findingCompare(String compareString)
    {
        if (compareString.toLowerCase().contains("if") && compareString.toLowerCase().contains("then"))
        {
            this.generateCompare("a > b");
        }
    }

    private void generateStringFromVarName(String varName)
    {
        outFileArray.add(varName + " dw ?");
    }

    private void generateStringFromConstName(String constName, String digital)
    {
        outFileArray.add(constName + " dw " + digital);
    }

    private void generateCompare(String expression)
    {

    }

    private void printGeneratedFile()
    {
        outFileArray.forEach(System.out::println);

    }
}
