/**
 * Created by fedor on 07.11.15.
 */
public class VariableItem
{
    private String typeValue;
    private String initValue;

    public VariableItem(String typeValue, String initValue)
    {
        this.typeValue = typeValue;
        this.initValue = initValue;
    }

    public VariableItem(String typeValue)
    {
        this.typeValue = typeValue;
        this.initValue = "?";
    }

    public String getInitValue() {
        return initValue;
    }
}
