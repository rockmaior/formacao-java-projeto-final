package br.com.casadocodigo.loja.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.models.Pedidos;
import br.com.casadocodigo.loja.models.Produto;

@Controller
public class PedidosServicoController {

	@Autowired
	private RestTemplate restTemplate;


	@RequestMapping(value = "/pedidos", method=RequestMethod.GET)
	public ModelAndView listPedidosServico() {
		String url = "https://book-payment.herokuapp.com/orders";
		ResponseEntity<List<Pedidos>>responsePedidos = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Pedidos>>() {});
		
		System.out.println(responsePedidos.getBody());

		
		List<Pedidos> pedidos = responsePedidos.getBody();


		ModelAndView modelAndView = new ModelAndView("pedidos");
		modelAndView.addObject("pedidos", pedidos);
		return modelAndView;
	}
	

}