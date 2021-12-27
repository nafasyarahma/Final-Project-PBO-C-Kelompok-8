package koperasisimpanpinjam;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import java.util.ArrayList;

public class Individu extends Nasabah{
    private LongProperty nik;
    private LongProperty npwp;


    public Individu(Integer idNasabah, String nama, String alamat, ArrayList<Rekening> rekening,long nik,long npwp) {
        super(idNasabah, nama, alamat, rekening);
        this.nik = new SimpleLongProperty(nik);
        this.npwp = new SimpleLongProperty(npwp);
    }

    public Individu(Integer idNasabah, String nama, String alamat, Rekening rekening,long nik,long npwp) {
        super(idNasabah, nama, alamat, rekening);
        this.nik = new SimpleLongProperty(nik);
        this.npwp = new SimpleLongProperty(npwp);
    }

    public long getNik() {
        return nik.get();
    }

    public LongProperty nikProperty() {
        return nik;
    }

    public void setNik(long nik) {
        this.nik.set(nik);
    }

    public long getNpwp() {
        return npwp.get();
    }

    public LongProperty npwpProperty() {
        return npwp;
    }

    public void setNpwp(long npwp) {
        this.npwp.set(npwp);
    }
    
    @Override
    public void print() {
        System.out.println ("NIK : "+ nik);
        System.out.println ("NPWP : "+ npwp);
    }
}
