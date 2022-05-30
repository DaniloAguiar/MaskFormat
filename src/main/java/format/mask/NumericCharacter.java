package format.mask;


public class NumericCharacter extends _MaskCharacter {

    public NumericCharacter(char value) {
        super(value);
    }

    @Override
    public boolean accept(char value) {
        return Character.isDigit(value);
    }

}