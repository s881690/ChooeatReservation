package enums;

public enum Gender {
    male(1), female(2);

    private int value; // 欄位

    Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
