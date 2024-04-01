package Datos;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class reporteTxt {
    public static void generarReporte(ArrayList<String> datos, ArrayList<String> datosGastos, String tipoReporte, String ingresos, String gastos, String totalGanancias) {
        //Formato de fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        //Fecha actual
        String fechaActual = dateFormat.format(new Date());
        //Carpeta del proyecto
        String proyectoDirectory = System.getProperty("user.dir");
        //Carpeta de cierre de caja
        String carpetaCierreCaja = proyectoDirectory + "\\cierre_caja";
        //Nombre del archivo
        String nombreArchivo = carpetaCierreCaja + "\\" + fechaActual + "_cierre_caja.txt";

        // Verificar si la carpeta "Cierre Caja" existe, si no, crearla
        File carpetaCierreCajaFile = new File(carpetaCierreCaja);
        if (!carpetaCierreCajaFile.exists()) {
            carpetaCierreCajaFile.mkdirs(); // Crea la carpeta y cualquier directorio padre que falte
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            // Escribe el encabezado del reporte
            writer.write("Reporte generado el " + fechaActual);
            writer.newLine();

            // Escribe los datos en el archivo
            for (String dato : datos) {
                writer.write(dato);
                writer.newLine();
            }

            // Escribe los datos de gastos en el archivo
            for (String datoGasto : datosGastos) {
                writer.write(datoGasto);
                writer.newLine();
            }
            
            // Encabezdo resumen
            writer.write("**********************CIERRE DE CAJA***************************");
            writer.newLine();

            // Escribe el total de ingresos
            writer.write("Ingresos: " + ingresos);
            writer.newLine();

            // Escribe el total de gastos
            writer.write("Gastos: " + gastos);
            writer.newLine();

            // Escribe el total de ganancias
            writer.write("Total Ganancias: " + totalGanancias);
            writer.newLine();

            System.out.println("Reporte generado exitosamente: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }
}
