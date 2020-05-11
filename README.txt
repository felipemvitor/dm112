
Sequência de testes: Os seguintes testes podem ser utilizados para executar as funcionalidades da aplicação:

-Criar pedido
	POST: http://localhost:8080/PedidoDM112/api/order
	{
    	"number": 1,
    	"cpf": "065.431.349-52",
    	"value": 100.00,
    	"status": 0,
    	"orderDate": "1589164898788",
    	"issueDate": "",
    	"paymentDate": ""
	}
	
-Consultar pedido pelo número
	GET: http://localhost:8080/PedidoDM112/api/order/1	
	
-Consultar todos os pedidos
	GET: http://localhost:8080/PedidoDM112/api/orders

-Consultar pedidos pelo CPF
	GET: http://localhost:8080/PedidoDM112/api/order/065.431.349-52

-Consultar pedidos por status
	GET: http://localhost:8080/PedidoDM112/api/order/delivery	
	
-Atualizar pedido
	PUT: http://localhost:8080/PedidoDM112/rest/updateOrder
	{
    	"number": 3,
    	"cpf": "111.111.111-11",
    	"value": 143.78,
    	"status": 0,
    	"orderDate": "1589164898788",
    	"issueDate": "",
    	"paymentDate": ""
	}	

-Consultar a lista de pedidos a serem entregues (Ainda não entregues)
	GET: http://localhost:8080/LogisticaDM112/api/orders	
	
-Selecionar pedido para entrega
	PUT: http://localhost:8080/LogisticaDM112/api/delivery/start
	{	
    		"orderNumber": 1,
		"deliveryDate": null,
		"cpfClient": null,
	}


-Registrar a entrega de um pedido
	POST: http://localhost:8080/LogisticaDM112/api/delivery/finish
	{	
    		"orderNumber": 1,
		"deliveryDate": "1589164898788"
		"cpfClient": "222.222.222-22",
	}	
