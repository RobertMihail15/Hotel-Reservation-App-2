package aplicatia3;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
*
* @author vali
*/
public class Rezervare {
 private final IntegerProperty idrezervare;
 private final IntegerProperty idhotel;
 private final IntegerProperty idcamera;
 private final StringProperty data_rezervare;
 private final StringProperty suma_rezervare;
 private final StringProperty perioada_rezervata;
 public Rezervare(Integer idrezervare, Integer idhotel, Integer idcamera, String data_rezervare, String suma_rezervare, String perioada_rezervata) {
 this.idrezervare = new SimpleIntegerProperty(idrezervare);
 this.idhotel = new SimpleIntegerProperty(idhotel);
 this.idcamera = new SimpleIntegerProperty(idcamera);
 this.data_rezervare = new SimpleStringProperty(data_rezervare);
 this.suma_rezervare = new SimpleStringProperty(suma_rezervare);
 this.perioada_rezervata = new SimpleStringProperty(perioada_rezervata);
 }
 public Integer getIdrezervare() {
 return idrezervare.get();
 }
 public Integer getIdhotel() {
	 return idhotel.get();
	 }
 public Integer getIdcamera() {
	 return idcamera.get();
	 }
 public String getDataRezervare() {
 return data_rezervare.get();
 }
 public String getSumaRezervare() {
 return suma_rezervare.get();
 }
 public String getPerioadaRezervata() {
 return perioada_rezervata.get();
 }
 public void setIdrezervare(Integer valoare) {
 idrezervare.set(valoare);
 }
 public void setIdhotel(Integer valoare) {
	 idhotel.set(valoare);
	 }
 public void setIdcamera(Integer valoare) {
	 idcamera.set(valoare);
	 }
 public void setDataRezervare(String valoare) {
 data_rezervare.set(valoare);
 }
 public void setSumaRezervare(String valoare) {
 suma_rezervare.set(valoare);
 }
 public void setPerioadaRezervare(String valoare) {
 perioada_rezervata.set(valoare);
 }
 public IntegerProperty idrezervareProperty() {
 return idrezervare;
 }
 public IntegerProperty idhotelProperty() {
	 return idhotel;
	 }
 public IntegerProperty idcameraProperty() {
	 return idcamera;
	 }
 public StringProperty data_rezervareProperty() {
 return data_rezervare;
 }
 public StringProperty suma_rezervareProperty() {
 return suma_rezervare;
 }
 public StringProperty perioada_rezervataProperty() {
 return perioada_rezervata;
 }
}