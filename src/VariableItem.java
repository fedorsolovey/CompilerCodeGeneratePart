/**
 * Created by fedor on 07.11.15.
 */
public class VariableItem
{
    String typeValue;
    String initValue;

    public VariableItem(String typeValue, String initValue)
    {
        this.typeValue = typeValue;
        this.initValue = initValue;
    }

    public VariableItem(String typeValue)
    {
        this.typeValue = typeValue;
        this.initValue = "0";
    }
}
