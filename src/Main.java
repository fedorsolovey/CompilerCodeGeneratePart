import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main
{
    public static void main(String[] args)
    {
        String[] cmd = new String[2];
        cmd[0] = "python";
        cmd[1] = "/home/fedor/Programs/Qt/Projects/build-Syntax-Desktop_Qt_5_5_1_GCC_64bit-Debug/lex_and_syntax/main.py";

//        try {
//// create runtime to execute external command
//            Runtime rt = Runtime.getRuntime();
//            Process pr = rt.exec(cmd);
//
//// retrieve output from python script
//            BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//            String line = "";
//            while ((line = bfr.readLine()) != null)
//            {
//// display each output line form python script
//                System.out.println(line);
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            Runtime rt = Runtime.getRuntime();
//            Process pr = rt.exec("/home/fedor/Документы/IDEA/CompilerCodeGeneratePart/lex_and_syntax/syntax.sh");
//
//            BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//            String line = "";
//            while ((line = bfr.readLine()) != null)
//            {
//                System.out.println(line);
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }

        CodeGenerator codeGenerator = new CodeGenerator(ReadAndWriteFiles.readTreeFile());

        ReadAndWriteFiles.writeInFile(codeGenerator.getOutputStrings());
    }
}
