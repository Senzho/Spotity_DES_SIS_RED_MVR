
package controlador;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.StageStyle;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import negocio.Album;
import negocio.Artista;
import negocio.Cancion;
import negocio.GeneroArtista;
import negocio.Usuario;
import org.farng.mp3.MP3File;
import org.farng.mp3.id3.ID3v1;
import org.farng.mp3.TagException;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import serviciosCliente.ClienteAlbum;
import serviciosCliente.ClienteArtista;
import serviciosCliente.ClienteCancion;
import serviciosCliente.ClienteGeneroArtista;
import static sun.audio.AudioPlayer.player;


/**
 * FXML Controller class
 *
 * @author Renato
 */
public class PanelSubirCancionController implements Initializable {

    @FXML
    private Button botonElegirArchivo;
    @FXML
    private Label etiquetaRutaArchivo;
    @FXML
    private Button botonSubirArchivo;
    @FXML
    private RadioButton radioCancion;
    @FXML
    private RadioButton radioAlbum;
    private String tipoArchivo;
    private Usuario usuarioActual;
    private String rutaOrigen;
    private String rutaCancion;
    private String rutaArchivo;
    private String rutaNueva;
    boolean archivoCargado;

    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void iniciarVentana(Usuario usuario){
        this.usuarioActual=usuario;
        tipoArchivo="cancion";
        radioCancion.setSelected(true);
        radioAlbum.setSelected(false);
        archivoCargado=false;
    }

    @FXML
    private void elegirArchivo(ActionEvent event) throws IOException, TagException {
        String fileName="";
        if(tipoArchivo.equals("cancion")){
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "MP3 Files", "mp3");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
               fileName=chooser.getSelectedFile().getAbsolutePath();
            }
            String path = fileName.replace("\\", "\\\\");
            rutaCancion=path;
            etiquetaRutaArchivo.setText(rutaCancion);
            archivoCargado=true;
        }else{
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "ZIP Files", "zip");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
               fileName=chooser.getSelectedFile().getAbsolutePath();
            }
            String path = fileName.replace("\\", "\\\\");
            
            rutaArchivo=path;
            etiquetaRutaArchivo.setText(rutaArchivo);
            archivoCargado=true;
            
        }
        
    }

    private void subirCancion(String direccion, File archivo) throws IOException, TagException{
        String nombreArtista;
        String nombreAlbum;
        String allo;
        String archivoActual=archivo.getAbsolutePath();
        archivoActual = archivoActual.replace("\\", "\\\\");
        System.out.println("Archivo Actual "+archivoActual);
        MP3File mp3file = new MP3File(archivoActual);
                nombreArtista=mp3file.getID3v2Tag().getLeadArtist();
                if((nombreArtista.equals(""))||(nombreArtista.equals(" "))||(nombreArtista.equals(null))){
                    nombreArtista="Desconocido";
                }
                nombreAlbum=mp3file.getID3v2Tag().getAlbumTitle();
                if((nombreAlbum.equals(""))||(nombreAlbum.equals(" "))||(nombreAlbum.equals(null))){
                    nombreAlbum="Desconocido";
                }

                allo = mp3file.getID3v2Tag().getYearReleased();

                Cancion nuevaCancion = new Cancion();
                nuevaCancion.setNombre(mp3file.getID3v2Tag().getSongTitle());
                nuevaCancion.setGenero(mp3file.getID3v2Tag().getSongGenre());
                nuevaCancion.setDuracion(obtenerDuracion(rutaCancion));
                nuevaCancion.setIdArtista(adquirirArtista(nombreArtista, mp3file.getID3v2Tag().getSongGenre()));
                nuevaCancion.setIdAlbum(adquirirAlbum(nombreAlbum, nuevaCancion.getIdArtista(), "nombreCompania", allo));

                if(!cancionExiste(nuevaCancion)){
                    new ClienteCancion().create_JSON(nuevaCancion);
                }
        
        
        
    
    }

    @FXML
    private void subirArchivo(ActionEvent event) throws IOException, TagException {
        String nombreArtista;
        String nombreAlbum;
        String allo;
        if(!archivoCargado){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Error");
            alert.setHeaderText("Error de archivo");
            alert.setContentText("No ha cargado ningún archivo");
            alert.showAndWait();
        }else{
            if(tipoArchivo.equals("cancion")){
                String nombreCompania= JOptionPane.showInputDialog("Por favor ingrese la compañia discografica ");
                MP3File mp3file = new MP3File(rutaCancion);
                nombreArtista=mp3file.getID3v2Tag().getLeadArtist();
                if((nombreArtista.equals(""))||(nombreArtista.equals(" "))||(nombreArtista.equals(null))){
                    nombreArtista="Desconocido";
                }
                nombreAlbum=mp3file.getID3v2Tag().getAlbumTitle();
                if((nombreAlbum.equals(""))||(nombreAlbum.equals(" "))||(nombreAlbum.equals(null))){
                    nombreAlbum="Desconocido";
                }

                allo = mp3file.getID3v2Tag().getYearReleased();

                Cancion nuevaCancion = new Cancion();
                nuevaCancion.setNombre(mp3file.getID3v2Tag().getSongTitle());
                nuevaCancion.setGenero(mp3file.getID3v2Tag().getSongGenre());
                nuevaCancion.setDuracion(obtenerDuracion(rutaCancion));
                nuevaCancion.setIdArtista(adquirirArtista(nombreArtista, mp3file.getID3v2Tag().getSongGenre()));
                nuevaCancion.setIdAlbum(adquirirAlbum(nombreAlbum, nuevaCancion.getIdArtista(), nombreCompania, allo));

                if(!cancionExiste(nuevaCancion)){
                    new ClienteCancion().create_JSON(nuevaCancion);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setTitle("Exito");
                    alert.setHeaderText("Exito al subir archivo");
                    alert.setContentText("La canción fué subida exitosamente al sistema");
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error de archivo");
                    alert.setContentText("Esa Cancion ya existe...");
                    alert.showAndWait();
                }
            }else{
            
                unzip(rutaArchivo, "src/Archivos");
            
                File dir = new File(rutaArchivo);
                String nombreCarpeta=dir.getName();
                nombreCarpeta=nombreCarpeta.substring(0, nombreCarpeta.length()-4);
                String direccion="src/Archivos/"+nombreCarpeta;
                File actual = new File("src/Archivos/"+nombreCarpeta);
                for( File f : actual.listFiles()){
                    subirCancion(direccion, f);
                }

                //Elimina archivos temporales
                actual = new File("src/Archivos/"+nombreCarpeta);
                for( File f : actual.listFiles()){
                    f.delete();
                }

                actual = new File("src/Archivos/");
                for( File f : actual.listFiles()){
                    f.delete();
                }
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Exito");
                alert.setHeaderText("Exito al subir archivo");
                alert.setContentText("El albúm se subió exitosamente al sistema");
                alert.showAndWait();
                
            
            }
        }
        
        
        
    }

    @FXML
    private void setArchivoCancion(ActionEvent event) {
        tipoArchivo="cancion";
        radioCancion.setSelected(true);
        radioAlbum.setSelected(false);
        
    }

    @FXML
    private void setArchivoAlbum(ActionEvent event) {
        tipoArchivo="album";
        radioCancion.setSelected(false);
        radioAlbum.setSelected(true);
    }
    
    public String obtenerDuracion(String ruta){
        String duracion="";
        String minutos="";
        int minutosEnteros;
        String segundos="";
        char[] arregloSegundos;
        try{
            int duration;
            double duracionMinutos;
            File file=new File(ruta);
            AudioFile audioFile = AudioFileIO.read(file);
            duration= audioFile.getAudioHeader().getTrackLength();
            duracionMinutos=(double)duration;
            minutosEnteros=duration/60;
            minutos=String.valueOf(minutosEnteros);
            duracionMinutos=duracionMinutos/60;
            double x = duracionMinutos - (long) duracionMinutos;
            x=(x*60)/100;
            
            segundos=String.valueOf(x);
            segundos=segundos.substring(2, 4);
            duracion=minutos+":"+segundos;
            
        }catch(Exception e){
         System.out.print("ERROR "+e);
        }
        
        return duracion;
    }
    
    public Artista adquirirArtista(String nombreArtista, String genero){
        List<Artista> listaArtistas=new ClienteArtista().findAll_JSON();
        //List<Album> listaAlbums=new ClienteAlbum().findAll_JSON();
        boolean artistaExiste=false;
        Artista artistaCancion=new Artista();
        for(int i=0; i<listaArtistas.size(); i++){
            if(listaArtistas.get(i).getNombre().equals(nombreArtista)){
                artistaExiste=true;
                artistaCancion=listaArtistas.get(i);
            }
        }
        if(artistaExiste==false){
            Artista nuevoArtista= new Artista();
            nuevoArtista.setNombre(nombreArtista);
            new ClienteArtista().create_JSON(nuevoArtista);
            listaArtistas=new ClienteArtista().findAll_JSON();
            for(int i=0; i<listaArtistas.size(); i++){
                if(listaArtistas.get(i).getNombre().equals(nombreArtista)){
                    artistaCancion=listaArtistas.get(i);
                }
            }
            
        }
        GeneroArtista relGenArt=new GeneroArtista();
        relGenArt.setGenero(genero);
        relGenArt.setIdArtista(artistaCancion);
        new ClienteGeneroArtista().create_JSON(relGenArt);
        return artistaCancion;
    }
    
    public Album adquirirAlbum(String nombreAlbum, Artista nuevoArtista, String nombreCompania, String alloLanzamiento){
        List<Album> listaAlbums=new ClienteAlbum().findAll_JSON();
        boolean albumExiste=false;
        Album albumCancion=new Album();
        for(int i=0; i<listaAlbums.size(); i++){
            if(listaAlbums.get(i).getNombre().equals(nombreAlbum)){
                albumExiste=true;
                albumCancion=listaAlbums.get(i);
            }
        }
        if(albumExiste==false){
            Album nuevoAlbum= new Album();
            nuevoAlbum.setNombre(nombreAlbum);
            nuevoAlbum.setCompaniaDiscografica(nombreCompania);
            nuevoAlbum.setIdArtista(nuevoArtista);
            Calendar cal = Calendar.getInstance();
            
            cal.set(Integer.parseInt(alloLanzamiento), 0, 0);
            Date fecha = cal.getTime();
            nuevoAlbum.setFechaLanzamiento(fecha);
            new ClienteAlbum().create_JSON(nuevoAlbum);
            listaAlbums=new ClienteAlbum().findAll_JSON();
            for(int i=0; i<listaAlbums.size(); i++){
                if(listaAlbums.get(i).getNombre().equals(nombreAlbum)){
                    albumCancion=listaAlbums.get(i);
                }
            }
            
        }
        return albumCancion;
    }
    
    public boolean cancionExiste(Cancion cancionComprobar){
        boolean existe=false;
        List<Cancion> listaCanciones = new ClienteCancion().obtenerCancionesAlbum(cancionComprobar.getIdAlbum().getIdAlbum());
        for(int i=0; i<listaCanciones.size();i++){
            if(listaCanciones.get(i).equals(cancionComprobar)){
                existe=true;
            }
        }
        
        return existe;
    }
    
    private static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        //if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                String fileName = ze.getName();
                File newFile = new File(destDir +File.separator+fileName);
                System.out.println("Unzipping to "+newFile.getAbsolutePath());
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
                }
                fos.close();
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
    
    

    
    
}
