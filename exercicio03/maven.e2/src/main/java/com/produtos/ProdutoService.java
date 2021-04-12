package com.produtos;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.ProdutoDAO;
import model.Produto;
import spark.Request;
import spark.Response;

public class ProdutoService {

	private ProdutoDAO ProdutoDAO;

	public ProdutoService() {
		produtoDAO = new ProdutoDAO.conectar;
	}

	public Object add(Request request, Response response) {
		String descricao = request.queryParams("descricao");
		float preco = Float.parseFloat(request.queryParams("preco"));
		int quantidade = Integer.parseInt(request.queryParams("quantidade"));
		LocalDateTime dataFabricacao = LocalDateTime.parse(request.queryParams("data_fabricacao"));
		LocalDate dataValidade = LocalDate.parse(request.queryParams("data_validade"));

		int id = produtoDAO.getMaxId() + 1;

		Produto produto = new Produto(id, descricao, preco, quantidade, dataFabricacao, dataValidade);

		produtoDAO.add(produto);

		response.status(201); // 201 Created
		return id;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Produto produto = (Produto) produtoDAO.get(id);
		
		if (produto != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<produto>\n" + 
            		"\t<id> " + produto.getId() + "</id>\n" +
            		"\t<descricao> " + produto.getDescricao() + "</descricao>\n" +
            		"\t<preco> " + produto.getPreco() + "</preco>\n" +
            		"\t<quantidade> " + produto.getQuant() + "</quantidade>\n" +
            		"\t<fabricacao> " + produto.getDataFabricacao() + "</fabricacao>\n" +
            		"\t<validade> " + produto.getDataValidade() + "</validade>\n" +
            		"</produto>\n";
        } else {
            response.status(404); // 404 Not found
            return "Produto " + id + " n�o encontrado.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Produto produto = (Produto) produtoDAO.get(id);

        if (produto != null) {
        	String descricao = request.queryParams("descricao");
    		float preco = Float.parseFloat(request.queryParams("preco"));
    		int quantidade = Integer.parseInt(request.queryParams("quantidade"));
    		LocalDateTime dataFabricacao = LocalDateTime.parse(request.queryParams("data_fabricacao"));
    		LocalDate dataValidade = LocalDate.parse(request.queryParams("data_validade"));

        	produtoDAO.update(produto);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Produto n�o encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Produto produto = (Produto) produtoDAO.get(id);

        if (produto != null) {

            produtoDAO.remove(produto);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Produto n�o encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<produtos type=\"array\">");
		for (Produto produto : produto.getAll()) {
			Produto produto = (Produto) produto;
			returnValue.append("\n<produto>\n" + 
            		"\t<id> " + produto.getId() + "</id>\n" +
            		"\t<descricao> " + produto.getDescricao() + "</descricao>\n" +
            		"\t<preco> " + produto.getPreco() + "</preco>\n" +
            		"\t<quantidade> " + produto.getQuant() + "</quantidade>\n" +
            		"\t<fabricacao> " + produto.getDataFabricacao() + "</fabricacao>\n" +
            		"\t<validade> " + produto.getDataValidade() + "</validade>\n" +
            		"</produto>\n");
		}
		returnValue.append("</produtos>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();

	}

}
