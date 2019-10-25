package com.pinamar.api.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinamar.api.exceptions.ClienteException;
import com.pinamar.api.exceptions.EmpleadoException;
import com.pinamar.api.exceptions.LiquidacionException;
import com.pinamar.api.negocio.Cliente;
import com.pinamar.api.negocio.Concepto;
import com.pinamar.api.negocio.Empleado;
import com.pinamar.api.negocio.EmpleadoFijo;
import com.pinamar.api.negocio.EmpleadoPorHora;
import com.pinamar.api.negocio.EmpleadoView;
import com.pinamar.api.negocio.Factura;
import com.pinamar.api.negocio.InformeDTO;
import com.pinamar.api.negocio.InformeVariosDTO;
import com.pinamar.api.negocio.Liquidacion;
import com.pinamar.api.negocio.LiquidacionDTO;
import com.pinamar.api.negocio.MovimientoDTO;
import com.pinamar.api.negocio.Novedad;
import com.pinamar.api.negocio.Recibo;
import com.pinamar.api.negocio.ResumenEscuelaDTO;
import com.pinamar.api.negocio.Transferencia;
import com.pinamar.api.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private final ClienteService clientesServ;
	private Cliente c;
	
	@Autowired
	public ClienteController (ClienteService cliServ) {
		this.clientesServ = cliServ;
	}

	@GetMapping("")
	public ResponseEntity<List<Cliente>> getAllClientes() {
		//no devuelve los empleados, solo el id
		return ResponseEntity.ok(clientesServ.getAllClientes());
	}
	
	@GetMapping("/{_id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable("_id") String _id) throws ClienteException{
		//no devuelve los empleados, solo el id
		try {
			c = clientesServ.findById(_id);
		}
		catch(ClienteException e) {
			c = null;
		}
		return ResponseEntity.ok(c);
	}
	
	@GetMapping("/empleados-cliente/{cuit}")
	public ResponseEntity<List<Empleado>> getEmpleadosByCliente(@PathVariable("cuit") String cuit){
		Cliente c = clientesServ.findByCuit(cuit);
		return ResponseEntity.ok(clientesServ.getEmpleadosByCliente(c));
	}
	
	@GetMapping("/empleados/{_id}")
	public ResponseEntity<Empleado> getEmpleadoById(@PathVariable("_id") String _id) throws EmpleadoException{
		EmpleadoView empV;
		EmpleadoFijo empF = null;
		EmpleadoPorHora empH = null;
		try {
			empV = clientesServ.findEmpleadoById(_id);
			if(empV.getTipo().equalsIgnoreCase("FIJO"))
				empF = new EmpleadoFijo(new ObjectId(empV.getId()), empV.getDni(), empV.getCuit(), empV.getNombre(), empV.getDireccion(), empV.getPuesto(), 
						empV.getFechaIngreso(), empV.getTipoLiquidacion(), empV.getSueldoBase(), empV.getDiasAusentes(), empV.getDiasEnfermedad(), 
						empV.getDiasVacaciones(), empV.getHorasExtras(), empV.getFeriados(), empV.getDiasTrabajados(), empV.getConceptos(), empV.getCbu(), 
						empV.getRecibos(), empV.getUltimaLiquidacion(), empV.getDiasContratados());
			else
				empH = new EmpleadoPorHora(new ObjectId(empV.getId()), empV.getDni(), empV.getCuit(), empV.getNombre(), empV.getDireccion(), empV.getPuesto(), 
						empV.getFechaIngreso(), empV.getTipoLiquidacion(), empV.getValorHora(), empV.getHorasTrabajadas(), empV.getConceptos(), empV.getCbu(), 
						empV.getRecibos(), empV.getUltimaLiquidacion());
		}
		catch(EmpleadoException e) {
			empF = null;
			empH = null;
		}
		if (empF != null)
			return ResponseEntity.ok(empF);
		else return ResponseEntity.ok(empH);
	}
	
	@GetMapping("/login/{cuit}/{password}")
	public ResponseEntity<Cliente> login(@PathVariable("cuit") String cuit, @PathVariable("password") String password) throws ClienteException{
		try {
			c = clientesServ.findByCuit(cuit);
			if(!c.getPassword().equalsIgnoreCase(password)) {
				c = null;
			}
		}
		catch(ClienteException e) {
			c = null;
		}
		return ResponseEntity.ok(c);
	}
	
	@PostMapping("/")
	public ResponseEntity<Cliente> saveCliente(@RequestBody @Valid Cliente c){
		return ResponseEntity.ok(clientesServ.saveCliente(c));
	}
	
	@PutMapping("/")
	public ResponseEntity<Cliente> updateCliente(@RequestBody @Valid Cliente c){
		//hay que mandarle el id, si no te crea uno nuevo con otro id. Por lo que con lo que me manda, hago un get y despues actualizo los campos diferentes
		// los arrays se actualizan por otro metodo
		//si es persona fisica o juridica me lo tiene pasar. un boolean no puede ser null, por eso no verifico
		Cliente aux = clientesServ.findById(c.getId());
		if(c.getCuit() != null)
			aux.setCuit(c.getCuit());;
		if(c.getNombre() != null)
			aux.setNombre(c.getNombre());
		if(c.getPassword() != null)
			aux.setPassword(c.getPassword());
		return ResponseEntity.ok(clientesServ.saveCliente(aux)); //el metodo en el repo del save y update hacen lo mismo -> un save
	}
	
	@PostMapping("/empleados/{tipo}/{valor}/{cuit}/{diasContratados}")
	public ResponseEntity<Empleado> saveEmpleado(@RequestBody @Valid Empleado e, @PathVariable("tipo") String tipo, @PathVariable("valor") double valor, @PathVariable("cuit") String cuit, @PathVariable("diasContratados") int diasContratados) {
		Cliente c = clientesServ.findByCuit(cuit);
		Empleado aux = clientesServ.saveEmpleado(e, tipo, valor, diasContratados);
		c.addEmpleado(new ObjectId(aux.getId()));
		clientesServ.updateCliente(c);
		return ResponseEntity.ok(aux);
	}
	
	@PostMapping("/sueldos")
	public ResponseEntity<List<LiquidacionDTO>> liquidarSueldos() {
		List<Cliente> clientes = clientesServ.getAllClientes();
		List<Liquidacion> liqs = new ArrayList<Liquidacion>();
		Date hoy = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		String dia = sdf.format(hoy);
		Calendar cal = Calendar.getInstance();
		cal.setTime(hoy);
		int diaNumero = cal.get(Calendar.DAY_OF_MONTH);
		for (Cliente c : clientes) {
			List<EmpleadoFijo> empleadosFijos;
			List<EmpleadoPorHora> empleadosPorHora;
			empleadosFijos = clientesServ.getEmpleadosFijoByCliente(c);
			empleadosPorHora = clientesServ.getEmpleadosHoraByCliente(c);
			int diaMensual = c.getDiaMesLiquidacionMensual();
			int diaPrimer = c.getDiaPrimerQuincena();
			int diaSegundo = c.getDiaSegundaQuincena();
			String diaSemana = c.getDiaSemana();
			Liquidacion lMensual = null;
			Liquidacion lQuincenal = null;
			Liquidacion lSemanal = null;
			Liquidacion lDiaria = null;
			if(diaMensual == diaNumero)
				lMensual = clientesServ.liquidacionMensual(empleadosFijos, empleadosPorHora, c);
			if(diaSegundo == diaNumero || diaPrimer == diaNumero)
				lQuincenal = clientesServ.liquidacionQuincenal(empleadosFijos, empleadosPorHora, c);
			if(diaSemana != null)
				if(diaSemana.equalsIgnoreCase(dia))
					lSemanal = clientesServ.liquidacionSemanal(empleadosFijos, empleadosPorHora, c);
			lDiaria = clientesServ.liquidacionDiaria(empleadosFijos, empleadosPorHora, c);
			if(lMensual != null)
				liqs.add(lMensual);
			if(lQuincenal != null)
				liqs.add(lQuincenal);
			if(lSemanal != null)
				liqs.add(lSemanal);
			if(lDiaria != null)
				liqs.add(lDiaria);
		}
		List<LiquidacionDTO> aux = new ArrayList<LiquidacionDTO>();
		for(Liquidacion liquid : liqs) {
			aux.add(this.findLiquidacionById(liquid.getId()).getBody());
		}
		return ResponseEntity.ok(aux);
	}
	
	@GetMapping("/informes")
	public ResponseEntity<MovimientoDTO> enviarAlBanco() {
		String origenCBU = "";
		String destinoCBU = "";
		double amount = 0;
		List<InformeDTO> informes = new ArrayList<InformeDTO>();
		List<Liquidacion> liqs = clientesServ.getLiquidacionesNoFacturadas();
		List<Cliente> clientes = clientesServ.getAllClientes();
		List<Recibo> recibos = clientesServ.getAllRecibos();
		List<Factura> facturas = clientesServ.getAllFacturasPendientes();
		List<Empleado> empleados = clientesServ.getAllEmpleados();
		for (Liquidacion liq : liqs) {
			for (ObjectId rec : liq.getRecibos()) {
				for(Recibo r : recibos) {
					if(r.getId().equalsIgnoreCase(rec.toHexString())) {
						amount = r.getSueldoNeto();
						for (Cliente c : clientes) {
							for (ObjectId l : c.getLiquidaciones()) {
								if(l.toHexString().equalsIgnoreCase(liq.getId())) {
									origenCBU = c.getCbu();
									for(Empleado e : empleados) {
										for (ObjectId re : e.getRecibos()) {
											if(re.toHexString().equalsIgnoreCase(r.getId())) {
												destinoCBU = e.getCbu();
												liq.setFacturada(true);
												clientesServ.updateLiquidacion(liq);
												InformeDTO info = new InformeDTO(origenCBU, destinoCBU, amount);
												informes.add(info);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		for (Factura f : facturas) {
			amount = f.getTotal();
			Cliente aux = clientesServ.findById(f.getId_cliente());
			origenCBU = aux.getCbu();
			destinoCBU = "1942414460182641";
			f.setPendiente(false);
			clientesServ.updateFactura(f);
			InformeDTO info = new InformeDTO(origenCBU, destinoCBU, amount);
			informes.add(info);
		}
		List<Transferencia> transf = new ArrayList<Transferencia>();
		for (InformeDTO i : informes) {
			if(i.getDestinoCBU().equalsIgnoreCase("1942414460182641")) {
				//cobro
				Transferencia t = new Transferencia(i.getOrigenCBU(), (float )i.getAmount());
				transf.add(t);
			}
		}
		InformeVariosDTO infCobro = new InformeVariosDTO("1942414460182641", transf); //este le pega al cobro, entonces el cliente nos paga a nostros
		transf = new ArrayList<Transferencia>();
		List<InformeVariosDTO> pagosClienteAEmpleado = new ArrayList<InformeVariosDTO>();
		for(InformeDTO informe : informes) {
			if(!informe.getDestinoCBU().equalsIgnoreCase("1942414460182641")) {
				InformeVariosDTO inf = null;
				for(InformeVariosDTO i : pagosClienteAEmpleado) {
					if(i.getCbuDestino().equalsIgnoreCase(informe.getOrigenCBU())) {
						inf = i;
					}
				}
				if(inf == null) {
					Transferencia t = new Transferencia(informe.getDestinoCBU(), (float) informe.getAmount());
					List<Transferencia> aux = new ArrayList<Transferencia>();
					aux.add(t);
					inf = new InformeVariosDTO(informe.getOrigenCBU(), aux);
					pagosClienteAEmpleado.add(inf);
				}
				else {
					Transferencia t = inf.getTransferenciaByDestino(informe.getDestinoCBU());
					if(t == null) {
						t = new Transferencia(informe.getDestinoCBU(), (float) informe.getAmount());
						inf.getTranferenciasUnicas().add(t);
					}
				}
			}
		}
		MovimientoDTO mov = new MovimientoDTO(infCobro, pagosClienteAEmpleado);
		return ResponseEntity.ok(mov);
	}
	
	@PostMapping("/empleados/{_id}/conceptos")
	public ResponseEntity<Empleado> addConcepto(@RequestBody @Valid Concepto c, @PathVariable("_id") String _id) {
		EmpleadoView ev = clientesServ.findEmpleadoById(_id);
		EmpleadoFijo ef;
		EmpleadoPorHora eh;
		if(ev.getTipo().equalsIgnoreCase("FIJO")) {
			ef = new EmpleadoFijo(new ObjectId(ev.getId()), ev.getDni(), ev.getCuit(), ev.getNombre(), ev.getDireccion(), ev.getPuesto(), 
					ev.getFechaIngreso(), ev.getTipoLiquidacion(), ev.getSueldoBase(), ev.getDiasAusentes(), ev.getDiasEnfermedad(), ev.getDiasVacaciones(), 
					ev.getHorasExtras(), ev.getFeriados(), ev.getDiasTrabajados(), ev.getConceptos(), ev.getCbu(), ev.getRecibos(), ev.getUltimaLiquidacion(), ev.getDiasContratados());
			ef.addConcepto(c);
			clientesServ.updateEmpleadoFijo(ef);
			return ResponseEntity.ok(ef);
		} else {
			eh = new EmpleadoPorHora(new ObjectId(ev.getId()), ev.getDni(), ev.getCuit(), ev.getNombre(), ev.getDireccion(), ev.getPuesto(), ev.getFechaIngreso(), 
					ev.getTipoLiquidacion(), ev.getValorHora(), ev.getHorasTrabajadas(), ev.getConceptos(), ev.getCbu(), ev.getRecibos(), ev.getUltimaLiquidacion());
			eh.addConcepto(c);
			clientesServ.updateEmpleadoHora(eh);
			return ResponseEntity.ok(eh);
		}
	}
	
	@PostMapping("/empleados/{cuit}/novedades")
	public ResponseEntity<String> addNovedad(@RequestBody @Valid Novedad n, @PathVariable("cuit") String cuit) {
		//se devuelve el empleado para mostrar que las novedades se agregaron correctamente
		EmpleadoView ev = null;
		try {
			ev = clientesServ.findEmpleadoByCuit(cuit);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		EmpleadoFijo ef = null;
		EmpleadoPorHora eh = null;
		if(ev.getTipo().equalsIgnoreCase("FIJO")) {
			ef = new EmpleadoFijo(new ObjectId(ev.getId()), ev.getDni(), ev.getCuit(), ev.getNombre(), ev.getDireccion(), ev.getPuesto(), ev.getFechaIngreso(), 
					ev.getTipoLiquidacion(), ev.getSueldoBase(), ev.getDiasAusentes(), ev.getDiasEnfermedad(), ev.getDiasVacaciones(), ev.getHorasExtras(), 
					ev.getFeriados(), ev.getDiasTrabajados(), ev.getConceptos(), ev.getCbu(), ev.getRecibos(), ev.getUltimaLiquidacion(), ev.getDiasContratados());
			if((n.getDiasAusentes() < n.getDiasEnfermedad() + n.getDiasVacaciones()) || (ef.getDiasContratados() < n.getDiasAusentes() + n.getFeriados())) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Los dias son inconsistentes. Los ausentes por enfermedad mas los ausentes por vacaciones son mas que los ausentes totales o los ausentes totales mas los feriados trabajados es mas que los dias establecidos por contrato");
			}
			int ausencias = n.getDiasAusentes() - n.getDiasEnfermedad() - n.getDiasVacaciones();
			ef.setDiasAusentes(ausencias);
			ef.setDiasEnfermedad(n.getDiasEnfermedad());
			ef.setDiasVacaciones(n.getDiasVacaciones());
			ef.setHorasExtra(n.getHorasExtra());
			ef.setFeriados(n.getFeriados());
			clientesServ.updateEmpleadoFijo(ef);
			n.setIdEmpleado(new ObjectId(ef.getId()));
			clientesServ.saveNovedad(n);
			return ResponseEntity.ok(ef.getCuit());
		}
		else {
			eh = new EmpleadoPorHora(new ObjectId(ev.getId()), ev.getDni(), ev.getCuit(), ev.getNombre(), ev.getDireccion(), ev.getPuesto(), ev.getFechaIngreso(), 
					ev.getTipoLiquidacion(), ev.getValorHora(), ev.getHorasTrabajadas(), ev.getConceptos(), ev.getCbu(), ev.getRecibos(), ev.getUltimaLiquidacion());
			eh.setHorasTrabajadas(n.getHorasTrabajadas());
			clientesServ.updateEmpleadoHora(eh);
			n.setIdEmpleado(new ObjectId(eh.getId()));
			clientesServ.saveNovedad(n);
			return ResponseEntity.ok(eh.getCuit());
		}
	}
	
	@GetMapping("/liquidacion/{_id}")
	public ResponseEntity<LiquidacionDTO> findLiquidacionById(@PathVariable("_id") String _id) throws LiquidacionException {
		Liquidacion liq = null;
		LiquidacionDTO liquidacion = null;
		List<Recibo> recibos = new ArrayList<Recibo>();
		try {
			liq = clientesServ.findLiquidacionById(_id);
			for (ObjectId rec : liq.getRecibos()) {
				recibos.add(clientesServ.findReciboById(rec.toHexString()));
			}
			liquidacion = new LiquidacionDTO(new ObjectId(liq.getId()), recibos, liq.getTipo(), liq.getFecha(), liq.getTotal());
		}
		catch (LiquidacionException ex) {
			liquidacion = null;
		}
		return ResponseEntity.ok(liquidacion);
	}
	
	@GetMapping("/liquidaciones/{cuit}")
	public ResponseEntity<ResumenEscuelaDTO> getUltimaLiquidacionByCliente(@PathVariable("cuit") String cuit){
		Cliente c = clientesServ.findByCuit(cuit);
		List<Liquidacion> liqs = clientesServ.getLiquidacionesByCliente(c);
		Liquidacion l = liqs.get(liqs.size()-1);
		ResumenEscuelaDTO r = new ResumenEscuelaDTO(l.getFecha(), (float) l.getTotal(), l.isFacturada());
		return ResponseEntity.ok(r);
	}
	
	@GetMapping("/liquidaciones-cliente/{cuit}")
	public ResponseEntity<List<Liquidacion>> getLiquidacionesByCliente(@PathVariable("cuit") String cuit){
		Cliente c = clientesServ.findByCuit(cuit);
		return ResponseEntity.ok(clientesServ.getLiquidacionesByCliente(c));
	}
	
	@GetMapping("/empleados/recibos/{id_recibo}")
	public ResponseEntity<String> getNombreEmpleadoRecibo(@PathVariable("id_recibo") String id){
		return ResponseEntity.ok(clientesServ.findNombreEmpleadoRecibo(id));
	}
	
	@GetMapping("/facturas/{cuit}")
	public ResponseEntity<List<Factura>> findFacturasByCliente(@PathVariable("cuit") String cuit){
		Cliente c = clientesServ.findByCuit(cuit);
		return ResponseEntity.ok(clientesServ.findFacturasByCliente(c.getId()));
	}
	
	@DeleteMapping("/{_id}")
	public ResponseEntity<Void> deleteCliente(@PathVariable String _id){
		clientesServ.deleteCliente(_id);
		return ResponseEntity.noContent().build();
	}

}