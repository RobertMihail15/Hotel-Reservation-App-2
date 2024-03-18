package aplicatia3;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
*
* @author vali
*/
public class Camera {
private final IntegerProperty idcamera;
private final StringProperty tip_camera;
private final StringProperty dotari;
 private final StringProperty numar_camera;
 public Camera(Integer idcamera, String tip_camera, String dotari, String numar_camera) {
 this.idcamera = new SimpleIntegerProperty(idcamera);
 this.tip_camera = new SimpleStringProperty(tip_camera);
 this.dotari = new SimpleStringProperty(dotari);
 this.numar_camera = new SimpleStringProperty(numar_camera);
 }
 public Integer getIdcamera() {
 return idcamera.get();
 }
 public String getTipCamera() {
 return tip_camera.get();
 }
 public String getDotari() {
 return dotari.get();
 }
 public String getNumarCamera() {
 return numar_camera.get();
 }
 public void setIdcamera(Integer valoare) {
 idcamera.set(valoare);
 }
 public void setTipCamera(String valoare) {
 tip_camera.set(valoare);
 }
 public void setDotari(String valoare) {
 dotari.set(valoare);
 }
 public void setNumarCamera(String valoare) {
 numar_camera.set(valoare);
 }
 public IntegerProperty idcameraProperty() {
 return idcamera;
 }
 public StringProperty tip_cameraProperty() {
 return tip_camera;
 }
 public StringProperty dotariProperty() {
 return dotari;
 }
 public StringProperty numar_cameraProperty() {
 return numar_camera;
 }
 @Override
 public String toString() {
     return getTipCamera() + " - " + getDotari() + " - " + getNumarCamera(); 
 }
}