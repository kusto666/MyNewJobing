package main.jobing.mynewjobing;


public class MyOffers implements Comparable< MyOffers > {
    public int iIndex;
    public String id;
    public String cat_id;
    public String name;
    public String price;
    public String img_sm;
    public String artikul;
    public String brand_id;
    public String descr;
    public String labels;// Y - значит хит!

    public String country_id;
    public String min_qty;
    public String qty;
    public String measure;
    public String can_buy;


    public String segment_id;
    //public String kitchen_id;

    private String small, medium, large;

    public MyOffers() {

    }
    public String getMedium() {
        return medium;
    }

    // Это для выбора еденичного товара по запросу из базы данных:
/*    public MyOffers(int iIndex,
                    String id,
                    String cat_id,
                    String name,
                    String price,
                    String img_sm,
                    String artikul,
                    String brand_id,
                    String descr,
                    String hit,
                            String country_id,
                            String min_qty,
                            String qty,
                            String measure,
                            String can_buy) {
        this.iIndex = iIndex;
        this.id = id;
        this.cat_id = cat_id;
        this.name = name;
        this.price = price;
        this.img_sm = img_sm;
        this.artikul = artikul;
        this.brand_id = brand_id;
        this.descr = descr;
        this.hit = hit;

        this.country_id = country_id;
        this.min_qty = min_qty;
        this.qty = qty;
        this.measure = measure;
        this.can_buy = can_buy;
    }*/



    public MyOffers(int iIndex,
                    String id,
                    String cat_id,
                    String name,
                    String price,
                    String img_sm,
                    String artikul,
                    String brand_id,
                    String country_id,
                    String min_qty,
                    String qty,
                    String measure,
                    String can_buy,
                    String labels) {
        this.iIndex = iIndex;
        this.id = id;
        this.cat_id = cat_id;
        this.name = name;
        this.price = price;
        this.img_sm = img_sm;
        this.artikul = artikul;
        this.brand_id = brand_id;


        this.country_id = country_id;
        this.min_qty = min_qty;
        this.qty = qty;
        this.measure = measure;
        this.can_buy = can_buy;
        this.labels = labels;
    }


    public Integer getId() {
        return iIndex;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public int compareTo(MyOffers o) {
        return this.getId().compareTo(o.getId());
    }
}
