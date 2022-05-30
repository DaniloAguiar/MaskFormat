package format.mask;

import java.util.regex.Pattern;

public class HexCharacter extends _MaskCharacter {

    public HexCharacter(char value) {
        super(value);
    }

    @Override
    public boolean accept(char value) {
        return Pattern.matches("[0-9a-fA-F]", String.valueOf(value));
    }
}