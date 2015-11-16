import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main
{
    public static void main(String[] args)
    {
        CodeGenerator codeGenerator = new CodeGenerator(ReadAndWriteFiles.readTreeFile(), ReadAndWriteFiles.readMapFile());

        ReadAndWriteFiles.writeInFile(codeGenerator.getOutputStrings());

        String[] cmd = new String[2];
        cmd[0] = "python";
        cmd[1] = "/home/fedor/Документы/IDEA/CompilerCodeGeneratePart/pythonPart/main.py";

        try {
// create runtime to execute external command
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(cmd);

// retrieve output from python script
            BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line = "";
            while ((line = bfr.readLine()) != null)
            {
// display each output line form python script
                System.out.println(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("/home/fedor/Документы/IDEA/CompilerCodeGeneratePart/pythonPart/syntax.sh");

            BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line = "";
            while ((line = bfr.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}
