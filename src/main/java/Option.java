public class Option {
    private int index;
    private String value;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    Option(int i, String value) {
        this.index = i;
        this.value = value;
    }
}
