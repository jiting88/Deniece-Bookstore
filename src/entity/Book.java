package entity;

/**
 * Created by jicl on 16/5/4.
 */
public class Book {
    int BookID;
    String title;
    String author;
    double price;
    String description;
    String category;

    public int getBookID(){
        return BookID;
    }

    public void setBookID(int BookID){
        this.BookID=BookID;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author=author;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price=price;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public String getCategory(){ return category; }

    public void setCategory(String category){ this.category=category; }
}
