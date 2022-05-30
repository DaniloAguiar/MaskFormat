package format.mask;

public class AlphaNumericCharacter extends _MaskCharacter {

    public AlphaNumericCharacter(char value) {
        super(value);
    }

    public boolean accept(char value) {
        return Character.isLetterOrDigit(value);
    }
}