package format.mask;

public class AnythingCharacter extends _MaskCharacter {

    public AnythingCharacter(char value) {
        super(value);
    }

    public boolean accept(char value) {
        return true;
    }
}
