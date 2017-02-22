package entity;

/**
 * Created by jicl on 16/5/4.
 */
public class Cart {
    Book book;
    int quantity;

    public Cart(Book book,int quantity){
        this.book=book;
        this.quantity=quantity;
    }

    public Book getBook(){
        return book;
    }

    public void setBook(Book book){
        this.book=book;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
}
