package pando.iMobile.phondex.phondex_main.db.entitys;

import androidx.room.Entity;

@Entity
public class Phone {


    int id;
    String name;
    String brand;
    String imgs;
    String details;
    String other_details;
    String shop_token;
    String price;

    public Phone(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getOther_details() {
        return other_details;
    }

    public void setOther_details(String other_details) {
        this.other_details = other_details;
    }

    public String getShop_token() {
        return shop_token;
    }

    public void setShop_token(String shop_token) {
        this.shop_token = shop_token;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
