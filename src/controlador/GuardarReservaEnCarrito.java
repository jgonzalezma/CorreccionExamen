package controlador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Parcela;
import modelo.ParcelaModelo;
import modelo.Reserva;

/**
 * Servlet implementation class GuardarReservaEnCarrito
 */
@WebServlet("/GuardarReservaEnCarrito")
public class GuardarReservaEnCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardarReservaEnCarrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// coger parametros
		int idParcela = Integer.parseInt(request.getParameter("id_parcela"));
		
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String dni = request.getParameter("dni");
		int numeroPersonas = Integer.parseInt(request.getParameter("numero_personas"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaInicio = null;
		Date fechaFin = null;
		boolean luz;
		try {
			fechaInicio = sdf.parse(request.getParameter("fecha_inicio"));
			fechaFin = sdf.parse(request.getParameter("fecha_fin"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(request.getParameter("luz") == null){
			luz = false;
		}else{
			luz = true;
		}
		
		// crear objeto reserva
		Reserva reserva = new Reserva();
		reserva.setNombre_usuario(nombre);
		reserva.setApellido_usuario(apellido);
		reserva.setDni_usuario(dni);
		reserva.setNumero_usuarios(numeroPersonas);
		reserva.setInicio_reserva(fechaInicio);
		reserva.setFin_reserva(fechaFin);
		reserva.setLuz(luz);
		
		// coger datos de la parcela con su id desde el modelo (selectId)
		ParcelaModelo parcelaModelo = new ParcelaModelo();
		Parcela parcela = parcelaModelo.selectPorId(idParcela);
		reserva.setParcela(parcela);
		
		// guardar la reserva en session
		HttpSession session = request.getSession();
		session.setAttribute("reserva", reserva);
		// pasar a la vista la parcela y la reserva
		request.getRequestDispatcher("infoReserva.jsp").forward(request, response);
		
	}

}
