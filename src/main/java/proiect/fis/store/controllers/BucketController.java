package proiect.fis.store.controllers;

import javafx.collections.ObservableList;
import proiect.fis.store.model.Customer;
import proiect.fis.store.model.Product;

import java.util.Observable;

public class BucketController {
    private Customer customer ;
    private ObservableList<Product> bucketList;


    public void setData (Customer customer, ObservableList<Product> bucket) {
        this.customer = customer;
        this.bucketList = bucket;
    }
}
