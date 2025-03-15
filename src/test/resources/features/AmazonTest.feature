@ML
Feature: Test Mercado Libre busqueda de productos

@Compra
Scenario: Como cliente cuando busco por Alexa, yo quiero ver si la tercera opción de la segunda pagina esta disponible y agregarla al carrito
    Given el usuario navega a mercadolibre.com.mx
    And busca por Producto
    And Seleccionar pagina 2
    And selecciona el tercer item
    Then el usuario es capaz de agregarlo al carrito


@valida
Scenario: "Como usuario, yo puedo ver todos las categorias del listado"
    Given El usuario navega a mercado libre
    Then El usuario verifica que el número de categorías es 21
    

