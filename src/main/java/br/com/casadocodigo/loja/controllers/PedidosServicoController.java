package br.com.casadocodigo.loja.controllers;

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

@Controller
public class PedidosServicoController {

	@Autowired
	private RestTemplate restTemplate;


	@RequestMapping(value = "/pedidos", method=RequestMethod.GET)
	public ModelAndView listPedidosServico() {
//		String url = "https://book-payment.herokuapp.com/orders";
		String url = "http://localhost:86/caixa/orders.json";

		ResponseEntity<List<Pedidos>>responsePedidos = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Pedidos>>() {});
		List<Pedidos> pedidos = responsePedidos.getBody();

		ModelAndView modelAndView = new ModelAndView("pedidos");
		modelAndView.addObject("pedidos", pedidos);
		return modelAndView;
	}
	

}
