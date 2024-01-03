package enums;

public enum cusState {
    未啟用(0),已啟用(1),停用(2);

    private int value;

    cusState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
