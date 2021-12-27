package koperasisimpanpinjam;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Perusahaan extends Nasabah{
    private StringProperty nib;

    public Perusahaan(int idNasabah, String nama, String alamat, ArrayList<Rekening> rekening, String nib) {
        super(idNasabah, nama, alamat, rekening);
        this.nib = new SimpleStringProperty(nib);
    }

    public Perusahaan(int idNasabah, String nama, String alamat, Rekening rekening, String nib) {
        super(idNasabah, nama, alamat, rekening);
        this.nib = new SimpleStringProperty(nib);
    }

    public String getNib() {
        return nib.get();
    }

    public StringProperty nibProperty() {
        return nib;
    }

    public void setNib(String nib) {
        this.nib.set(nib);
    }
    
    @Override
    public void print() {
        System.out.println ("NIB : "+ nib);
    }
}
    
