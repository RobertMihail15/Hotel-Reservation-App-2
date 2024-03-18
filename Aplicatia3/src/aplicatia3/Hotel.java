package aplicatia3;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
*
* @author vali
*/
public class Hotel {
 private final IntegerProperty idhotel;
 private final StringProperty nume_hotel;
 private final StringProperty adresa;
 private final StringProperty numar_stele;
 private final StringProperty spatii_cazare;
 public Hotel(Integer idhotel, String nume_hotel, String adresa, String numar_stele, String spatii_cazare) {
 this.idhotel = new SimpleIntegerProperty(idhotel);
 this.nume_hotel = new SimpleStringProperty(nume_hotel);
 this.adresa = new SimpleStringProperty(adresa);
 this.numar_stele = new SimpleStringProperty(numar_stele);
 this.spatii_cazare = new SimpleStringProperty(spatii_cazare);
 }
 public Integer getIdhotel() {
 return idhotel.get();
 }
 public String getNumeHotel() {
 return nume_hotel.get();
 }
 public String getAdresa() {
 return adresa.get();
 }
 public String getNumarStele() {
 return numar_stele.get();
 }
 public String getSpatiiCazare() {
 return spatii_cazare.get();
	 }
 public void setIdhotel(Integer valoare) {
 idhotel.set(valoare);
 }
 public void setNumeHotel(String valoare) {
 nume_hotel.set(valoare);
 }
 public void setAdresa(String valoare) {
 adresa.set(valoare);
 }
 public void setNumarStele(String valoare) {
 numar_stele.set(valoare);
 }
 public void setSpatiiCazare(String valoare) {
 spatii_cazare.set(valoare);
	 }
 public IntegerProperty idhotelProperty() {
 return idhotel;
 }
 public StringProperty nume_hotelProperty() {
 return nume_hotel;
 }
 public StringProperty adresaProperty() {
 return adresa;
 }
 public StringProperty numar_steleProperty() {
 return numar_stele;
 }
 public StringProperty spatii_cazareProperty() {
 return spatii_cazare;
	 }
 
 @Override
 public String toString() {
     return getNumeHotel() + " - " + getAdresa() + " - " + getNumarStele()+ " - " + getSpatiiCazare(); // Customize this based on your preferences
 }
}