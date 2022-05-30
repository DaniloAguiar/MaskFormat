package format.mask;

public class UpperCaseCharacter extends _MaskCharacter {

    public UpperCaseCharacter(char value) {
        super(value);
    }

    @Override
    public char getValue() {
        return Character.toUpperCase(super.getValue());
    }

    public boolean accept(char value) {
        return Character.isLetter(value);
    }
}