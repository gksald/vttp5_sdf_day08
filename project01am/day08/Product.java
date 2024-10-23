package day08;

import java.util.Date;

public class Product {
    private Long id;
    private String prodName;
    private String prodDesc;
    private String prodCat;
    private Float price;
    private Date createdDate;

    // Constructor
    public Product(Long id, String prodName, String prodDesc, String prodCat, Float price, Date createdDate) {
        this.id = id;
        this.prodName = prodName;
        this.prodDesc = prodDesc;
        this.prodCat = prodCat;
        this.price = price;
        this.createdDate = createdDate;
    }

    // Default constructor
    public Product() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public String getProdCat() {
        return prodCat;
    }

    public void setProdCat(String prodCat) {
        this.prodCat = prodCat;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    // toString method
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", prodName = '" + prodName + '\'' +  // the '\'' adds a single colon (') as part of a string.
                ", prodDesc = '" + prodDesc + '\'' +
                ", prodCat = '" + prodCat + '\'' +
                ", price = " + price +
                ", createdDate = " + createdDate +
                '}';        // '}' or "}" outputs the same, just that usu use '' for character and "" for string.
    }
}

