package Models;

public enum OrderStatus{
    AWAITING_DEPARTURE(0), OUT_FOR_DELIVERY(1), DELIVERED(2), CANCELED(3);

    private int _value;

    OrderStatus(int value){
        this._value = value;
    }

    public int getValue(){
        return _value;
    }
}
