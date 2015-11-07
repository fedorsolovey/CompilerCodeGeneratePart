
public class Main
{
    public static void main(String[] args)
    {
        ReadAndWriteFiles readAndWriteFiles = new ReadAndWriteFiles();

        CodeGenerator codeGenerator = new CodeGenerator(readAndWriteFiles.readTreeFile(), readAndWriteFiles.readMapFile());

        readAndWriteFiles.writeInFile(codeGenerator.getOutputStrings());
    }


}
