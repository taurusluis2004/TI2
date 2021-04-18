package app;

import static spark.Spark.*;

import service.ProdutoService;

public class Aplicacao {
	
	private static ProdutoService produtoService = new ProdutoService();
	
    public static void main(String[] args) {
        port(6789);


        post("/produtos", (request, response) -> produtoService.add(request, response));

        get("/produtos/:id", (request, response) -> produtoService.get(request, response));

        get("/produtos/update/:id", (request, response) -> produtoService.update(request, response));

        get("/produtos/delete/:id", (request, response) -> produtoService.remove(request, response));

        get("/produtos", (request, response) -> produtoService.getAll(request, response));
        
        
    }
}