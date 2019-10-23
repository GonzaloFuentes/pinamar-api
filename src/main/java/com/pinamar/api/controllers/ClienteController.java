package com.pinamar.api.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.pinamar.api.negocio.Cliente;
import com.pinamar.api.negocio.Empleado;
import com.pinamar.api.negocio.EmpleadoFijo;
import com.pinamar.api.negocio.EmpleadoPorHora;
import com.pinamar.api.negocio.EmpleadoView;
import com.pinamar.api.negocio.Liquidacion;
import com.pinamar.api.negocio.Recibo;
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
				empF = new EmpleadoFijo(new ObjectId(empV.getId()), empV.getDni(), empV.getNombre(), empV.getDireccion(), empV.getPuesto(), empV.getFechaIngreso(), empV.getTipoLiquidacion(), empV.getSueldoBase(), empV.getDiasAusentes(), empV.getDiasEnfermedad(), empV.getDiasVacaciones(), empV.getHorasExtras(), empV.getFeriados(), empV.getDiasTrabajados(), empV.getConceptos(), empV.getCbu());
			else
				empH = new EmpleadoPorHora(new ObjectId(empV.getId()), empV.getDni(), empV.getNombre(), empV.getDireccion(), empV.getPuesto(), empV.getFechaIngreso(), empV.getTipoLiquidacion(), empV.getValorHora(), empV.getHorasTrabajadas(), empV.getConceptos(), empV.getCbu());
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
		//debo recibir el objeto completo pero como tengo dos array, creo que debe mandar los arrays inicilizados y nada mas
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
	
	@PostMapping("/empleados/{tipo}/{valor}/{cuit}")
	public ResponseEntity<Empleado> saveEmpleado(@RequestBody @Valid Empleado e, @PathVariable("tipo") String tipo, @PathVariable("valor") double valor, @PathVariable("cuit") String cuit) {
		Cliente c = clientesServ.findByCuit(cuit);
		Empleado aux = clientesServ.saveEmpleado(e, tipo, valor);
		c.addEmpleado(new ObjectId(aux.getId()));
		clientesServ.saveCliente(c);
		return ResponseEntity.ok(aux);
	}
	
	@PostMapping("/sueldos/{tipo}")
	public ResponseEntity<Liquidacion> liquidarSueldos(@PathVariable("tipo") String tipo){ //despues devuelve una liquidacion en vez de void
		List<Cliente> clientes = clientesServ.getAllClientes();
		Liquidacion liq = null;
		for (Cliente c : clientes) {
			List<EmpleadoFijo> empleadosFijos;
			List<EmpleadoPorHora> empleadosPorHora;
			empleadosFijos = clientesServ.getEmpleadosFijoByClienteAndTipo(c, tipo);
			empleadosPorHora = clientesServ.getEmpleadosHoraByClienteAndTipo(c, tipo);
			int cantidad = empleadosFijos.size() + empleadosPorHora.size();
			List<ObjectId> rs = new ArrayList<ObjectId>();
			List<Recibo> recibos = new ArrayList<Recibo>();
			Recibo rec;
			for (EmpleadoFijo e : empleadosFijos) {
				rec = e.liquidarSueldo();
				rs.add(new ObjectId(rec.getId()));
				recibos.add(rec);
			}
			for (EmpleadoPorHora e : empleadosPorHora) {
				rec = e.liquidarSueldo();
				rs.add(new ObjectId(rec.getId()));
				recibos.add(rec);
			}
			double total = 0;
			for (Recibo r : recibos) {
				total += r.getSueldoNeto();
			}
			liq = new Liquidacion(new ObjectId(), rs, tipo, new Date(), total);
			c.addLiquidacion(new ObjectId(liq.getId()));
			//facturar
			//save de recibos, liquidaciones
			for (Recibo r : recibos) {
				clientesServ.saveRecibo(r);
			}
			clientesServ.saveLiquidacion(liq);
		}
		return ResponseEntity.ok(liq);
	}
	
	@DeleteMapping("/{_id}")
	public ResponseEntity<Void> deleteCliente(@PathVariable String _id){
		clientesServ.deleteCliente(_id);
		return ResponseEntity.noContent().build();
	}

}