package com.example.quan_ly_nha_thuoc.Model;

public class CardViewModel {

    String cardName;
    int imageResourceId;
    public CardViewModel(){}
    public CardViewModel(String cardName, int imageResourceId) {
        this.cardName = cardName;
        this.imageResourceId = imageResourceId;
    }

    public String getCardName() {
        return cardName;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

}
