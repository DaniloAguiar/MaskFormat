package format.mask;

public class LowerCaseCharacter extends _MaskCharacter {

    public LowerCaseCharacter(char value) {
        super(value);
    }

    @Override
    public char getValue() {
        return Character.toLowerCase(super.getValue());
    }

    public boolean accept(char value) {
        return Character.isLetter(value);
    }
}