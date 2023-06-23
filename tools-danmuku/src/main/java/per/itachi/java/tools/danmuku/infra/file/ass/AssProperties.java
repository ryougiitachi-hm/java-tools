package per.itachi.java.tools.danmuku.infra.file.ass;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AssProperties {

    private AssScriptInfoProperties scriptInfo = new AssScriptInfoProperties();

    private AssV4PlusStylesProperties v4PlusStyles = new AssV4PlusStylesProperties();

}
