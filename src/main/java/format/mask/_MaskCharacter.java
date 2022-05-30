package format.mask;

public abstract class _MaskCharacter {

    private char value;

    _MaskCharacter(char value) {
        this.value = value;
    }

    public char getValue() {
        return this.value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public void setNull() {
        setValue('\u0000');
    }

    public boolean isNull() {
        return value == '\u0000';
    }

    public boolean isLiteral() {
        return false;
    }

    public abstract boolean accept(char value);

}