package format;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import org.junit.jupiter.api.Test;

class FormatPatternTest {
    FormatPattern formatPattern = new FormatPattern("###.###.###-##");

    @Test
    void exemple() {
        System.out.println(formatPattern.format("00000000000"));
        //#result_format: 000.000.000-00


        System.out.println(formatPattern.parse("000.000.000-00"));
        //#result_parse: 00000000000
    }


    void exempleTextField() {
        StringConverter<String> stringConverter = new StringConverter<String>() {
            @Override
            public String toString(String object) {
                if (object == null) return "";
                return formatPattern.format(object);
            }

            @Override
            public String fromString(String string) {
                if (string == null || string.length() == 0) return null;
                return formatPattern.parse(string);
            }
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(stringConverter);

        TextField textField = new TextField();
        textField.setTextFormatter(textFormatter);
    }

    void exempleBind() {


        StringConverter<String> stringConverter = new StringConverter<String>() {
            @Override
            public String toString(String object) {
                if (object == null) return "";
                return formatPattern.format(object);
            }

            @Override
            public String fromString(String string) {
                if (string == null || string.length() == 0) return null;
                return formatPattern.parse(string);
            }
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(stringConverter);

        StringProperty stringProperty = new SimpleStringProperty();
        stringProperty.bindBidirectional(textFormatter.valueProperty());
    }
}