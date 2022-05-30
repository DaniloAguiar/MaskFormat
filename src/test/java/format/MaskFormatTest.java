package format;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import org.junit.jupiter.api.Test;

class MaskFormatTest {
    MaskFormat maskFormat = new MaskFormat("###.###.###-##");
    MaskFormat maskFormat2 = new MaskFormat("(##) #####-####");

    @Test
    void exemple() {
        System.out.println(maskFormat.format("00000000000"));
        //#result_format: 000.000.000-00

        System.out.println(maskFormat.parse("000.000.000-00"));
        //#result_parse: 00000000000
    }

    @Test
    void exemple2() {
        System.out.println(maskFormat2.format("00000000000"));
        //#result_format: (00) 00000-0000

        System.out.println(maskFormat2.parse("(00) 00000-0000"));
        //#result_parse: 00000000000
    }

    void exempleTextField() {
        StringConverter<String> stringConverter = new StringConverter<String>() {
            @Override
            public String toString(String object) {
                if (object == null) return "";
                return maskFormat.format(object);
            }

            @Override
            public String fromString(String string) {
                if (string == null || string.length() == 0) return null;
                return maskFormat.parse(string);
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
                return maskFormat.format(object);
            }

            @Override
            public String fromString(String string) {
                if (string == null || string.length() == 0) return null;
                return maskFormat.parse(string);
            }
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(stringConverter);

        StringProperty stringProperty = new SimpleStringProperty();
        stringProperty.bindBidirectional(textFormatter.valueProperty());
    }
}