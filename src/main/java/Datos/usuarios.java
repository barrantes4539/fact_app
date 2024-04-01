package Datos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class usuarios {
    
    //Variables Globales
    public String[][] datosUsuarios = {{"Yendry", "89896767", "675654335", "yendry@gmail.com"}, {"Kendall", "82376578", "765367886", "yendry@gmail.com"}};
    public String[][] productos = {{"Laptop", "700000"}, {"Tablet", "250000"}, {"Celular", "350000"}, {"PS5", "800000"}};
    public String motivosGastos[][];
    private final double IVA_COSTA_RICA = 0.13; // Tasa de IVA en Costa Rica (ajusta según sea necesario)

    public class Usuario {
        private String nombre;
        private String telefono;
        private String ID;
        private String correo;
        public ArrayList<String> calculos;
        public ArrayList<String> gastos;

        public Usuario(String nombre, String telefono, String ID, String correo) {
            this.nombre = nombre;
            this.telefono = telefono;
            this.ID = ID;
            this.correo = correo;
            this.calculos = new ArrayList<>();
            this.gastos = new ArrayList<>();
        }

        public void adicionCalculo(String producto, int precioU, int cantidad) {
            // Obtener el precio unitario del producto
            int precioUnitario = obtenerPrecioUnitario(precioU);

            // Calcular el total considerando el IVA
            double total = calcularTotalConIVA(precioUnitario, cantidad);

            // Construir la cadena del cálculo con el total
            String calculo = String.format("%s, %s, %s, %s, %s, %s",
                    nombre, correo, producto, cantidad, precioUnitario, total);

            // Agregar el cálculo a la lista
            this.calculos.add(calculo);
        }
        public void adicionGastos(String motivoGasto, int montoGasto) {
            // Construir la cadena del cálculo con el total
            String datosGastos = String.format("%s, %s", 
                    motivoGasto, montoGasto);

            // Agregar el cálculo a la lista
            this.gastos.add(datosGastos);
        }

        public String getID() {
            return ID;
        }

        public String getNombre() {
            return nombre;
        }
        
        public String getProducto() {
            return nombre;
        }
        
        public String getTelefono() {
            return telefono;
        }
        
        public String getCorreo() {
            return correo;
        }

        // Agrega más getters y setters según sea necesario

        private double calcularTotalConIVA(int precioUnitario, int cantidad) {
            // Calcular el total sin IVA
            double totalSinIVA = precioUnitario * cantidad;

            // Calcular el total con IVA
            double totalConIVA = totalSinIVA * (1 + IVA_COSTA_RICA);

            return totalConIVA;
        }

        private int obtenerPrecioUnitario(int precio) {
            return precio;
        }
    }

    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<String> listaProductos;

    public usuarios() {
        this.listaUsuarios = new ArrayList<>();
        inicializarUsuarios();
        this.listaProductos = new ArrayList<>(Arrays.asList(obtenerProductos()));
    }

    private void inicializarUsuarios() {
        for (String[] datosUsuario : datosUsuarios) {
            Usuario usuario = new Usuario(datosUsuario[0], datosUsuario[1], datosUsuario[2], datosUsuario[3]);
            listaUsuarios.add(usuario);
        }
    }
    
    public Usuario buscarUsuarioPorNombre(String nombreUsuario) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.nombre.equals(nombreUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    public void adicionCalculo(String nombreUsuario, String producto, int precioU, int cantidad) {
        Usuario usuario = buscarUsuarioPorNombre(nombreUsuario);
        if (usuario != null) {
            usuario.adicionCalculo(producto, precioU, cantidad);
        } else {
            System.out.println("Usuario no encontrado: " + nombreUsuario);
        }
    }
    
    public void adicionGastos(String motivoGasto, int montoGasto) {
        Usuario usuario = null;
        
        usuario.adicionGastos(motivoGasto, montoGasto);
   
    }
    
   
    public String obtenerIDPorNombre(String nombreUsuario) {
        for (String[] datosUsuario : datosUsuarios) {
            if (datosUsuario[0].equals(nombreUsuario)) {
                return datosUsuario[2]; // El tercer elemento es el RUC
            }
        }
        System.out.println("Usuario no encontrado: " + nombreUsuario);
        return null;
    }
    
    public String obtenerPrecioPorProducto(String producto) {
        for (String[] datoProducto : productos) {
            if (datoProducto[0].equals(producto)) {
                return datoProducto[1]; // El tercer elemento es el RUC
            }
        }
        System.out.println("Producto no encontrado: " + producto);
        return null;
    }

    public String[] obtenerProductos() {
        String[] nombresProductos = new String[productos.length];
        for (int i = 0; i < productos.length; i++) {
            nombresProductos[i] = productos[i][0];
        }
        return nombresProductos;
    }
    
    // Método para agregar un nuevo usuario dinámicamente  
    public void agregarNuevoUsuario(String nombre, String telefono, String ID, String correo) {
 
 

        String[] nuevoUsuario = {nombre, telefono, ID, correo};
        datosUsuarios = Arrays.copyOf(datosUsuarios, datosUsuarios.length + 1);
        datosUsuarios[datosUsuarios.length - 1] = nuevoUsuario;

        // También puedes inicializar el nuevo usuario en la lista de usuarios
        Usuario usuario = new Usuario(nombre, telefono, ID, correo);
        listaUsuarios.add(usuario);
            // Imprimir todos los datos de listaUsuarios
        System.out.println("Datos de listaUsuarios:");
        for (Usuario u : listaUsuarios) {
            System.out.println("Nombre: " + u.getNombre());
            System.out.println("Teléfono: " + u.getTelefono());
            System.out.println("ID: " + u.getID());
            System.out.println("Correo: " + u.getCorreo());
            System.out.println("---");
        }
    }
}


