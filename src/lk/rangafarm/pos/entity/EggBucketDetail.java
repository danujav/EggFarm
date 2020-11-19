package lk.rangafarm.pos.entity;

public class EggBucketDetail implements SuperEntity{
    private String eggBucketId;
    private String productId;
    private int qty;

    public EggBucketDetail() {
    }

    public EggBucketDetail(String eggBucketId, String productId, int qty) {
        this.setEggBucketId(eggBucketId);
        this.setProductId(productId);
        this.setQty(qty);
    }

    public String getEggBucketId() {
        return eggBucketId;
    }

    public void setEggBucketId(String eggBucketId) {
        this.eggBucketId = eggBucketId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "EggBucketDetail{" +
                "eggBucketId='" + eggBucketId + '\'' +
                ", productId='" + productId + '\'' +
                ", qty=" + qty +
                '}';
    }
}
