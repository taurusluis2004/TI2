package app;
import static spark.Spark.*;

import service.BemDeConsumoService;

public class Aplicacao {
	
	private static BemDeConsumoService bemDeConsumoService = new BemDeConsumoService();
	
    public static void main(String[] args) {
        port(6789);


        post("/produtos", (request, response) -> bemDeConsumoService.add(request, response));

        get("/produtos/:id", (request, response) -> bemDeConsumoService.get(request, response));

        get("/produtos/update/:id", (request, response) -> bemDeConsumoService.update(request, response));

        get("/produtos/delete/:id", (request, response) -> bemDeConsumoService.remove(request, response));

        get("/produtos", (request, response) -> bemDeConsumoService.getAll(request, response));
        
        
    }
}