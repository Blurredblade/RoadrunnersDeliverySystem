package Models;

public class Business {
    private String address;
    private float deliveryBlockRate;
    private float deliveryBase;
    private float bonusRate;
    private int bonusGracePeriod;

    public Business(String address, float dBlockRate, float dBase, float bRate, int bGracePeriod){
        setAddress(address);
        setDeliveryBlockRate(dBlockRate);
        setDeliveryBase(dBase);
        setBonusRate(bRate);
        setBonusGracePeriod(bGracePeriod);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getDeliveryBlockRate() {
        return deliveryBlockRate;
    }

    public void setDeliveryBlockRate(float deliveryBlockRate) {
        this.deliveryBlockRate = deliveryBlockRate;
    }

    public float getDeliveryBase() {
        return deliveryBase;
    }

    public void setDeliveryBase(float deliveryBase) {
        this.deliveryBase = deliveryBase;
    }

    public float getBonusRate() {
        return bonusRate;
    }

    public void setBonusRate(float bonusRate) {
        this.bonusRate = bonusRate;
    }

    public int getBonusGracePeriod() {
        return bonusGracePeriod;
    }

    public void setBonusGracePeriod(int bonusGracePeriod) {
        this.bonusGracePeriod = bonusGracePeriod;
    }


}
