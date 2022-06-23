package anno.basic;

public class Good
{
    @GoodsName("airFresh")
    private String goodNmae;

    @GoodsType(goodsType = GoodsType.Type.LIFE)
    private String goodType;

    public void setGoodNmae(String goodNmae) {
        this.goodNmae = goodNmae;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getGoodNmae() {
        return goodNmae;
    }

    public String getGoodType() {
        return goodType;
    }

    public String toString() {
        return "产品：" + goodNmae + "属于：" + goodType;
    }
}
