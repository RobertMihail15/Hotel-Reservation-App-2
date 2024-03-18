package aplicatia3;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
/**
*
* @author vali
*/
public class Aplicatia3Controller implements Initializable {

 @FXML
 private TableView<Hotel> tabela_Hoteluri;
 @FXML
 private Tab tab_Hoteluri;
 @FXML
 private Button buton_IncarcareHoteluri;

 @FXML
 private TableColumn<Hotel, Integer> atribut_idhotel;
 @FXML
 private TableColumn<Hotel, String> atribut_NumeP;
 @FXML
 private TableColumn<Hotel, String> atribut_AdresaP;
 @FXML
 private TableColumn<Hotel, String> atribut_NumarSteleP;
 @FXML
 private TableColumn<Hotel, String> atribut_SpatiiCazareP;
 @FXML
 private TableView<Camera> tabela_Camere;
 @FXML
 private Tab tab_Camere;
 @FXML
 private Button buton_IncarcareCamere;
 @FXML
 private TableColumn<Camera, Integer> atribut_idcamera;
 @FXML
 private TableColumn<Camera, String> atribut_TipCameraM;
 @FXML
 private TableColumn<Camera, String> atribut_DotariM;
 @FXML
 private TableColumn<Camera, String> atribut_NumarCameraM;
 @FXML
 private TableView<Rezervare> tabela_Rezervari;
 @FXML
 private Tab tab_Rezervari;
 @FXML
 private Button buton_IncarcareRezervari;
 @FXML
 private TableColumn<Rezervare, Integer> atribut_idrezervare;
 @FXML
 private TableColumn<Rezervare, String> atribut_idhotel1;
 @FXML
 private TableColumn<Rezervare, String> atribut_idcamera1;
 @FXML
 private TableColumn<Rezervare, String> atribut_DataRezervareN;
 @FXML
 private TableColumn<Rezervare, String> atribut_SumaRezervareN;
 @FXML
 private TableColumn<Rezervare, String> atribut_PerioadaRezervataN;
 @FXML
 private TextField NumeHotelP;
 @FXML
 private TextField AdresaP;
 @FXML
 private TextField NumarSteleP;
 @FXML
 private TextField SpatiiCazareP;
 @FXML
 private TextField DRP;
 @FXML
 private TextField SRP;
 @FXML
 private TextField PRP;
 @FXML
 private ComboBox<Hotel> combobox_hotel;
 @FXML
 private ComboBox<Camera> combobox_camera;
 @FXML
 private TextField tipcameraK;
 @FXML
 private TextField dotariK;
 @FXML
 private TextField numarcameraK;
 @FXML
 private TextField mNumeH;
 @FXML
 private TextField mAdresaH;
 @FXML
 private TextField mSteleH;
 @FXML
 private TextField mSCH;
 @FXML
 private TextField mtipC;
 @FXML
 private TextField mdotariC;
 @FXML
 private TextField mnumarC;
 @FXML
 private TextField mDRR;
 @FXML
 private TextField mSRR;
 @FXML
 private TextField mPRR;
 
 
 
 
 private ObservableList<Hotel> dateHoteluri;
 private ObservableList<Camera> dateCamere;
 private ObservableList<Rezervare> dateRezervari;
 private DBOperations jb;
 private Hotel hotelmodificat;
 Integer hotelmodificatID;
 private Camera cameramodificat;
 Integer cameramodificatID;
 private Rezervare rezervaremodificat;
 Integer rezervaremodificatID;


 @Override
 public void initialize(URL url, ResourceBundle rb) {
		jb = new DBOperations();
		try {
	        // Initialize ComboBox elements
	        jb.connect();

	        dateHoteluri = FXCollections.observableArrayList();
	        dateCamere = FXCollections.observableArrayList();

	        ResultSet rsh = jb.vedeTabel("hoteluri");
	        ResultSet rsc = jb.vedeTabel("camere");

	        while (rsh.next()) {
	            dateHoteluri.add(new Hotel(rsh.getInt("idhotel"), rsh.getString("nume_hotel"), rsh.getString("adresa"), rsh.getString("numar_stele"), rsh.getString("spatii_cazare")));
	        }

	        combobox_hotel.setItems(dateHoteluri);

	        while (rsc.next()) {
	            dateCamere.add(new Camera(rsc.getInt("idcamera"), rsc.getString("tip_camera"), rsc.getString("dotari"), rsc.getString("numar_camera")));
	        }

	        combobox_camera.setItems(dateCamere);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            jb.disconnect();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
 }
 
 
	
	

 @FXML
 private void incarcaHoteluri(ActionEvent event) throws SQLException, Exception {
 jb.connect();
 dateHoteluri = FXCollections.observableArrayList();
 ResultSet rs = jb.vedeTabel("hoteluri");
 while (rs.next()) {
 dateHoteluri.add(new Hotel(rs.getInt("idhotel"), rs.getString("nume_hotel"), rs.getString("adresa"), rs.getString("numar_stele"), rs.getString("spatii_cazare")));
 }
 atribut_idhotel.setCellValueFactory(new PropertyValueFactory<>("idhotel"));
 atribut_NumeP.setCellValueFactory(new PropertyValueFactory<>("nume_hotel"));
 atribut_AdresaP.setCellValueFactory(new PropertyValueFactory<>("adresa"));
 atribut_NumarSteleP.setCellValueFactory(new PropertyValueFactory<>("numar_stele"));
 atribut_SpatiiCazareP.setCellValueFactory(new PropertyValueFactory<>("spatii_cazare"));

 tabela_Hoteluri.setItems(null);
 tabela_Hoteluri.setItems(dateHoteluri);
 jb.disconnect();
 }
 @FXML
	private void adaugaHoteluri(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AdaugaHotel.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	
	}
	
	@FXML
	private void adaugaHotel(ActionEvent event) throws SQLException, Exception {
	
		
		
			String nume_hotel = NumeHotelP.getText();
			String adresa = AdresaP.getText();
			String numar_stele = NumarSteleP.getText();
			String spatii_cazare = SpatiiCazareP.getText();
			jb.connect();
			jb.adaugaHotel(nume_hotel, adresa, numar_stele, spatii_cazare);
			
			incarcaHoteluri(event);
			
		
		    jb.disconnect();
		}
	
	@FXML
	private void stergeHoteluri(ActionEvent event) throws SQLException, Exception {
	    try {
	        jb.connect();
	        Hotel hotel = tabela_Hoteluri.getSelectionModel().getSelectedItem();

	        if (hotel != null) {
	            jb.stergeDateTabela("hoteluri", "idhotel", String.valueOf(hotel.getIdhotel()));
	        } else {
	            System.out.println("Nu s-a putut face stergerea hotelului!");
	        }

	        incarcaHoteluri(event);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error deleting hotel from the database: " + e.getMessage());
	    } finally {
	        jb.disconnect();
	    }
	}
	
	
	void setObject(Hotel hotel) {
		 hotelmodificat = hotel;
		 mNumeH.setText(hotelmodificat.getNumeHotel());
		 mAdresaH.setText(hotelmodificat.getAdresa());
		 mSteleH.setText(hotelmodificat.getNumarStele());
		 mSCH.setText(hotelmodificat.getSpatiiCazare());
		 hotelmodificatID = hotelmodificat.getIdhotel();
		 }
	@FXML
	private void modificaHoteluri(ActionEvent event) throws IOException {
	    // Get the FXMLLoader
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificaHotel.fxml"));
	    AnchorPane root = loader.load();

	    // Get the controller associated with the FXML file
	    Aplicatia3Controller modificaHotelController = loader.getController();

	    // Set the selected hotel in the controller
	    Hotel selectedHotel = tabela_Hoteluri.getSelectionModel().getSelectedItem();
	    modificaHotelController.setObject(selectedHotel);

	    // Create and show the stage
	    Stage stage = new Stage();
	    stage.initModality(Modality.APPLICATION_MODAL);
	    Scene scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}

	@FXML
	private void modificaHotel(ActionEvent event) throws SQLException, Exception {
	    try {
	        jb.connect();

	        // Get the updated values
	        String nume_hotel = mNumeH.getText();
	        String adresa = mAdresaH.getText();
	        String numar_stele = mSteleH.getText();
	        String spatii_cazare = mSCH.getText();

	        // Update the database
	        String[] valori = {nume_hotel, adresa, numar_stele, spatii_cazare};
	        String[] campuri = {"nume_hotel", "adresa", "numar_stele", "spatii_cazare"};
	        jb.modificaTabela("hoteluri", "idhotel", hotelmodificatID, campuri, valori);

	        // Update the selectedHotel object
	        hotelmodificat.setNumeHotel(nume_hotel);
	        hotelmodificat.setAdresa(adresa);
	        hotelmodificat.setNumarStele(numar_stele);
	        hotelmodificat.setSpatiiCazare(spatii_cazare);

	        // Update the UI components
	        tabela_Hoteluri.refresh();

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error updating hotel: " + e.getMessage());
	    } finally {
	        jb.disconnect();
	    }
	}

 
 
 
 
 @FXML
 private void incarcaCamere(ActionEvent event) throws SQLException, Exception {
 jb.connect();
 dateCamere = FXCollections.observableArrayList();
 ResultSet rs = jb.vedeTabel("camere");
 while (rs.next()) {
 dateCamere.add(new Camera(rs.getInt("idcamera"), rs.getString("tip_camera"), rs.getString("dotari"), rs.getString("numar_camera")));
 }
 atribut_idcamera.setCellValueFactory(new PropertyValueFactory<>("idcamera"));
 atribut_TipCameraM.setCellValueFactory(new PropertyValueFactory<>("tip_camera"));
 atribut_DotariM.setCellValueFactory(new PropertyValueFactory<>("dotari"));
 atribut_NumarCameraM.setCellValueFactory(new PropertyValueFactory<>("numar_camera"));

 tabela_Camere.setItems(null);
 tabela_Camere.setItems(dateCamere);
 jb.disconnect();
 }
 @FXML
	private void adaugaCamere(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AdaugaCamera.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	
	}
	
	@FXML
	private void adaugaCamera(ActionEvent event) throws SQLException, Exception {
	
		
		
			String tip_camera = tipcameraK.getText();
			String dotari = dotariK.getText();
			String numar_camera = numarcameraK.getText();
			
			jb.connect();
			jb.adaugaCamera(tip_camera, dotari, numar_camera);
			
			incarcaHoteluri(event);
			
		
		    jb.disconnect();
		}
 @FXML
	private void stergeCamere(ActionEvent event) throws SQLException, Exception {
	    try {
	        jb.connect();
	        Camera camera = tabela_Camere.getSelectionModel().getSelectedItem();

	        if (camera != null) {
	            jb.stergeDateTabela("camere", "idcamera", String.valueOf(camera.getIdcamera()));
	        } else {
	            System.out.println("Nu s-a putut face stergerea hotelului!");
	        }

	        incarcaHoteluri(event);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error deleting hotel from the database: " + e.getMessage());
	    } finally {
	        jb.disconnect();
	    }
	}
 void setObject(Camera camera) {
	 cameramodificat = camera;
	 mtipC.setText(cameramodificat.getTipCamera());
	 mdotariC.setText(cameramodificat.getDotari());
	 mnumarC.setText(cameramodificat.getNumarCamera());
	
	 cameramodificatID = cameramodificat.getIdcamera();
	 }
@FXML
private void modificaCamere(ActionEvent event) throws IOException {
    // Get the FXMLLoader
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificaCamera.fxml"));
    AnchorPane root = loader.load();

    // Get the controller associated with the FXML file
    Aplicatia3Controller modificaCameraController = loader.getController();

    // Set the selected hotel in the controller
    Camera selectedCamera = tabela_Camere.getSelectionModel().getSelectedItem();
    modificaCameraController.setObject(selectedCamera);

    // Create and show the stage
    Stage stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}

@FXML
private void modificaCamera(ActionEvent event) throws SQLException, Exception {
    try {
        jb.connect();

        // Get the updated values
        String tip_camera = mtipC.getText();
        String dotari = mdotariC.getText();
        String numar_camera = mnumarC.getText();
        

        // Update the database
        String[] valori = {tip_camera, dotari, numar_camera};
        String[] campuri = {"tip_camera", "dotari", "numar_camera"};
        jb.modificaTabela("camere", "idcamera", cameramodificatID, campuri, valori);

        // Update the selectedHotel object
        cameramodificat.setTipCamera(tip_camera);
        cameramodificat.setDotari(dotari);
        cameramodificat.setNumarCamera(numar_camera);
        

        // Update the UI components
        tabela_Camere.refresh();

    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error updating hotel: " + e.getMessage());
    } finally {
        jb.disconnect();
    }
}


 @FXML
 private void incarcaRezervari(ActionEvent event) throws SQLException, Exception {
 jb.connect();
 dateRezervari = FXCollections.observableArrayList();
 ResultSet rs = jb.vedeTabel("rezervari");
 while (rs.next()) {
 dateRezervari.add(new Rezervare(rs.getInt("idrezervare"), rs.getInt("idhotel"), rs.getInt("idcamera"), rs.getString("data_rezervare"), rs.getString("suma_rezervare"), rs.getString("perioada_rezervata")));
 }
 atribut_idrezervare.setCellValueFactory(new PropertyValueFactory<>("idrezervare"));
 atribut_idhotel1.setCellValueFactory(new PropertyValueFactory<>("idhotel"));
 atribut_idcamera1.setCellValueFactory(new PropertyValueFactory<>("idcamera"));
 atribut_DataRezervareN.setCellValueFactory(new PropertyValueFactory<>("data_rezervare"));
 atribut_SumaRezervareN.setCellValueFactory(new PropertyValueFactory<>("suma_rezervare"));
 atribut_PerioadaRezervataN.setCellValueFactory(new PropertyValueFactory<>("perioada_rezervata"));


 tabela_Rezervari.setItems(null);
 tabela_Rezervari.setItems(dateRezervari);
 jb.disconnect();
 }
 @FXML
	private void adaugaRezervari(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AdaugaRezervare.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
 @FXML
 private void adaugaRezervare(ActionEvent event) throws SQLException, Exception {
     // Get selected items and other fields
     int idhotel = combobox_hotel.getSelectionModel().getSelectedItem().getIdhotel();
     int idcamera = combobox_camera.getSelectionModel().getSelectedItem().getIdcamera();
     String data_rezervare = DRP.getText();
     String suma_rezervare = SRP.getText();
     String perioada_rezervata = PRP.getText();

     try {
         // Validate user input
         if (data_rezervare.isEmpty() || suma_rezervare.isEmpty() || perioada_rezervata.isEmpty()) {
             System.out.println("Please fill in all fields.");
             return;
         }

         jb.connect();

         // Fetch hotels and cameras from the database
         dateHoteluri = FXCollections.observableArrayList();
         dateCamere = FXCollections.observableArrayList();

         ResultSet rsh = jb.vedeTabel("hoteluri");
         ResultSet rsc = jb.vedeTabel("camere");

         while (rsh.next()) {
             dateHoteluri.add(new Hotel(rsh.getInt("idhotel"), rsh.getString("nume_hotel"), rsh.getString("adresa"), rsh.getString("numar_stele"), rsh.getString("spatii_cazare")));
         }

         combobox_hotel.setItems(dateHoteluri);

         while (rsc.next()) {
             dateCamere.add(new Camera(rsc.getInt("idcamera"), rsc.getString("tip_camera"), rsc.getString("dotari"), rsc.getString("numar_camera")));
         }

         combobox_camera.setItems(dateCamere);

         // Add reservation
         jb.adaugaRezervare(idhotel, idcamera, data_rezervare, suma_rezervare, perioada_rezervata);

         // Reload reservations
         incarcaRezervari(event);
     } catch (NullPointerException e) {
         // Handle if no item is selected in ComboBox
         e.printStackTrace();
         System.out.println("Please select a hotel and camera.");
     } finally {
         jb.disconnect();
     }

     // Close the stage
     Stage stage = (Stage) DRP.getScene().getWindow();
     stage.close();
 }
 
	@FXML
	private void stergeRezervare(ActionEvent event) throws SQLException, Exception {
	    try {
	        jb.connect();
	        Rezervare rezervare = tabela_Rezervari.getSelectionModel().getSelectedItem();

	        if (rezervare != null) {
	            jb.stergeDateTabela("rezervari", "idrezervare", String.valueOf(rezervare.getIdrezervare()));
	        } else {
	            System.out.println("Nu s-a putut face stergerea hotelului!");
	        }

	        incarcaRezervari(event);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error deleting hotel from the database: " + e.getMessage());
	    } finally {
	        jb.disconnect();
	    }
	}
	void setObject(Rezervare rezervare) {
		 rezervaremodificat = rezervare;
		 mDRR.setText(rezervaremodificat.getDataRezervare());
		 mSRR.setText(rezervaremodificat.getSumaRezervare());
		 mPRR.setText(rezervaremodificat.getPerioadaRezervata());
		
		 rezervaremodificatID = rezervaremodificat.getIdrezervare();
		 }
	@FXML
	private void modificaRezervari(ActionEvent event) throws IOException {
	    // Get the FXMLLoader
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificaRezervare.fxml"));
	    AnchorPane root = loader.load();

	    // Get the controller associated with the FXML file
	    Aplicatia3Controller modificaRezervareController = loader.getController();

	    // Set the selected hotel in the controller
	    Rezervare selectedRezervare = tabela_Rezervari.getSelectionModel().getSelectedItem();
	    modificaRezervareController.setObject(selectedRezervare);

	    // Create and show the stage
	    Stage stage = new Stage();
	    stage.initModality(Modality.APPLICATION_MODAL);
	    Scene scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}

	@FXML
	private void modificaRezervare(ActionEvent event) throws SQLException, Exception {
	    try {
	        jb.connect();

	        // Get the updated values
	        String data_rezervare = mDRR.getText();
	        String suma_rezervare = mSRR.getText();
	        String perioada_rezervata = mPRR.getText();
	        

	        // Update the database
	        String[] valori = {data_rezervare, suma_rezervare, perioada_rezervata};
	        String[] campuri = {"data_rezervare", "suma_rezervare", "perioada_rezervata"};
	        jb.modificaTabela("rezervari", "idrezervare", rezervaremodificatID, campuri, valori);

	        // Update the selectedHotel object
	        rezervaremodificat.setDataRezervare(data_rezervare);
	        rezervaremodificat.setSumaRezervare(suma_rezervare);
	        rezervaremodificat.setPerioadaRezervare(perioada_rezervata);
	        

	        // Update the UI components
	        tabela_Rezervari.refresh();

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error updating hotel: " + e.getMessage());
	    } finally {
	        jb.disconnect();
	    }
	}

}