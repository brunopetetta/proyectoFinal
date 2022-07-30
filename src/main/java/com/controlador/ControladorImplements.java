package com.controlador;

import com.modelo.Apunte;
import com.modelo.Carrito;
import com.modelo.PrecioFotocopia;
import com.modeloDAO.ApunteDAO;
import com.modeloDAO.PrecioFotocopiaDAO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ControladorImplements {
    
    
    public static void actualizarPaginaDesde(int idApunte, int paginaDesde, List<Carrito> listaCarrito) throws Exception {
        for (int j = 0; j < listaCarrito.size(); j++) {
            if (listaCarrito.get(j).getIdApunte() == idApunte) {
                ApunteDAO adao = new ApunteDAO();
                Apunte a = new Apunte();
                a = adao.listarId(idApunte);
                listaCarrito.get(j).setPaginaDesde(paginaDesde);
                int valorAnillado = 0;
                int valorTI = 1;
                if(listaCarrito.get(j).getAnillado().equals("checked")){
                    valorAnillado = 20;
                }
                if(listaCarrito.get(j).getTipoImpresion().equals("checked")){
                    valorTI = 2;
                }
                int cantPaginas = listaCarrito.get(j).getPaginaHasta() - listaCarrito.get(j).getPaginaDesde() + 1;
                double subtotal = (valorAnillado + cantPaginas *  listaCarrito.get(j).getPrecioCompra() * valorTI) * listaCarrito.get(j).getCantidadCopias() ; 
                listaCarrito.get(j).setSubtotal(subtotal);
            }
        }
    }
    
    public static void actualizarPaginaHasta(int idApunte, int paginaHasta, List<Carrito> listaCarrito) throws Exception {
        for (int j = 0; j < listaCarrito.size(); j++) {
            if (listaCarrito.get(j).getIdApunte() == idApunte) {
                ApunteDAO adao = new ApunteDAO();
                Apunte a = new Apunte();
                a = adao.listarId(idApunte);
                listaCarrito.get(j).setPaginaHasta(paginaHasta);
                int valorAnillado = 0;
                int valorTI = 1;
                if(listaCarrito.get(j).getAnillado().equals("checked")){
                    valorAnillado = 20;
                }
                if(listaCarrito.get(j).getTipoImpresion().equals("checked")){
                    valorTI = 2;
                }
                int cantPaginas = listaCarrito.get(j).getPaginaHasta() - listaCarrito.get(j).getPaginaDesde() + 1;
                double subtotal = (valorAnillado + cantPaginas * listaCarrito.get(j).getPrecioCompra() * valorTI) * listaCarrito.get(j).getCantidadCopias(); 
                listaCarrito.get(j).setSubtotal(subtotal);
            }
        }
    }
    
    public static void actualizarCantidad(int idApunte, int cantCopias, List<Carrito> listaCarrito) throws Exception {
        for (int j = 0; j < listaCarrito.size(); j++) {
            if (listaCarrito.get(j).getIdApunte() == idApunte) {
                ApunteDAO adao = new ApunteDAO();
                Apunte a = new Apunte();
                a = adao.listarId(idApunte);
                listaCarrito.get(j).setCantidadCopias(cantCopias);
                int valorAnillado = 0;
                int valorTI = 1;
                if(listaCarrito.get(j).getAnillado().equals("checked")){
                    valorAnillado = 20;
                }
                if(listaCarrito.get(j).getTipoImpresion().equals("checked")){
                    valorTI = 2;
                }
                int cantPaginas = listaCarrito.get(j).getPaginaHasta() - listaCarrito.get(j).getPaginaDesde() + 1;
                double subtotal = (valorAnillado + cantPaginas * listaCarrito.get(j).getPrecioCompra() * valorTI) * cantCopias; 
                listaCarrito.get(j).setSubtotal(subtotal);
            }
        }
    }
    
    public static void actualizarAnillado(int idApunte, int anillado, List<Carrito> listaCarrito) throws Exception {
        for (int j = 0; j < listaCarrito.size(); j++) {
            if (listaCarrito.get(j).getIdApunte() == idApunte) {
                ApunteDAO adao = new ApunteDAO();
                Apunte a = new Apunte();
                a = adao.listarId(idApunte);
                int valorAnillado;
                double subtotal;
                int valorTI = 1;
                if(listaCarrito.get(j).getTipoImpresion().equals("checked")){
                    valorTI = 2;
                }
                if(anillado == 1){
                    listaCarrito.get(j).setAnillado("checked");
                    valorAnillado = 20;
                } else {
                    listaCarrito.get(j).setAnillado("");
                    valorAnillado = 0;
                }
                int cantPaginas = listaCarrito.get(j).getPaginaHasta() - listaCarrito.get(j).getPaginaDesde() + 1;
                subtotal = (valorAnillado + cantPaginas * listaCarrito.get(j).getPrecioCompra() * valorTI) * listaCarrito.get(j).getCantidadCopias();
                listaCarrito.get(j).setSubtotal(subtotal); 
            }                
        }
    }
    
    public static void actualizarTipoImpresion(int idApunte, int tipoImpresion, List<Carrito> listaCarrito) throws Exception {
        for (int j = 0; j < listaCarrito.size(); j++) {
            if (listaCarrito.get(j).getIdApunte() == idApunte) {
                ApunteDAO adao = new ApunteDAO();
                Apunte a = new Apunte();
                a = adao.listarId(idApunte);
                int valorAnillado = 0;
                if(listaCarrito.get(j).getAnillado().equals("checked")){
                    valorAnillado = 20;
                }
                if(tipoImpresion == 1){
                    listaCarrito.get(j).setTipoImpresion("checked");
                    int cantPaginas = listaCarrito.get(j).getPaginaHasta() - listaCarrito.get(j).getPaginaDesde() + 1;
                    double subtotal = (valorAnillado + cantPaginas * listaCarrito.get(j).getPrecioCompra() * 2) * listaCarrito.get(j).getCantidadCopias();
                    listaCarrito.get(j).setSubtotal(subtotal);
                } else{
                    listaCarrito.get(j).setTipoImpresion("");
                    int cantPaginas = listaCarrito.get(j).getPaginaHasta() - listaCarrito.get(j).getPaginaDesde() + 1;
                    double subtotal = (valorAnillado + cantPaginas * listaCarrito.get(j).getPrecioCompra()) * listaCarrito.get(j).getCantidadCopias();
                    listaCarrito.get(j).setSubtotal(subtotal);
                }
            }                
        }
    }
    
    public static void actualizarObservaciones(int idApunte, String observaciones, List<Carrito> listaCarrito) throws Exception {
        for (int j = 0; j < listaCarrito.size(); j++) {
            if (listaCarrito.get(j).getIdApunte() == idApunte) {
                listaCarrito.get(j).setObservaciones(observaciones);
            }                
        }
    }
    
    public static void eliminarApunte(int idApunte, List<Carrito> listaCarrito) {
        if (listaCarrito != null) {
            for (int j = 0; j < listaCarrito.size(); j++) {
                if (listaCarrito.get(j).getIdApunte() == idApunte) {
                    listaCarrito.remove(j);
                }
            }
        }
    }
    
    public static void agregarCarrito(HttpServletRequest request, List<Carrito> listaCarrito, double subtotal) throws SQLException, Exception {
        Carrito car;
        ApunteDAO adao = new ApunteDAO();
        Apunte a = new Apunte();
        PrecioFotocopiaDAO precioDAO = new PrecioFotocopiaDAO();
        PrecioFotocopia preciofo = new PrecioFotocopia();
        preciofo = precioDAO.listarMax();
        int cantidad = 1;
        int pos = 0;
        int ida = Integer.parseInt(request.getParameter("id"));
        a = adao.listarId(ida);
        if (listaCarrito.size() > 0) {
            for (int i = 0; i < listaCarrito.size(); i++) {
                if (listaCarrito.get(i).getIdApunte() == ida) {
                    pos = i;
                }
            }
            if (ida == listaCarrito.get(pos).getIdApunte()) {
                cantidad = listaCarrito.get(pos).getCantidadCopias() + cantidad;
                subtotal = listaCarrito.get(pos).getPrecioCompra() * cantidad;
                listaCarrito.get(pos).setCantidadCopias(cantidad);
                listaCarrito.get(pos).setSubtotal(subtotal);
            } else {
                car = new Carrito();
                car.setIdApunte(a.getId());
                car.setNombre(a.getNombre());
                car.setDescripcion(a.getDescripcion());
                car.setCantidadCopias(cantidad);
                car.setPrecioCompra(preciofo.getValorFotocopia());
                car.setAnillado("");
                car.setTipoImpresion("");
                car.setPaginaDesde(1);
                car.setPaginaHasta(a.getCantPaginas());
                car.setCantPaginas(a.getCantPaginas());
                subtotal = cantidad * preciofo.getValorFotocopia() * (a.getCantPaginas());
                car.setSubtotal(subtotal);
                car.setObservaciones("");
                listaCarrito.add(car);
            }
        } else {
            car = new Carrito();
            car.setIdApunte(a.getId());
            car.setNombre(a.getNombre());
            car.setDescripcion(a.getDescripcion());
            car.setCantidadCopias(cantidad);
            car.setPrecioCompra(preciofo.getValorFotocopia());
            car.setAnillado("");
            car.setTipoImpresion("");
            car.setPaginaDesde(1);
            car.setPaginaHasta(a.getCantPaginas());
            car.setCantPaginas(a.getCantPaginas());
            subtotal = cantidad * preciofo.getValorFotocopia() * (a.getCantPaginas());
            car.setSubtotal(subtotal);
            car.setObservaciones("");
            listaCarrito.add(car);
        }
    }
    
    public static void response(String url, String message, String alert,HttpServletRequest request) {
        request.setAttribute("URL", url);
        request.setAttribute("mensaje", message);
        request.setAttribute("alert", alert);
    }
    
}
