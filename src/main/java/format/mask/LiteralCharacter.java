package format.mask;

public class LiteralCharacter extends _MaskCharacter {

    public LiteralCharacter(char value) {
        super(value);
    }

    @Override
    public boolean isLiteral() {
        return true;
    }

    @Override
    public void setValue(char value) {
        // Literal can't be changed
    }

    public boolean accept(char value) {
        return false;
    }
}