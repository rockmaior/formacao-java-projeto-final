package br.com.casadocodigo.loja.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.RelatorioProdutos;

@Controller
public class RelatorioProdutosController {

	@Autowired
	private ProdutoDAO dao;

	@Autowired
	private RelatorioProdutos relatorio;

	@RequestMapping(value = "/relatorio-produtos", method = RequestMethod.GET)
	@ResponseBody
	public RelatorioProdutos listarProdutos(@RequestParam(value = "data", required = false) String data)
			throws ParseException {

		List<Produto> produtos;

		if (data==null || data.equals("")) {
			produtos = dao.listar();

		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(data));
			produtos = dao.listar(cal);
		}

		relatorio.setDataGeracao(Calendar.getInstance());
		relatorio.setQuantidade(produtos.size());
		relatorio.setProdutos(produtos);

		return relatorio;
	}

}
