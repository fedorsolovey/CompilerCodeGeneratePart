package com.fedor.compiler;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Main
{
    public static void main(String[] args)
    {
        String[] cmd = new String[2];
        cmd[0] = "python";
        cmd[1] = "lex_and_syntax/main.py";

        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(cmd);

            BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            System.out.println("Лексический анализатор");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Синтаксический анализатор");

            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("./syntax.sh");

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

        File f = new File("code.log");
        if (f.exists() && !f.isDirectory()) {
            System.out.println("Начата кодогенерация");
            CodeGenerator codeGenerator = new CodeGenerator(ReadAndWriteFiles.readTreeFile());
            System.out.println("Закончена кодогенерация");

            System.out.println("Начата запись в файл");
            ReadAndWriteFiles.writeInFile(codeGenerator.getOutputStrings());
            System.out.println("Закончена запись в файл");
        }

    }
}
