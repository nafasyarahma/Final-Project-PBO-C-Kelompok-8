package koperasisimpanpinjam;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public abstract class Nasabah {
    private IntegerProperty idNasabah;
    private StringProperty nama;
    private StringProperty alamat;
    private IntegerProperty numRek;
    private ArrayList <Rekening> rekening;

    public Nasabah(int idNasabah, String nama, String alamat, ArrayList<Rekening> rekening) {
        this.idNasabah = new SimpleIntegerProperty(idNasabah);
        this.nama = new SimpleStringProperty(nama);
        this.alamat = new SimpleStringProperty(alamat);
        this.rekening = rekening;
        this.numRek = new SimpleIntegerProperty(rekening.size());
    }

    public Nasabah(int idNasabah, String nama, String alamat, Rekening rekenings) {
        rekening = new ArrayList<>();
        this.idNasabah = new SimpleIntegerProperty(idNasabah);
        this.nama = new SimpleStringProperty(nama);
        this.alamat = new SimpleStringProperty(alamat);
        this.rekening.add(rekenings);
        this.numRek = new SimpleIntegerProperty(this.rekening.size());
    }

    public int getIdNasabah() {
        return idNasabah.get();
    }

    public IntegerProperty idNasabahProperty() {
        return idNasabah;
    }

    public void setIdNasabah(int idNasabah) {
        this.idNasabah.set(idNasabah);
    }

    public String getNama() {
        return nama.get();
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    public StringProperty namaProperty() {
        return nama;
    }

    public String getAlamat() {
        return alamat.get();
    }

    public StringProperty alamatProperty() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat.set(alamat);
    }

    public int getNumRek() {
        return numRek.get();
    }

    public IntegerProperty numRekProperty() {
        return numRek;
    }

    public void setNumRek(int numRek) {
        this.numRek.set(numRek);
    }

    public ArrayList<Rekening> getRekening() {
        return rekening;
    }

    public void setRekening(ArrayList<Rekening> rekening) {
        this.rekening = rekening;
    }

    public void tambahRekening(Rekening rek) {
        rekening.add(rek);
    }
    
    abstract public void print();
}